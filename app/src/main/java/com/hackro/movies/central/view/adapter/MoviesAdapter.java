package com.hackro.movies.central.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.hackro.movies.central.R;
import com.hackro.movies.central.view.model.Result;
import com.hackro.movies.central.view.model.MoviesView;

import java.util.List;

/**
 * Created by hackro on 3/03/17.
 */
public class MoviesAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<MoviesView> moviesViews;

    public MoviesAdapter(Context context, List<MoviesView> moviesViews) {
        this.context = context;
        this.moviesViews = moviesViews;
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return moviesViews.get(groupPosition).getMovies().getResults().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

        final Result childText = (Result) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.movies_item, parent, false);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText.getTitle());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return moviesViews.get(groupPosition).getMovies().getResults().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return moviesViews.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return moviesViews.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                                       ViewGroup parent) {
        MoviesView headerTitle = (MoviesView) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater =
                    (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.movies_group, parent, false);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getGenre().getName());

        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}