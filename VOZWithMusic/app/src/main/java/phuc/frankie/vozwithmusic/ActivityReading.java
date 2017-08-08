package phuc.frankie.vozwithmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import phuc.frankie.vozwithmusic.Utility.Utility;

public class ActivityReading extends AppCompatActivity {
    TextView chapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        chapter =(TextView) findViewById(R.id.chapter);
        chapter.setText(Utility.readRawTextFile(this,R.raw.vebmnn1));
    }
}
