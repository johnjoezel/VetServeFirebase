package com.example.vetservefirebase.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.example.vetservefirebase.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private Map<String, ArrayList<String>> providerServices;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 Map<String, ArrayList<String>> providerServices) {
        this._context = context;
        this.listDataHeader = listDataHeader;
        this.providerServices = providerServices;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.providerServices.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
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

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.providerServices.get(this.listDataHeader.get(groupPosition))
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
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        Typeface regular = ResourcesCompat.getFont(_context, R.font.champagne);
        lblListHeader.setTypeface(regular, Typeface.NORMAL);
        lblListHeader.setText(headerTitle);
        if(isExpanded)
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.arrow_down,0,0,0);
        else
            lblListHeader.setCompoundDrawablesWithIntrinsicBounds(R.drawable.arrow_up,0,0,0);
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
