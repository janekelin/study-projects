package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //initializing the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onAddEvent(View view) {

        //Gather data from input controllers and put it in the database
        EditText inputTitle = (EditText) findViewById(R.id.add_title);
        String title = inputTitle.getText().toString();
        EditText inputCity = (EditText) findViewById(R.id.add_city);
        String city = inputCity.getText().toString();
        EditText inputAddress = (EditText) findViewById(R.id.add_address);
        String address = inputAddress.getText().toString();
        EditText inputDescription = (EditText) findViewById(R.id.add_description);
        String description = inputDescription.getText().toString();

        DatePicker datePicker = (DatePicker) findViewById(R.id.add_date);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

        TimePicker timePicker = (TimePicker) findViewById(R.id.add_time);
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();
        String time = Integer.toString(hour) + ":" + Integer.toString(min);

        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            ContentValues eventValues = new ContentValues();
            eventValues.put("NAME", title);
            eventValues.put("DATE", date);
            eventValues.put("TIME", time);
            eventValues.put("CITY", city);
            eventValues.put("ADDRESS", address);
            eventValues.put("DESCRIPTION", description);
            eventValues.put("IMAGE_RESOURCE_ID", R.drawable.add);
            eventValues.put("FAVOURITE", false);
            db.insert("EVENT", null, eventValues);

            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "This event was too good to be true. Joking. The database is temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        //Create and show a confirmation message
        CharSequence text = "You have added a new event!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //Show the newly created event in the list of events
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);


    }
}
