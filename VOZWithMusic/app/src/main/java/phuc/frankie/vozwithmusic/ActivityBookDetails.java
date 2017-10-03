package phuc.frankie.vozwithmusic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import phuc.frankie.vozwithmusic.Utility.Utility;

public class ActivityBookDetails extends AppCompatActivity {


    ImageView cover;
    TextView storyName,author,content,updatedAt,numberOfChapter;
    RelativeLayout container;
    TextView toolbarTitle;
    ImageButton back;
    Button selectChapter;
    Button startReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        back = (ImageButton) findViewById(R.id.toolbar_ic_back);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title_book_details);
        cover = (ImageView) findViewById(R.id.story_cover_activity_book_details);
        storyName = (TextView) findViewById(R.id.story_name);
        author = (TextView) findViewById(R.id.story_author);
        content = (TextView) findViewById(R.id.story_content);
        updatedAt = (TextView) findViewById(R.id.story_update_day);
        container = (RelativeLayout) findViewById(R.id.details_container_activity_book_details);
        selectChapter = (Button) findViewById(R.id.select_chapter_spinner);
        numberOfChapter = (TextView) findViewById(R.id.number_chapters);
        startReading = (Button) findViewById(R.id.start_reading_btn);


        if (getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER,0) == 0){
            startReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ActivityBookDetails.this, "Truyện đang được cập nhật!", Toast.LENGTH_SHORT).show();
                }
            });
            selectChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ActivityBookDetails.this, "Truyện đang được cập nhật!", Toast.LENGTH_SHORT).show();
                }
            });
            //
        }
        else {
            startReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent readIntent = new Intent(getBaseContext(), ActivityReading.class);
                    readIntent.putExtra(Utility.NAME, storyName.getText().toString());
                    readIntent.putExtra(Utility.CONTENT, 1); // Chapter number
                    readIntent.putExtra(Utility.RAW_LINK_CHAPTER, getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER, 0));
                    startActivity(readIntent);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                    boolean musicOn = preferences.getBoolean(getResources().getString(R.string.pref_key_music_onoff), true);
                    if (musicOn) {
                        Intent playMusic = new Intent(getBaseContext(), MusicPlayService.class);
                        ArrayList<Integer> listSongs = new ArrayList<>();
                        listSongs.add(R.raw.em_la_tat_ca);
                        listSongs.add(R.raw.lac_nhau_co_phai_muon_doi);
                        Bundle argument = new Bundle();
                        argument.putIntegerArrayList("songs", listSongs);
                        playMusic.putExtras(argument);
                        startService(playMusic);
                        startReading.setContentDescription("clicked");
                    }

                }
            });
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });
        toolbarTitle.setText(getIntent().getStringExtra(Utility.NAME));
        cover.setImageResource(getIntent().getIntExtra(Utility.COVER,0));
        storyName.setText(getIntent().getStringExtra(Utility.NAME));
        author.setText(getIntent().getStringExtra(Utility.AUTHOR));
        content.setText(getIntent().getStringExtra(Utility.CONTENT));
        updatedAt.setText("Cập nhật mới nhất ngày: " + getIntent().getStringExtra(Utility.UPDATEDAT));


        int numbersChapter = getIntent().getIntExtra(Utility.LIST_CHAPTER,0);
        final List<String> listChap = new ArrayList<>();
        for (int i = 1; i <= numbersChapter;i++){
            listChap.add("Chương " + i);
        }

        numberOfChapter.setText(numberOfChapter.getText().toString() + ": " + numbersChapter);
        selectChapter.setText("Đọc tiếp");
        selectChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingleChoice(listChap.toArray(new String[0]));
            }
        });

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int textSize = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_text_size_list),"13"));
        int color = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_theme_color_list),"0"));

        if (color == 0){
            //White
            container.setBackgroundColor(getResources().getColor(R.color.cover_background));
            storyName.setTextColor(getResources().getColor(R.color.text_color_black));
            author.setTextColor(getResources().getColor(R.color.text_color_black));
            content.setTextColor(getResources().getColor(R.color.text_color_black));
            updatedAt.setTextColor(getResources().getColor(R.color.text_color_black));
            numberOfChapter.setTextColor(getResources().getColor(R.color.text_color_black));
        }
        else{
            container.setBackgroundColor(getResources().getColor(R.color.background_color_black));
            storyName.setTextColor(getResources().getColor(R.color.text_color_white));
            author.setTextColor(getResources().getColor(R.color.text_color_white));
            content.setTextColor(getResources().getColor(R.color.text_color_white));
            updatedAt.setTextColor(getResources().getColor(R.color.text_color_white));
            numberOfChapter.setTextColor(getResources().getColor(R.color.text_color_white));

        }


    }

    @Override
    public void onBackPressed(){
        supportFinishAfterTransition();
    }

    private void SingleChoice(final String[] listChapter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn chương");
        builder.setItems(listChapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(),"Đã chọn: " +
                        listChapter[which], Toast.LENGTH_LONG)
                        .show();
                Intent readIntent = new Intent(getBaseContext(),ActivityReading.class);
                readIntent.putExtra(Utility.NAME,storyName.getText().toString());
                readIntent.putExtra(Utility.CONTENT, which + 1); // Chapter number
                readIntent.putExtra(Utility.RAW_LINK_CHAPTER, getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER, 0) + which);
                startActivity(readIntent);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onResume(){
        if (startReading.getContentDescription() != null) {
            if (startReading.getContentDescription().equals("clicked")) {
                stopService(new Intent(this, MusicPlayService.class));
            }
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int textSize = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_text_size_list),"13"));
        int color = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_theme_color_list),"0"));

        if (color == 0){
            //White
            container.setBackgroundColor(getResources().getColor(R.color.cover_background));
            storyName.setTextColor(getResources().getColor(R.color.text_color_black));
            author.setTextColor(getResources().getColor(R.color.text_color_black));
            content.setTextColor(getResources().getColor(R.color.text_color_black));
            updatedAt.setTextColor(getResources().getColor(R.color.text_color_black));
            numberOfChapter.setTextColor(getResources().getColor(R.color.text_color_black));

        }
        else{
            container.setBackgroundColor(getResources().getColor(R.color.background_color_black));
            storyName.setTextColor(getResources().getColor(R.color.text_color_white));
            author.setTextColor(getResources().getColor(R.color.text_color_white));
            content.setTextColor(getResources().getColor(R.color.text_color_white));
            updatedAt.setTextColor(getResources().getColor(R.color.text_color_white));
            numberOfChapter.setTextColor(getResources().getColor(R.color.text_color_white));

        }

        super.onResume();
    }
}
