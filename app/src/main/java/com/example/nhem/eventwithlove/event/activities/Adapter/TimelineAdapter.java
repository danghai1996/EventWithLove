package com.example.nhem.eventwithlove.event.activities.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alorma.timeline.TimelineView;
import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.models.domain.Timeline;

import java.util.List;

/**
 * Created by Hau on 1/21/2018.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolderItem> {
    private final LayoutInflater inflater;
    private final List<Timeline> timelines;

    public TimelineAdapter(LayoutInflater inflater, List<Timeline> timelines) {
        this.inflater = inflater;
        this.timelines= timelines;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderItem(inflater.inflate(R.layout.item_timeline, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {
        Timeline timeline = timelines.get(position);

        holder.text.setText(timeline.getName());
        holder.timeline.setTimelineType(timeline.getType());
        holder.timeline.setTimelineAlignment(timeline.getAlignment());
    }

    @Override
    public int getItemCount() {
        return timelines.size();
    }

    static class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView text;
        TimelineView timeline;

        ViewHolderItem(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.textView);
            timeline = (TimelineView) itemView.findViewById(R.id.timeline);
        }
    }
}
