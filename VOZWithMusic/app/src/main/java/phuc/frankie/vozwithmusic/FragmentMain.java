package phuc.frankie.vozwithmusic;


import android.os.Bundle;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {


    RecyclerView storiesList;
    StoryAdapter storyAdapter;
    Database db;

    public FragmentMain() {
        // Required empty public constructor
        db = Database.getInstance();
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
        storyAdapter = new StoryAdapter(db.getListStories(),getContext());
        storiesList.setAdapter(storyAdapter);
        storiesList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
