package com.example.droidloginhosti;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Andrea on 30/01/2015.
 */
public class ExpandableListAdapter extends Activity {
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }


            public Object getChild(int groupPosition, int childPosititon) {
                return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                        .get(childPosititon);
            }


            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }


            public View getChildView(int groupPosition, final int childPosition,
                                     boolean isLastChild, View convertView, ViewGroup parent) {

                final String childText = (String) getChild(groupPosition, childPosition);

                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) this._context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_item, null);
                }

                TextView txtListChild = (TextView) convertView
                        .findViewById(R.id.lblListItem);

                txtListChild.setText(childText);
                return convertView;
            }


            public int getChildrenCount(int groupPosition) {
                return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                        .size();
            }

            public Object getGroup(int groupPosition) {
                return this._listDataHeader.get(groupPosition);
            }


            public int getGroupCount() {
                return this._listDataHeader.size();
            }


            public long getGroupId(int groupPosition) {
                return groupPosition;
            }


            public View getGroupView(int groupPosition, boolean isExpanded,
                                     View convertView, ViewGroup parent) {
                String headerTitle = (String) getGroup(groupPosition);
                if (convertView == null) {
                    LayoutInflater infalInflater = (LayoutInflater) this._context
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = infalInflater.inflate(R.layout.list_group, null);
                }

                TextView lblListHeader = (TextView) convertView
                        .findViewById(R.id.lblListHeader);
                lblListHeader.setTypeface(null, Typeface.BOLD);
                lblListHeader.setText(headerTitle);

                return convertView;
            }


            public boolean hasStableIds() {
                return false;
            }


            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
}