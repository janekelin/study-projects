package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //initializing the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onSearch(View view){
        EditText search = (EditText) findViewById(R.id.choose_city);
        String city = search.getText().toString();
        String searchSelection = "CITY = '" + city + "';";
        ListView searchResults = (ListView) findViewById(R.id.search_results);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.query("EVENT", new String[] {"_id", "NAME"}, searchSelection, null, null, null, null);
            CursorAdapter searchAdapter =
                    new SimpleCursorAdapter(SearchActivity.this,
                            android.R.layout.simple_list_item_1, cursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            searchResults.setAdapter(searchAdapter);


        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Today is not your day, Joking. The database is just temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        searchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, EventDetailsActivity.class);
                intent.putExtra(EventDetailsActivity.EXTRA_EVENTID, (int) id);
                startActivity(intent);
            }
        });

    }
}
