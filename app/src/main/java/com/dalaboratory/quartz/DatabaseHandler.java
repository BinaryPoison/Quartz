package com.dalaboratory.quartz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 4/23/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "alarmManager";

    // Alarms table name
    private static final String TABLE_ALARMS = "alarms";

    // Alarms Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ICON_ID = "icon_id";
    private static final String KEY_LABEL = "label";
    private static final String KEY_DESC = "description";
    private static final String KEY_TRIGGER_AT = "triggerAtMillis";
    private static final String KEY_INTERVAL = "intervalMillis";
    private static final String KEY_PATTERN = "pattern";

    // Alarms Table Columns array
    private static final String[] COLUMNS = new String[] {
        KEY_ID,
        KEY_ICON_ID,
        KEY_LABEL,
        KEY_DESC,
        KEY_TRIGGER_AT,
        KEY_INTERVAL,
        KEY_PATTERN,};

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_ALARMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ICON_ID + " INTEGER,"
                + KEY_LABEL + " TEXT,"
                + KEY_DESC  + " TEXT,"
                + KEY_TRIGGER_AT + " INTEGER,"
                + KEY_INTERVAL + " INTEGER,"
                + KEY_PATTERN + " TEXT" + ")";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("drop table if exists " + TABLE_ALARMS);

        // Create tables again
        onCreate(db);
    }

    public void addAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = toValue(alarm);
        db.insert(TABLE_ALARMS, null, values);
        db.close();
    }

    public Alarm getAlarm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.query(TABLE_ALARMS, COLUMNS, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Alarm alarm = new Alarm(cursor);
        return alarm;
    }

    public List<Alarm> getAllAlarms() {
        List<Alarm> alarmList = new ArrayList<Alarm>();

        String selectQuery = "SELECT * FROM " + TABLE_ALARMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm(cursor);
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }

        return alarmList;
    }

    public int getAlarmsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ALARMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updateAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = toValue(alarm);

        // updating row
        return db.update(TABLE_ALARMS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(alarm.getId())});
    }

    public void deleteAlarm(Alarm alarm){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARMS, KEY_ID + " = ?",
                new String[] { String.valueOf(alarm.getId()) });
        db.close();
    }

    public ContentValues toValue(Alarm alarm){

        ContentValues values = new ContentValues();

        values.put(KEY_ICON_ID, alarm.getIconId());
        values.put(KEY_LABEL, alarm.getLabel());
        values.put(KEY_DESC, alarm.getDescription());
        values.put(KEY_TRIGGER_AT, alarm.getTriggerAtMillis());
        values.put(KEY_INTERVAL, alarm.getIntervalMillis());
        values.put(KEY_PATTERN, alarm.getPatternString());

        return values;
    }
}
