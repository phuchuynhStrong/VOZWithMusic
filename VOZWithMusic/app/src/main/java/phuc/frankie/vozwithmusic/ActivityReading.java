package phuc.frankie.vozwithmusic;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import phuc.frankie.vozwithmusic.Utility.Utility;

public class ActivityReading extends AppCompatActivity {
    TextView chapter,tvTitle,tvChapter;
    Button nextChapter, prevChapter, home;
    ConstraintLayout mainContainer;
    AppCompatActivity ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ref = this;
        setContentView(R.layout.activity_reading);
        mainContainer = (ConstraintLayout) findViewById(R.id.atvt_reading_main_container);
        chapter =(TextView) findViewById(R.id.chapter);
        tvTitle = (TextView) findViewById(R.id.atvt_reading_tv_title);
        tvChapter = (TextView) findViewById(R.id.atvt_reading_tv_chapter);
        nextChapter = (Button) findViewById(R.id.atvt_reading_next_btn);
        prevChapter = (Button) findViewById(R.id.atvt_reading_pre_btn);
        home = (Button) findViewById(R.id.atvt_reading_home_btn);



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(ref,MusicPlayService.class));
                startActivity(new Intent(ref,ActivityMain.class));
                finish();
            }
        });
        tvTitle.setText(getIntent().getStringExtra(Utility.NAME));
        tvChapter.setText("Chương: " + getIntent().getIntExtra(Utility.CONTENT,0));
        chapter.setText(Utility.readRawTextFile(this,getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER,0)));

            nextChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent readIntent = new Intent(getBaseContext(),ActivityReading.class);
                    readIntent.putExtra(Utility.NAME,getIntent().getStringExtra(Utility.NAME));
                    readIntent.putExtra(Utility.CONTENT,getIntent().getIntExtra(Utility.CONTENT,0) + 1); // Chapter number
                    readIntent.putExtra(Utility.RAW_LINK_CHAPTER,getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER,0) + 1);
                    startActivity(readIntent);
                    ref.overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left);
                    finish();


                }
            });
        if (getIntent().getIntExtra(Utility.CONTENT,0) == 1){
            prevChapter.setEnabled(false);
        }
        else {
            prevChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent readIntent = new Intent(getBaseContext(),ActivityReading.class);
                    readIntent.putExtra(Utility.NAME,getIntent().getStringExtra(Utility.NAME));
                    readIntent.putExtra(Utility.CONTENT,getIntent().getIntExtra(Utility.CONTENT,0) - 1); // Chapter number
                    readIntent.putExtra(Utility.RAW_LINK_CHAPTER,getIntent().getIntExtra(Utility.RAW_LINK_CHAPTER,0) - 1);
                    startActivity(readIntent);
                    ref.overridePendingTransition(R.transition.slide_in_left, R.transition.slide_out_right);
                    finish();
                }
            });

        }
    }

    @Override
    public void onResume(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int textSize = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_text_size_list),""));
        int color = Integer.parseInt(preferences.getString(getResources().getString(R.string.pref_key_theme_color_list),""));

        chapter.setTextSize(textSize);
        if (color == 0){
            //White
            mainContainer.setBackgroundColor(getResources().getColor(R.color.cover_background));
            chapter.setTextColor(getResources().getColor(R.color.text_color_black));
            tvTitle.setTextColor(getResources().getColor(R.color.text_color_black));
            tvChapter.setTextColor(getResources().getColor(R.color.text_color_black));
            nextChapter.setBackground(getResources().getDrawable(R.drawable.main_item_border));
            nextChapter.setTextColor(getResources().getColor(R.color.text_color_black));
            home.setBackground(getResources().getDrawable(R.drawable.main_item_border));
            home.setTextColor(getResources().getColor(R.color.text_color_black));
            prevChapter.setBackground(getResources().getDrawable(R.drawable.main_item_border));
            prevChapter.setTextColor(getResources().getColor(R.color.text_color_black));

        }
        else{
            mainContainer.setBackgroundColor(getResources().getColor(R.color.background_color_black));
            chapter.setTextColor(getResources().getColor(R.color.text_color_white));
            tvTitle.setTextColor(getResources().getColor(R.color.text_color_white));
            tvChapter.setTextColor(getResources().getColor(R.color.text_color_white));
            nextChapter.setBackground(getResources().getDrawable(R.drawable.main_item_border_white));
            nextChapter.setTextColor(getResources().getColor(R.color.text_color_white));
            home.setBackground(getResources().getDrawable(R.drawable.main_item_border_white));
            home.setTextColor(getResources().getColor(R.color.text_color_white));
            prevChapter.setBackground(getResources().getDrawable(R.drawable.main_item_border_white));
            prevChapter.setTextColor(getResources().getColor(R.color.text_color_white));
        }

        super.onResume();
    }

    @Override
    public void onDestroy(){

        super.onDestroy();
    }
}
