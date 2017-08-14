package phuc.frankie.vozwithmusic.Utility;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by frank on 04/08/2017.
 */

public class Utility {
    public static final String COVER = "cover";
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";
    public static final String UPDATEDAT = "updatedAt";
    public static final String LIST_CHAPTER = "list_chapter";
    public static final String TYPE_NOVEL = "novel";
    public static final String TYPE_REVIEW = "review";
    public static final String TYPE_DIARY = "diary";
    public static final String TYPE_ALL = "all";
    public static final String RAW_LINK_CHAPTER = "raw_link_chapter";
    public static final String RAW_LINK_NEXT_CHAPTER = "raw_link_next_chapter";
    public static final String RAW_LINK_PREVIOUS_CHAPTER = "raw_link_previous_chapter";


    public static String readRawTextFile(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while (( line =buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();
    }
}
