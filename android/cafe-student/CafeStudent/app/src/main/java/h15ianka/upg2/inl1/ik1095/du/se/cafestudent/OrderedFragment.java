package h15ianka.upg2.inl1.ik1095.du.se.cafestudent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class OrderedFragment extends Fragment {
    TextView textView;

    public OrderedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_ordered, container, false);

        Intent intent = new Intent(getActivity(), OrderHistoryActivity.class);


        startActivity(intent);
        return view;
    }

    public void setText(String text){

    }


}
