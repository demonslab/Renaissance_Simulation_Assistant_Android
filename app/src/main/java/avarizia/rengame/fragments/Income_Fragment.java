package avarizia.rengame.fragments;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import avarizia.rengame.R;

/**
 * Created by DemonSlab on 5/30/16.
 */
public class Income_Fragment extends Fragment{

    private static View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_income, container, false);
        this.rootView = rootView;

        final ArrayList<EditText> entries = new ArrayList<>();
        //Get layout objects
        final Spinner select_season = (Spinner) rootView.findViewById(R.id.input_season);
        entries.add((EditText) rootView.findViewById(R.id.input_sea));
        entries.add((EditText) rootView.findViewById(R.id.input_prov));
        entries.add((EditText) rootView.findViewById(R.id.input_cities));
        entries.add((EditText) rootView.findViewById(R.id.input_remaining));
        entries.add((EditText) rootView.findViewById(R.id.input_variable));
        entries.add((EditText) rootView.findViewById(R.id.input_extra));
        entries.add((EditText) rootView.findViewById(R.id.input_tax));
        final TextView get_total= (TextView) rootView.findViewById(R.id.input_total);
        Button submit = (Button) rootView.findViewById(R.id.submit);

        final LinearLayout collect_layout = (LinearLayout) rootView.findViewById(R.id.collect);

        //Fill Season Drop Down Menu
        String[] seasons = {"Spring", "Summer", "Fall"};
        ArrayAdapter<String> seasonAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, seasons);
        select_season.setAdapter(seasonAdapter);

        //Listener for season list
        select_season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    collect_layout.setVisibility(View.VISIBLE);
                } else
                    collect_layout.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer sum = 0;
                for (int i = 0; i<entries.size(); i++) {
                    if (i > 2 || select_season.getSelectedItemPosition() == 0) {
                        String val = entries.get(i).getText().toString();
                        Integer num = val.isEmpty() ? 0 : Integer.parseInt(val);
                        sum += (i < entries.size()-1) ? num : -num;
                    }
                }
                get_total.setText(sum.toString());
            }
        });

        return rootView;
    }

}