package com.hackro.movies.central.movies;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.hackro.movies.central.R;
import com.hackro.movies.central.movies.models.Genre;
import com.hackro.movies.central.movies.models.Result;
import com.hackro.movies.central.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class MoviesAdapter extends BaseExpandableListAdapter implements MoviesAdapterFilter {

  private List<Genre> listDataHeaderFilters = new ArrayList<>();
  private HashMap<Genre, List<Result>> listDataChildFilters = new HashMap<>();
  private Context context;
  private List<Genre> listDataHeader;
  private HashMap<Genre, List<Result>> listDataChild;

  MoviesAdapter(Context context, final List<Genre> listDataHeader,
      final HashMap<Genre, List<Result>> listChildData) {
    this.context = context;
    this.listDataHeader = listDataHeader;
    this.listDataChild = listChildData;
    listDataHeaderFilters.addAll(listDataHeader);
    listDataChildFilters.putAll(listChildData);
  }

  @Override public Object getChild(int groupPosition, int childPosititon) {
    return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
  }

  @Override public long getChildId(int groupPosition, int childPosition) {
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

  @Override public int getChildrenCount(int groupPosition) {
    return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
  }

  @Override public Object getGroup(int groupPosition) {
    return this.listDataHeader.get(groupPosition);
  }

  @Override public int getGroupCount() {
    return this.listDataHeader.size();
  }

  @Override public long getGroupId(int groupPosition) {
    return groupPosition;
  }

  @Override public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
      ViewGroup parent) {
    Genre headerTitle = (Genre) getGroup(groupPosition);
    if (convertView == null) {
      LayoutInflater infalInflater =
          (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = infalInflater.inflate(R.layout.movies_group, parent, false);
    }

    TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
    lblListHeader.setTypeface(null, Typeface.BOLD);
    lblListHeader.setText(headerTitle.getName());

    return convertView;
  }

  @Override public boolean hasStableIds() {
    return false;
  }

  @Override public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
  }

  //Order Genres and movies alphabetically
  @Override public void orderAlphabetically() {

    Set<Map.Entry<Genre, List<Result>>> mapEntries = listDataChild.entrySet();

    List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<>(mapEntries);

    for (Map.Entry<Genre, List<Result>> ra : aList) {

      Collections.sort(ra.getValue(), (result, t1) -> result.getTitle().compareTo(t1.getTitle()));
    }
    Collections.sort(this.listDataHeader, (genre, t1) -> genre.getName().compareTo(t1.getName()));
    notifyDataSetChanged();
  }

  public void orderDate() {
    Set<Map.Entry<Genre, List<Result>>> mapEntries = listDataChild.entrySet();
    List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<>(mapEntries);
    for (Map.Entry<Genre, List<Result>> ra : aList) {
      Collections.sort(ra.getValue(), (result, t1) -> Strings.toDate(result.getReleaseDate())
          .compareTo(Strings.toDate(t1.getReleaseDate())));
    }
    notifyDataSetChanged();
  }

  @Override public void orderStars() {
    Set<Map.Entry<Genre, List<Result>>> mapEntries = listDataChild.entrySet();
    List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<>(mapEntries);
    for (Map.Entry<Genre, List<Result>> ra : aList) {
      Collections.sort(aList.get(1).getValue(), (result, t1) -> {
        Double r = result.getVoteAverage();
        Double t = t1.getVoteAverage();
        return t.compareTo(r);
      });
    }
    notifyDataSetChanged();
  }

  @Override public void findName(final String name) {
    Set<Map.Entry<Genre, List<Result>>> mapEntries = listDataChildFilters.entrySet();
    List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<>(mapEntries);

    for (Map.Entry<Genre, List<Result>> ra : aList) {
      List<Result> r = new ArrayList<>();
      for (Result result : ra.getValue()) {
        if (result.getTitle().toLowerCase().contains(name.toLowerCase())) r.add(result);
      }
      if (r.size() == 0) {
        listDataHeader.remove(ra.getKey());
        listDataChild.put(ra.getKey(), r);
      } else {
        listDataChild.put(ra.getKey(), r);
      }
    }

    notifyDataSetChanged();
  }

  @Override public void findStars(Double vote_average) {

    Set<Map.Entry<Genre, List<Result>>> mapEntries = listDataChildFilters.entrySet();
    List<Map.Entry<Genre, List<Result>>> aList = new LinkedList<>(mapEntries);

    for (Map.Entry<Genre, List<Result>> ra : aList) {
      List<Result> r = new ArrayList<>();
      for (Result result : ra.getValue()) {
        if (result.getVoteAverage() >= vote_average) r.add(result);
      }
      if (r.size() == 0) {
        listDataHeader.remove(ra.getKey());
        listDataChild.put(ra.getKey(), r);
      } else {
        listDataChild.put(ra.getKey(), r);
      }
    }
    notifyDataSetChanged();
  }

  @Override public void resetFilters() {
    if (!listDataHeader.isEmpty()) {
      listDataHeader.clear();
      listDataHeader.addAll(listDataHeaderFilters);
      listDataChild.putAll(listDataChildFilters);
      notifyDataSetChanged();
    }
  }
}
