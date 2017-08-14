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
        stories.add(0,new Story(0,"Vẽ em bằng màu nỗi nhớ","Ve em bang mau noi nho","Thanh Tâm Phạm","04-08-2017", R.drawable.cover_vebmn,R.mipmap.ic_vebmnn,R.raw.content_vebmnn,42,Utility.TYPE_NOVEL,R.raw.vebmnn01));
        stories.add(1,new Story(1,"Ngày hôm qua đã từng - My Life","Ngay hom qua da tung - My Life","Nguyễn Mon","04-08-2017",R.drawable.cover_mylife,R.mipmap.ic_mylife,R.raw.content_mylife,117,Utility.TYPE_NOVEL,0));
        stories.add(2,new Story(2,"Ngày hôm qua đã từng - My Daisy","Ngay hom qua da tung - My Daisy","Nguyễn Mon","04-08-2017",R.drawable.cover_mydaisy,R.mipmap.ic_mydaisy,R.raw.content_mydaisy,85,Utility.TYPE_NOVEL,0));
        stories.add(3,new Story(3,"Con đường mang tên em","Con duong mang ten em","Cường Nguyễn","04-08-2017",R.drawable.cover_cdmte,R.mipmap.ic_cdmte,R.raw.content_mydaisy,130,Utility.TYPE_DIARY,0));
        stories.add(4,new Story(4,"Ranh giới","Rain8x","Ranh gioi","04-08-2017",R.drawable.cover_ranhgioi,R.mipmap.ic_ranhgioi,R.raw.content_ranhgioi,64,Utility.TYPE_NOVEL,0));
        stories.add(5,new Story(5,"Vị tình đầu","Vi tinh dau","huymanutd","04-08-2017",R.drawable.cover_vtd,R.mipmap.ic_vtd,R.raw.content_vtd,111,Utility.TYPE_REVIEW,R.raw.vtd01));
    };
    private static Database db;
    public static Database getInstance(){
        if (db == null){
            db = new Database();
        }
        return db;
    }

    public class Story{
        private String name,author,updateDay,value;
        String type;
        int id,cover,logo,numberOfChap;
        int rawShortContent;
        List<Integer> listRawChapter = new ArrayList<>();
        int firstChapter;
        public Story(int mId,String mName,String mValue,String mAuthor,String mUpdateDay, int mCover,int mLogo,int content,int defaultChapAmount,String mType,int mFC){
            id = mId;
            name = mName;
            value = mValue;
            author = mAuthor;
            updateDay = mUpdateDay;
            cover = mCover;
            logo = mLogo;
            rawShortContent = content;
            numberOfChap = defaultChapAmount;
            type = mType;
            firstChapter = mFC;
        }

        public String getName(){
            return name;
        }
        public String getValue() { return value; }
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
        public int getContent(){
            return rawShortContent;
        }
        public String getUpdateDay(){
            return updateDay;
        }
        public int getNumberOfChap(){
            return listRawChapter.size() == 0 ? numberOfChap : listRawChapter.size();
        }
        public String getType(){
            return type;
        }
        public int getFirstChapter() { return firstChapter;}
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

