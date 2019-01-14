package h15ianka.inl2.ik1095.du.se.humanecologynetwork;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_EVENTID = "eventID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //basic installation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        //initializing the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //populating the data fields with information about relevant event

        int eventId = (Integer)getIntent().getExtras().get(EXTRA_EVENTID);
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);

        try {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("EVENT",
                    new String[] {"NAME", "DATE", "TIME", "CITY", "ADDRESS", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVOURITE"},
                    "_id = ?",
                    new String[] {Integer.toString(eventId)},
                    null, null,null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String dateText = cursor.getString(1);
                String timeText = cursor.getString(2);
                String cityText = cursor.getString(3);
                String addressText = cursor.getString(4);
                String descriptionText = cursor.getString(5);
                int photoId = cursor.getInt(6);
                boolean isFavourite = (cursor.getInt(7) == 1);

                EditText name = (EditText)findViewById(R.id.title);
                name.setText(nameText);

                EditText date = (EditText)findViewById(R.id.date);
                date.setText(dateText);

                EditText time = (EditText)findViewById(R.id.time);
                time.setText(timeText);

                EditText city = (EditText)findViewById(R.id.city);
                city.setText(cityText);

                EditText address = (EditText)findViewById(R.id.address);
                address.setText(addressText);

                EditText description = (EditText)findViewById(R.id.description);
                description.setText(descriptionText);

                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                CheckBox favourite = (CheckBox)findViewById(R.id.favorite);
                favourite.setChecked(isFavourite);

                cursor.close();
            }

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "The database doesn't like you anymore. Joking. It is just temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void onUpdateEvent(View view){

        EditText inputTitle = (EditText) findViewById(R.id.title);
        String title = inputTitle.getText().toString();
        EditText inputDate = (EditText) findViewById(R.id.date);
        String date = inputDate.getText().toString();
        EditText inputTime = (EditText) findViewById(R.id.time);
        String time = inputTime.getText().toString();
        EditText inputCity = (EditText) findViewById(R.id.city);
        String city = inputCity.getText().toString();
        EditText inputAddress = (EditText) findViewById(R.id.address);
        String address = inputAddress.getText().toString();
        EditText inputDescription = (EditText) findViewById(R.id.description);
        String description = inputDescription.getText().toString();

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
            int eventId = (Integer) getIntent().getExtras().get(EXTRA_EVENTID);
            db.update("EVENT", eventValues, "_id = ?", new String[]{Integer.toString(eventId)});

            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "No jokes. The database is temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        //Create and show a confirmation message
        CharSequence text = "You have updated the event!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //Show the newly created event in the list of events
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);


    }

    public void onDeleteEvent(View view){
        SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
        try {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            int eventId = (Integer) getIntent().getExtras().get(EXTRA_EVENTID);
            db.delete("EVENT","_id = ?", new String[]{Integer.toString(eventId)});

            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Oops. The database is temporarily unavailable.", Toast.LENGTH_LONG);
            toast.show();
        }

        //Create and show a confirmation message
        CharSequence text = "You have deleted the event!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //Show the newly created event in the list of events
        Intent intent = new Intent(this, EventListActivity.class);
        startActivity(intent);

    }


    //experimental use of asynctasks when the event is marked as favourite
    public void onFavouriteClicked(View view){

        int eventId = (Integer) getIntent().getExtras().get(EXTRA_EVENTID);
        new UpdateEventTask().execute(eventId);
    }

    private class UpdateEventTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues eventValues;
        protected void onPreExecute() {
            CheckBox favourite = (CheckBox) findViewById(R.id.favorite);
            eventValues = new ContentValues();
            eventValues.put("FAVOURITE", favourite.isChecked());
        }

        protected Boolean doInBackground(Integer... events) {
            int eventId = events[0];

            SQLiteOpenHelper databaseHelper =
                    new DatabaseHelper(EventDetailsActivity.this);
            try {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                db.update("EVENT", eventValues,
                        "_id = ?", new String[]{Integer.toString(eventId)});
                db.close();
                return true;

            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {

            if (!success) {
                Toast toast = Toast.makeText(EventDetailsActivity.this,
                        "The database is tired and wants to sleep. Joking. It is just temporarily unavailable.", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }


}

