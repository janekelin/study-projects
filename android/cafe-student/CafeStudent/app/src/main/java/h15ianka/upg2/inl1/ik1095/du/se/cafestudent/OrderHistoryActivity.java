package h15ianka.upg2.inl1.ik1095.du.se.cafestudent;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class OrderHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        //Tollbar hosts Back-arrow
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get intent from the OrderedFragment and show information about orders
        Intent intent = getIntent();
        String yourOrder = intent.getStringExtra("orderDetails");
        TextView textView = (TextView) findViewById(R.id.order_history);
        textView.setText(yourOrder);

    }
}
