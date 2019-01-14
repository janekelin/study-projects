package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HEN_main";
    private static final int DB_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //when created first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    //after an upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    //helper function to smooth out populating of the database
    private static void insertEvent(SQLiteDatabase db, String name, String date, String time, String city, String address, String description,
                                    int resourceId, boolean isFavourite) {
        ContentValues eventValues = new ContentValues();
        eventValues.put("NAME", name);
        eventValues.put("DATE", date);
        eventValues.put("TIME", time);
        eventValues.put("CITY", city);
        eventValues.put("ADDRESS", address);
        eventValues.put("DESCRIPTION", description);
        eventValues.put("IMAGE_RESOURCE_ID", resourceId);
        eventValues.put("FAVOURITE", isFavourite);
        db.insert("EVENT", null, eventValues);
    }

    //generic function for database populating
    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < 1) {

            db.execSQL("CREATE TABLE EVENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "DATE TEXT, "
                    + "TIME TEXT, "
                    + "CITY TEXT, "
                    + "ADDRESS TEXT, "
                    + "DESCRIPTION TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER, "
                    + "FAVOURITE NUMERIC);");

            insertEvent(db, "Malmö for justice", "2019-08-25", "14:00-17:00",
                    "Malmö", "Folkets Park",
                    "This International Degrowth Conference brings together academics, activists, artists and other interested persons who question the logic of growth as a model for our societiies.",
                    R.drawable.malmo, false);

            insertEvent(db, "Jordnära Permaculture Festival", "2019-06-08 - 2019-06-10", "all day",
                    "Jordnära Knästorp", "Knästorpsvägen",
                    "This festival is for everyone who wants to learn more about sustainability, zero-waste, reusing, reducing and upcycling!",
                    R.drawable.knastorp, false);

            insertEvent(db, "Cultural Political Week", "2019-05-16 - 2019-05-27", "all day",
                    "Lund", "Smålands Nation, Kastanjegatan 7",
                    "Loads of different clubs, pubs, lectures, workshops to culture your mind and educate you.",
                    R.drawable.lund, false);
        }



    }


}
