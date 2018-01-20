package com.example.nhem.eventwithlove.event.activities.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.models.domain.Event;

import org.w3c.dom.Text;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hau on 1/20/2018.
 */

public class ListEventAdapter extends BaseAdapter {

    private List<Event> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListEventAdapter(List<Event> listData, Context context) {
        this.listData = listData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_list_event, null);
            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.tv_event_name);
            holder.tvAddress = view.findViewById(R.id.tv_address);
            holder.tvTimeStart = view.findViewById(R.id.tv_time);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Event event = this.listData.get(i);
        holder.tvName.setText(event.getName());
        holder.tvAddress.setText(event.getAddress());
        Date date = new Date(event.getTimeStart());
        DateFormat format = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
        String time = format.format(date);
        holder.tvTimeStart.setText(time);
        return view;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvAddress;
        TextView tvTimeStart;
    }
}
