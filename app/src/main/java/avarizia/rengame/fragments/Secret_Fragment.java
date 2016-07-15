package avarizia.rengame.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import avarizia.rengame.R;
import avarizia.rengame.activity.Utils;
import avarizia.rengame.adapters.Secret_Adapter;

/**
 * Created by DemonSlab on 5/30/16.
 */
public class Secret_Fragment extends Fragment{
    private static View rootView;
    private static ArrayList<String> inputList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_secret, container, false);
        this.rootView = rootView;

        inputList = new ArrayList<>();
        ListView list = (ListView) rootView.findViewById(R.id.listView);
        final EditText input = (EditText) rootView.findViewById(R.id.editText);
        final Button add = (Button) rootView.findViewById(R.id.button);

        final Secret_Adapter adapter = new Secret_Adapter(getContext(), inputList, add);
        list.setAdapter(adapter);


        input.setOnClickListener(Utils.adjustScreen(getActivity()));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputList.add(input.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });


        final Button submit = (Button) rootView.findViewById(R.id.secSubmit);
        final Button clear = (Button) rootView.findViewById(R.id.secClear);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputList.clear();
                adapter.notifyDataSetChanged();
                input.setText("");
                add.setEnabled(true);
            }
        });

        return rootView;
    }
}
