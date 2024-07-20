package com.example.rohan1;



import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, Integer> listGroupIcons;

    private ExpandableListView.OnChildClickListener onChildClickListener;
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 HashMap<String, Integer> listGroupIcons) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        this.listGroupIcons = listGroupIcons;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public interface OnChildClickListener {
        void onChildClick(int groupPosition, int childPosition);

        void onChildClick(String childItem);
    }

    public void setOnChildClickListener(OnChildClickListener listener) {
        this.onChildClickListener = (ExpandableListView.OnChildClickListener) listener;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }

        TextView childTextView = convertView.findViewById(R.id.child_item_text);
        childTextView.setText(childText);
        convertView.setBackgroundColor(Color.TRANSPARENT);
//        ImageView imageView = convertView.findViewById(R.id.group_item_image);
//        // Change the image resource or do any other modification you need
//        imageView.setImageResource(R.drawable.baseline_star_24);

        convertView.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               if (groupPosition == 0 && childPosition == 0) {
                   Intent intent = new Intent(context, firstSem.class);
                   context.startActivity(intent);
               }else if (groupPosition == 0 && childPosition == 1) {
                   Intent intent = new Intent(context, secondSem.class);
                   context.startActivity(intent);
               }else if (groupPosition == 1 && childPosition == 0) {
                   Intent intent = new Intent(context, thirdSem.class);
                   context.startActivity(intent);
               } else if (groupPosition == 1 && childPosition == 1) {
                   Intent intent = new Intent(context, fourthSem.class);
                   context.startActivity(intent);
               } else if (groupPosition == 2 && childPosition == 0) {
                   Intent intent = new Intent(context, fifthSem.class);
                   context.startActivity(intent);
               } else if (groupPosition == 2 && childPosition == 1) {
                   Intent intent = new Intent(context, sixthSem.class);
                   context.startActivity(intent);

               } else if (groupPosition == 3 && childPosition == 0) {
                   Intent intent = new Intent(context, seventhSem.class);
                   context.startActivity(intent);

               } else {
                   Intent intent = new Intent(context, eighthSem.class);
                   context.startActivity(intent);
               }
           }
           });
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, null);
        }

        ImageView groupIconImageView = convertView.findViewById(R.id.group_item_image);
        groupIconImageView.setImageResource(listGroupIcons.get(headerTitle));

        TextView groupTextView = convertView.findViewById(R.id.group_item_text);
        groupTextView.setText(headerTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}




