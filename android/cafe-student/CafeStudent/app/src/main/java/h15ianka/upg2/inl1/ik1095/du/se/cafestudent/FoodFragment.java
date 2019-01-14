package h15ianka.upg2.inl1.ik1095.du.se.cafestudent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

public class FoodFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView foodRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_food, container, false);

        String[] sandwichesNames = new String[h15ianka.upg2.inl1.ik1095.du.se.cafestudent.Food.sandwiches.length];

        for (int i = 0; i < sandwichesNames.length; i++) {
            sandwichesNames[i] = h15ianka.upg2.inl1.ik1095.du.se.cafestudent.Food.sandwiches[i].getName();
        }

        int[] sandwichesImages = new int[h15ianka.upg2.inl1.ik1095.du.se.cafestudent.Food.sandwiches.length];

        for (int i = 0; i < sandwichesImages.length; i++) {
            sandwichesImages[i] = h15ianka.upg2.inl1.ik1095.du.se.cafestudent.Food.sandwiches[i].getImageResourceId();
        }

        h15ianka.upg2.inl1.ik1095.du.se.cafestudent.CaptionedImagesAdapter adapter = new h15ianka.upg2.inl1.ik1095.du.se.cafestudent.CaptionedImagesAdapter(sandwichesNames, sandwichesImages);

        foodRecycler.setAdapter(adapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);

        foodRecycler.setLayoutManager(layoutManager);

        return foodRecycler;

    }

}
