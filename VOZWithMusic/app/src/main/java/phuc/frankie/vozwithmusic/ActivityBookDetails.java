package phuc.frankie.vozwithmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import phuc.frankie.vozwithmusic.Utility.Utility;

public class ActivityBookDetails extends AppCompatActivity {


    ImageView cover;
    TextView storyName,author,content,updatedAt;
    RelativeLayout container;
    TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title_book_details);
        cover = (ImageView) findViewById(R.id.story_cover_activity_book_details);
        storyName = (TextView) findViewById(R.id.story_name);
        author = (TextView) findViewById(R.id.story_author);
        content = (TextView) findViewById(R.id.story_content);
        updatedAt = (TextView) findViewById(R.id.story_update_day);
        container = (RelativeLayout) findViewById(R.id.details_container_activity_book_details);

        toolbarTitle.setText(getIntent().getStringExtra(Utility.NAME));
        cover.setImageResource(getIntent().getIntExtra(Utility.COVER,0));
        storyName.setText(getIntent().getStringExtra(Utility.NAME));
        author.setText(getIntent().getStringExtra(Utility.AUTHOR));
        content.setText(getIntent().getStringExtra(Utility.CONTENT));
        updatedAt.setText("Cập nhật mới nhất ngày: " + getIntent().getStringExtra(Utility.UPDATEDAT));
    }
}
