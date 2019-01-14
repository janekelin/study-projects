package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.support.v7.app.ActionBar;
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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class EventListActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        //initializing the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //populating list of events from the database
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        ListView listEvents = (ListView) findViewById(R.id.list_events);

        try {
            db = databaseHelper.getReadableDatabase();
            cursor = db.query("EVENT",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            listEvents.setAdapter(listAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "The database ran away to Disneyland. Joking. It is just temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> listEvents,
                                            View itemView,
                                            int position,
                                            long id) {

                        Intent intent = new Intent(EventListActivity.this,
                                EventDetailsActivity.class);
                        intent.putExtra(EventDetailsActivity.EXTRA_EVENTID, (int) id);
                        startActivity(intent);
                    }
                };

        listEvents.setOnItemClickListener(itemClickListener);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}


