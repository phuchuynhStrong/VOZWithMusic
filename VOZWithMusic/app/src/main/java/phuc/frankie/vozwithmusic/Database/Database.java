package phuc.frankie.vozwithmusic.Database;

import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import phuc.frankie.vozwithmusic.R;
import phuc.frankie.vozwithmusic.Utility.Utility;

/**
 * Created by frank on 04/08/2017.
 */

public class Database {

    private List<Story> stories = new ArrayList<Story>();
    private Database(){
        stories.add(0,new Story(0,"Vẽ em bằng màu nỗi nhớ","Thanh Tâm Phạm","04-08-2017", R.drawable.cover_vebmn,R.mipmap.ic_vebmnn));
        stories.add(1,new Story(1,"Ngày hôm qua đã từng - My Life","Nguyễn Mon","04-08-2017",R.drawable.cover_mylife,R.mipmap.ic_mylife));
        stories.add(2,new Story(2,"Ngày hôm qua đã từng - My Daisy","Nguyễn Mon","04-08-2017",R.drawable.cover_mydaisy,R.mipmap.ic_mydaisy));
        stories.add(3,new Story(3,"Con đường mang tên em","Cường Nguyễn","04-08-2017",R.drawable.cover_cdmte,R.mipmap.ic_cdmte));
        stories.add(4,new Story(4,"Ranh giới","Rain8x","04-08-2017",R.drawable.cover_ranhgioi,R.mipmap.ic_ranhgioi));
        stories.add(5,new Story(5,"Vị tình đầu","huymanutd","04-08-2017",R.drawable.cover_vtd,R.mipmap.ic_vtd));
    };
    private static Database db;
    public static Database getInstance(){
        if (db == null){
            db = new Database();
        }
        return db;
    }

    public class Story{
        private String name,author,updateDay;
        int id,cover,logo;
        int rawShortContent;
        List<Integer> listRawChapter = new ArrayList<>();
        public Story(int mId,String mName,String mAuthor,String mUpdateDay, int mCover,int mLogo){
            id = mId;
            name = mName;
            author = mAuthor;
            updateDay = mUpdateDay;
            cover = mCover;
            logo = mLogo;
        }

        public String getName(){
            return name;
        }
        public String getAuthor(){
            return author;
        }
        public int getId(){
            return id;
        }
        public int getCover(){
            return cover;
        }
        public int getLogo(){
            return logo;
        }
        public List<Integer> getListRawChapter(){
            return listRawChapter;
        }
        public String getContent(){
            return "";
        }
        public String getUpdateDay(){
            return updateDay;
        }
    }

    public Story getStory(int id){
        if (id < 0 || id >= stories.size()){
            return null;
        }
        return stories.get(id);
    }

    public List<Story> getListStories(){
        return stories;
    }
}

