package h15ianka.upg2.inl1.ik1095.du.se.cafestudent;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //Tollbar is enriched with back button that leads to the Main Activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickOrderFood(View view) {

        //Gather data from input controllers and combine it in a string
        Spinner spinner_food = (Spinner) findViewById(R.id.choose_sandwich);
        String sandwich = String.valueOf(spinner_food.getSelectedItem());
        Spinner spinner_time = (Spinner) findViewById(R.id.choose_time);
        String time = String.valueOf(spinner_time.getSelectedItem());
        DatePicker datePicker = (DatePicker) findViewById(R.id.choose_day);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
        String yourOrder = "Du har beställt " + sandwich + " till " + time + " den " + date + ".";

        //Create and show a confirmation message
        CharSequence text = "Din beställning är mottagen!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //Pass the info about order in an intent to the main activity
        Intent intent = new Intent(this, OrderHistoryActivity.class);
        intent.putExtra("orderDetails", yourOrder);
        startActivity(intent);


    }
}
