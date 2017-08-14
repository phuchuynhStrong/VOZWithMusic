package phuc.frankie.vozwithmusic.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import phuc.frankie.vozwithmusic.ActivityBookDetails;
import phuc.frankie.vozwithmusic.ActivityMain;
import phuc.frankie.vozwithmusic.ActivityReading;
import phuc.frankie.vozwithmusic.Database.Database;
import phuc.frankie.vozwithmusic.R;
import phuc.frankie.vozwithmusic.Utility.Utility;

/**
 * Created by frank on 03/08/2017.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private List<Database.Story> listStories;
    private Context mContext;
    public StoryAdapter(List<Database.Story> mList, Context context){
        listStories = mList;
        mContext = context;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the view for this view holder
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View thisItemsView = layoutInflater.inflate(R.layout.main_item_layout,parent, false);
        // Call the view holder's constructor, and pass the view to it;
        // return that new view holder
        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) thisItemsView.getLayoutParams();
        layoutParams.height = display.getHeight() / 6;
        thisItemsView.setLayoutParams(layoutParams);
        return new StoryViewHolder(thisItemsView);
    }

    @Override
    public void onBindViewHolder(final StoryViewHolder holder, final int position) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        int color = Integer.parseInt(preferences.getString(mContext.getResources().getString(R.string.pref_key_theme_color_list),""));
        if (color == 0){
            holder.container.setBackground(mContext.getResources().getDrawable(R.drawable.main_item_border));
            holder.name.setTextColor(mContext.getResources().getColor(R.color.text_color_black));
        }
        else {
            holder.container.setBackground(mContext.getResources().getDrawable(R.drawable.main_item_border_white));
            holder.name.setTextColor(mContext.getResources().getColor(R.color.text_color_white));
        }
        holder.logo.setImageResource(listStories.get(position).getCover());
        holder.name.setText(listStories.get(position).getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database.Story story = listStories.get(position);
                Intent bookDetails = new Intent(mContext,ActivityBookDetails.class);
                bookDetails.putExtra(Utility.COVER,story.getCover());
                bookDetails.putExtra(Utility.NAME,story.getName());
                bookDetails.putExtra(Utility.AUTHOR,story.getAuthor());
                bookDetails.putExtra(Utility.CONTENT,Utility.readRawTextFile(mContext,story.getContent()));
                bookDetails.putExtra(Utility.UPDATEDAT,story.getUpdateDay());
                bookDetails.putExtra(Utility.LIST_CHAPTER,story.getNumberOfChap());
                bookDetails.putExtra(Utility.RAW_LINK_CHAPTER,story.getFirstChapter());
                Pair<View,String> cover = Pair.create((View) holder.logo,"cover");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((ActivityMain)mContext, cover);
                mContext.startActivity(bookDetails,options.toBundle());
            }
        });

    }

    @Override
    public int getItemCount() {
        return listStories.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder{
        LinearLayout container;
        ImageView logo;
        TextView name;
        public StoryViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.main_item_container);
            logo = itemView.findViewById(R.id.main_item_logo);
            name = itemView.findViewById(R.id.main_item_name);
        }
    }

}

