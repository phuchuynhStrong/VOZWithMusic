package phuc.frankie.vozwithmusic;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import phuc.frankie.vozwithmusic.Database.Database;
import phuc.frankie.vozwithmusic.Presenter.StoryAdapter;
import phuc.frankie.vozwithmusic.Utility.Utility;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {


    RecyclerView storiesList;
    List<Database.Story> searchResult;
    List<Database.Story> initialList;
    StoryAdapter storyAdapter;
    Database db;

    public FragmentMain() {
        // Required empty public constructor
        db = Database.getInstance();
    }
    @Override
    public void onResume(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int color = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_theme_color_list),""));
        if (color == 0 ){
            storiesList.setBackgroundColor(getResources().getColor(R.color.background_color_white));
        }
        else{
            storiesList.setBackgroundColor(getResources().getColor(R.color.background_color_black));
        }
        if (storyAdapter != null) {
            initialList.clear();
            if (searchResult != null) initialList.addAll(searchResult);
            else initialList.addAll(db.getListStories());
            storyAdapter.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v("FragmentMain","onCreateView");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        storiesList = rootView.findViewById(R.id.fragment_main_recycler_view);
        initAdapter();

        return rootView;
    }


    public void initAdapter(){
        initialList = new ArrayList<>();
        initialList.addAll(db.getListStories());
        storyAdapter = new StoryAdapter(initialList,getContext());
        storiesList.setAdapter(storyAdapter);
        storiesList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void filterByName(String value){
        if (value.equals("")){
            initialList.clear();
            initialList.addAll(db.getListStories());
            storyAdapter.notifyDataSetChanged();
            return;
        }
        searchResult = new ArrayList<>();
        for (Database.Story story : db.getListStories()){
            if (story.getValue().contains(value)) searchResult.add(story);
            else if (story.getName().contains(value)) searchResult.add(story);
        }
        initialList.clear();
        initialList.addAll(searchResult);
        storyAdapter.notifyDataSetChanged();
    }

    public void filterByType(String type){
        if (type.equals(Utility.TYPE_ALL)){
            searchResult = null;
            if (initialList != null) initialList.clear();
            initialList.addAll(db.getListStories());
            storyAdapter.notifyDataSetChanged();
            return;
        }
        if (searchResult != null) searchResult.clear();
        searchResult = new ArrayList<>();
        for (Database.Story story : db.getListStories()){
            if (story.getType().equals(type)) searchResult.add(story);
        }
        if (initialList != null) initialList.clear();
        initialList.addAll(searchResult);
        storyAdapter.notifyDataSetChanged();
    }
}
