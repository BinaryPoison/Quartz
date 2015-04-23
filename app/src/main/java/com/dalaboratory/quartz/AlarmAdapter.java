package com.dalaboratory.quartz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Sam on 4/23/2015.
 */
public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    List<Alarm> list = Collections.emptyList();

    public AlarmAdapter(Context context, List<Alarm> list) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.alarm, parent, false);
        AlarmViewHolder holder = new AlarmViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        Alarm current = list.get(position);
        holder.icon.setImageResource(current.getIconId());
        holder.label.setText(current.getLabel());
        holder.description.setText(current.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView label;
        TextView description;

        public AlarmViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.alarm_icon);
            label = (TextView) itemView.findViewById(R.id.alarm_label);
            description = (TextView) itemView.findViewById(R.id.alarm_description);
        }
    }

}
