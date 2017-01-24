package com.example.hackro.myapplication.home;

/**
 * Created by hackro on 21/01/17.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.hackro.myapplication.R;
import com.example.hackro.myapplication.models.Generos.Genre;
import com.example.hackro.myapplication.models.Movies.Result;
import com.example.hackro.myapplication.util.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements ExpandableListAdapterFilter {

    private Context _context;
    private List<Genre> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<Genre, List<Result>> _listDataChild;

    List<Genre> _listDataHeaderFilters = null;
    HashMap<Genre, List<Result>> _listDataChildFilters = null;

    public ExpandableListAdapter(Context context, final List<Genre> listDataHeader, final
    HashMap<Genre, List<Result>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;

        _listDataHeaderFilters = new ArrayList<>(listDataHeader);
        _listDataChildFilters = new HashMap<Genre, List<Result>>(listChildData);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Result childText = (Result) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText.getTitle());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Genre headerTitle = (Genre) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getName());

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


    //Order Genres and movies alphabetically
    @Override
    public void orderAlphabetically() {

        Set<Map.Entry<Genre, List<Result>>> mapEntries = _listDataChild.entrySet();

        List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<Map.Entry<Genre, List<Result>>>(mapEntries);

        for (Map.Entry<Genre, List<Result>> ra : aList) {

            Collections.sort(ra.getValue(), new Comparator<Result>() {
                @Override
                public int compare(Result result, Result t1) {
                    return result.getTitle().compareTo(t1.getTitle());
                }
            });

        }
        Collections.sort(this._listDataHeader, new Comparator<Genre>() {
            @Override
            public int compare(Genre genre, Genre t1) {
                return genre.getName().compareTo(t1.getName());
            }
        });
        notifyDataSetChanged();
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void orderDate() {
        Set<Map.Entry<Genre, List<Result>>> mapEntries = _listDataChild.entrySet();
        List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<Map.Entry<Genre, List<Result>>>(mapEntries);
        for (Map.Entry<Genre, List<Result>> ra : aList) {
            Collections.sort(ra.getValue(), new Comparator<Result>() {
                @Override
                public int compare(Result result, Result t1) {
                    return
                            Utils.convertStringtoDate(result.getReleaseDate()).compareTo(
                                    Utils.convertStringtoDate(t1.getReleaseDate()));
                }
            });
        }

        notifyDataSetChanged();
    }


    @Override
    public void orderStars() {
        Set<Map.Entry<Genre, List<Result>>> mapEntries = _listDataChild.entrySet();
        List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<Map.Entry<Genre, List<Result>>>(mapEntries);
        for (Map.Entry<Genre, List<Result>> ra : aList) {
            Collections.sort(aList.get(1).getValue(), new Comparator<Result>() {
                @Override
                public int compare(Result result, Result t1) {
                    Double r = result.getVoteAverage();
                    Double t = t1.getVoteAverage();
                    return t.compareTo(r);
                }
            });
        }

        notifyDataSetChanged();
    }

    @Override
    public void findName(final String name) {
        Set<Map.Entry<Genre, List<Result>>> mapEntries = _listDataChildFilters.entrySet();
        List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<Map.Entry<Genre, List<Result>>>(mapEntries);
        List<Result> r = null;
        for (Map.Entry<Genre, List<Result>> ra : aList) {
            r = new ArrayList<>();
            for (Result result : ra.getValue()) {
                if (result.getTitle().toLowerCase().contains(name.toLowerCase()))
                    r.add(result);
            }
            if (r.size() == 0) {
                _listDataHeader.remove(ra.getKey());
                _listDataChild.put(ra.getKey(), r);
            } else
                _listDataChild.put(ra.getKey(), r);
        }

        notifyDataSetChanged();

    }

    @Override
    public void findStars(Double vote_average) {

        Set<Map.Entry<Genre, List<Result>>> mapEntries = _listDataChildFilters.entrySet();
        List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<Map.Entry<Genre, List<Result>>>(mapEntries);
        List<Result> r = null;
        for (Map.Entry<Genre, List<Result>> ra : aList) {
            r = new ArrayList<>();
            for (Result result : ra.getValue()) {
                if (result.getVoteAverage() >= vote_average)
                    r.add(result);
            }
            if (r.size() == 0) {
                _listDataHeader.remove(ra.getKey());
                _listDataChild.put(ra.getKey(), r);
            } else
                _listDataChild.put(ra.getKey(), r);
        }

        notifyDataSetChanged();
    }

    @Override
    public void resetFilters() {
        _listDataHeader.clear();
        _listDataHeader.addAll(_listDataHeaderFilters);
        _listDataChild.putAll(_listDataChildFilters);
        notifyDataSetChanged();
    }


}
