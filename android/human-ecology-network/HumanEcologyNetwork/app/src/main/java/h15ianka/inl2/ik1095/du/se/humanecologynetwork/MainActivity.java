package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favouritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupOptionsListView();
        setupFavouritesListView();
    }

    //populating menu options
    private void setupOptionsListView() {

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        switch(position){
                            case 0:
                                Intent intent1 = new Intent(MainActivity.this,
                                        EventListActivity.class);
                                startActivity(intent1);
                                break;
                            case 1:
                                Intent intent2 = new Intent(MainActivity.this,
                                        SearchActivity.class);
                                startActivity(intent2);
                                break;
                            case 2:
                                Intent intent3 = new Intent(MainActivity.this,
                                        AddEventActivity.class);
                                startActivity(intent3);
                                break;

                        }

                    }
                };

        ListView listView = (ListView) findViewById(R.id.main_menu);
        listView.setOnItemClickListener(itemClickListener);
    }

    //populating list of favourite events
    private void setupFavouritesListView() {

        ListView listFavourites = (ListView) findViewById(R.id.list_favourites);

        try{
            SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
            db = databaseHelper.getReadableDatabase();
            favouritesCursor = db.query("EVENT",
                    new String[] { "_id", "NAME"},
                    "FAVOURITE = 1",
                    null, null, null, null);
            CursorAdapter favouriteAdapter =
                    new SimpleCursorAdapter(MainActivity.this,
                            android.R.layout.simple_list_item_1, favouritesCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavourites.setAdapter(favouriteAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "The database went on vacation without a goodbye-note. Joking. It is just temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        listFavourites.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EventDetailsActivity.class);
                intent.putExtra(EventDetailsActivity.EXTRA_EVENTID, (int) id);
                startActivity(intent);
            }
        });
    }

    //so that list of favourites does not disappear when you rotate the device
    @Override
    public void onRestart() {
        super.onRestart();
        Cursor newCursor = db.query("EVENT",
                new String[] { "_id", "NAME"}, "FAVORITE = 1",
                null, null, null, null);
        ListView listFavourites = (ListView) findViewById(R.id.list_favourites);
        CursorAdapter adapter = (CursorAdapter) listFavourites.getAdapter();
        adapter.changeCursor(newCursor);
        favouritesCursor = newCursor;
    }

    //cleaning up
    @Override
    public void onDestroy(){
        super.onDestroy();
        favouritesCursor.close();
        db.close();
    }
}
