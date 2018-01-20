package com.example.nhem.eventwithlove.event.activities.models.domain;

import android.support.annotation.NonNull;

import com.alorma.timeline.TimelineView;

import java.io.Serializable;

/**
 * Created by Hau on 1/21/2018.
 */

public class Timeline implements Serializable{
    private String name;
    private Long start;
    private int type;
    private int alignment;

    public Timeline() {
    }

    Timeline(@NonNull String name) {
        this(name, TimelineView.TYPE_DEFAULT);
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Timeline(@NonNull String name, int type) {
        this(name, type, TimelineView.ALIGNMENT_DEFAULT);
    }

    Timeline(@NonNull String name, int type, int alignment) {
        this.name = name;
        this.type = type;
        this.alignment = alignment;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @TimelineView.TimelineType
    public int getType() {
        return type;
    }

    public void setType(@TimelineView.TimelineType int type) {
        this.type = type;
    }

    @TimelineView.TimelineAlignment
    public int getAlignment() {
        return alignment;
    }

    public void setAlignment(@TimelineView.TimelineAlignment int alignment) {
        this.alignment = alignment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", alignment=").append(alignment);
        sb.append('}');
        return sb.toString();
    }
}
