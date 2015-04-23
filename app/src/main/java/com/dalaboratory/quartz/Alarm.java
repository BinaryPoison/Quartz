package com.dalaboratory.quartz;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by Sam on 4/23/2015.
 */
public class Alarm {

    public static final long PATTERN_SHORT = 200;
    public static final long PATTERN_LONG = 400;
    public static final long PATTERN_PAUSE = 200;

    private int id;
    private int iconId;
    private String label;
    private String description;
    private long triggerAtMillis;
    private long intervalMillis;
    private long[] pattern;
    private String patternString;

    public Alarm() {

    }

    public Alarm(Cursor cursor) {
        this.id = Integer.parseInt(cursor.getString(0));
        this.iconId = Integer.parseInt(cursor.getString(1));
        this.label = cursor.getString(2);
        this.description = cursor.getString(3);
        this.triggerAtMillis = Long.parseLong(cursor.getString(4));
        this.intervalMillis = Long.parseLong(cursor.getString(5));
        this.patternString = cursor.getString(6);
        this.pattern = stringToPattern(patternString);
    }

    public Alarm(int iconId, String label, String description, long triggerAtMillis, long intervalMillis, long[] pattern) {
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.pattern = pattern;
        this.patternString = patternToString(pattern);
    }

    public Alarm(int iconId, String label, String description, long triggerAtMillis, long intervalMillis, String patternString) {
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.patternString = patternString;
        this.pattern = stringToPattern(patternString);
    }

    public Alarm(int iconId, String label, String description, long triggerAtMillis, long intervalMillis, long[] pattern, String patternString) {
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.pattern = pattern;
        this.patternString = patternString;
    }

    public Alarm(int id, int iconId, String label, String description, long triggerAtMillis, long intervalMillis, long[] pattern) {
        this.id = id;
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.pattern = pattern;
        this.patternString = patternToString(pattern);
    }

    public Alarm(int id, int iconId, String label, String description, long triggerAtMillis, long intervalMillis, String patternString) {
        this.id = id;
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.patternString = patternString;
        this.pattern = stringToPattern(patternString);
    }

    public Alarm(int id, int iconId, String label, String description, long triggerAtMillis, long intervalMillis, long[] pattern, String patternString) {
        this.id = id;
        this.iconId = iconId;
        this.label = label;
        this.description = description;
        this.triggerAtMillis = triggerAtMillis;
        this.intervalMillis = intervalMillis;
        this.pattern = pattern;
        this.patternString = patternString;
    }

    public static long[] stringToPattern(String string) {
        if (string.length() == 0)
            return new long[] {0};
        else{
            long[] pattern = new long[string.length() * 2 - 1];
            for (int i = 0; i < string.length(); i++) {
                Log.d("DEBUG", i+" "+string.charAt(i)+" ");
                switch (string.charAt(i)) {
                    case '.':
                        pattern[i * 2] = PATTERN_SHORT;
                        break;
                    case '-':
                        pattern[i * 2] = PATTERN_LONG;
                        break;
                    default:
                        break;
                }

                if (i != string.length()-1) {
                    Log.d("DEBUG", "there's more");
                    pattern[i * 2 + 1] = PATTERN_PAUSE;
                }
            }

            return pattern;
        }
    }

    public static String patternToString(long[] pattern) {
        if (pattern.length == 0) return "";
        else {
            String sPattern = "";
            for (int i = 0; i < pattern.length / 2 + 1; i++) {
                switch ((int) pattern[i-1]) {
                    case (int) PATTERN_SHORT:
                        sPattern += ".";
                        break;
                    case (int) PATTERN_LONG:
                        sPattern += "-";
                }
            }

            return sPattern;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTriggerAtMillis() {
        return triggerAtMillis;
    }

    public void setTriggerAtMillis(long triggerAtMillis) {
        this.triggerAtMillis = triggerAtMillis;
    }

    public long getIntervalMillis() {
        return intervalMillis;
    }

    public void setIntervalMillis(long intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    public long[] getPattern() {
        return pattern;
    }

    public void setPattern(long[] pattern) {
        this.pattern = pattern;
    }


    public String getPatternString() {
        return patternString;
    }

    public void setPatternString(String patterString) {
        this.patternString = patterString;
    }
}
