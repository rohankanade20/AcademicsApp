package com.example.rohan1.design;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rohan1.R;
import com.example.rohan1.model.SocialMedialist;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private final Context context;
    private final ArrayList<SocialMedialist> medialists;

    public CustomAdapter(Context context, ArrayList<SocialMedialist> medialists) {
        this.context = context;
        this.medialists = medialists;
    }

    @Override
    public int getCount() {
        return medialists.size() ;
    }

    @Override
    public Object getItem(int position) {
        return medialists.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        }
        else {
            holderView = (HolderView) convertView.getTag();
        }

        SocialMedialist list= medialists.get(position);
        holderView.iconList.setImageResource(list.getSocialMediaIcon());
        holderView.mediaName.setText(list.getSocialMediaName());


        return convertView;
    }
    private static class HolderView{
        private final ImageView iconList;
        private final TextView mediaName;

        public HolderView(View view){
            iconList = view.findViewById(R.id.listImage);
            mediaName = view.findViewById(R.id.listName);

        }

    }
}
