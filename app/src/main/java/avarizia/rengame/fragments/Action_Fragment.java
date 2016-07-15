package avarizia.rengame.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import java.util.ArrayList;

import avarizia.rengame.R;
import avarizia.rengame.activity.Utils;
import avarizia.rengame.adapters.Action_Adapter;

/**
 * Created by DemonSlab on 5/30/16.
 */
public class Action_Fragment extends Fragment{

    private static View rootView;
    private static ArrayList<String> armies;
    private static ArrayList<String> garrisons;
    private static ArrayList<String> fleets;
    private static ArrayList<String> mercs;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_action, container, false);
        this.rootView = rootView;

        TabHost tabHost = (TabHost) rootView.findViewById(R.id.tabHost);
        tabHost.setup();

        initArmyTab();
        initGarrisonTab();
        initFleetTab();
        initMercTab();

        createTab(tabHost, "Armies", R.id.armies);
        createTab(tabHost, "Garrisons", R.id.garrisons);
        createTab(tabHost, "Fleets", R.id.fleets);
        createTab(tabHost, "Mercs", R.id.mercs);

        return rootView;
    }

    private static void createTab(TabHost tabHost, String tag, int resource){
        View tabview = createTabView(tabHost.getContext(), tag);
        TabSpec tab = tabHost.newTabSpec(tag);
        tab.setIndicator(tabview);
        tab.setContent(resource);
        tabHost.addTab(tab);
    }

    private static View createTabView(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_view, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }


    private void initArmyTab(){
        final int numArmies = 12;
        armies = new ArrayList<>();

        final Button add = (Button) rootView.findViewById(R.id.add_army);
        final Action_Adapter adapter = new Action_Adapter(getContext(),armies, add);
        ListView list = (ListView) rootView.findViewById(R.id.armyView);
        list.setAdapter(adapter);

        final Spinner label = (Spinner) rootView.findViewById(R.id.input_army_label);
        final AutoCompleteTextView location = (AutoCompleteTextView) rootView.findViewById(R.id.input_army_location);
        final CheckBox siege = (CheckBox) rootView.findViewById(R.id.siege);
        final EditText support = (EditText) rootView.findViewById(R.id.input_army_support);
        final Spinner status = (Spinner) rootView.findViewById(R.id.input_army_status);

        final Button submit = (Button) rootView.findViewById(R.id.armySubmit);
        final Button clear= (Button) rootView.findViewById(R.id.armyClear);

        String labels[] = Utils.genLabels("A", numArmies);

        ArrayAdapter<String> labelAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, labels);
        label.setAdapter(labelAdapter);

        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.provinces);
        location.setAdapter(locationAdapter);

        ArrayAdapter<String> statusAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.statuses);
        status.setAdapter(statusAdapter);

        location.setOnClickListener(Utils.adjustScreen(getActivity()));
        support.setOnClickListener(Utils.adjustScreen(getActivity()));

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Item Format
                //  M: move, S: support, B: Bought, Disband, Neither
                //  "A## M:province  S:A##  B/D/N" [X]
                armies.add(
                    String.format("%s/,M-%s/,%s/,%c",
                            label.getSelectedItem().toString(),
                            location.getText().toString(),
                            (siege.isChecked()) ? "Siege" : "S-" + support.getText().toString(),
                            status.getSelectedItem().toString().charAt(0)
                        )
                );
                adapter.notifyDataSetChanged();

                if (armies.size() >= numArmies)
                    add.setEnabled(false);
            }
        });


        //add submit function here


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                armies.clear();
                adapter.notifyDataSetChanged();
                location.setText("");
                support.setText("");
            }
        });

    }

    private void initGarrisonTab(){
        final int numGars = 10;
        garrisons = new ArrayList<>();

        final Button add = (Button) rootView.findViewById(R.id.add_gar);
        final Action_Adapter adapter = new Action_Adapter(getContext(),garrisons, add);
        ListView list = (ListView) rootView.findViewById(R.id.garView);
        list.setAdapter(adapter);

        final Spinner label = (Spinner) rootView.findViewById(R.id.input_gar_label);
        final AutoCompleteTextView location = (AutoCompleteTextView) rootView.findViewById(R.id.input_gar_location);
        final CheckBox support = (CheckBox) rootView.findViewById(R.id.input_gar_support);
        final Spinner status = (Spinner) rootView.findViewById(R.id.input_gar_status);

        final Button submit = (Button) rootView.findViewById(R.id.garSubmit);
        final Button clear= (Button) rootView.findViewById(R.id.garClear);

        String labels[] = Utils.genLabels("G", numGars);
        ArrayAdapter<String> labelAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, labels);
        label.setAdapter(labelAdapter);

        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.cities);
        location.setAdapter(locationAdapter);

        ArrayAdapter<String> statusAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.statuses);
        status.setAdapter(statusAdapter);

        location.setOnClickListener(Utils.adjustScreen(getActivity()));

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Item Format
                //  M: move, S: support, B: Bought, Disband, Neither
                //  "A## M:province  S:A##  B/D/N" [X]
                garrisons.add(
                        String.format("%s/,M-%s/,S-%c/,%c",
                                label.getSelectedItem().toString(),
                                location.getText().toString(),
                                (support.isChecked()) ? 'Y' : 'N',
                                status.getSelectedItem().toString().charAt(0)
                        )
                );
                adapter.notifyDataSetChanged();

                if (garrisons.size() >= numGars)
                    add.setEnabled(false);
            }
        });

        //add submit function here


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garrisons.clear();
                adapter.notifyDataSetChanged();
                location.setText("");
            }
        });

    }

    private void initFleetTab(){
        final int numFleets = 12;
        fleets = new ArrayList<>();

        final Button add = (Button) rootView.findViewById(R.id.add_fleet);
        final Action_Adapter adapter = new Action_Adapter(getContext(),fleets, add);
        ListView list = (ListView) rootView.findViewById(R.id.fleetView);
        list.setAdapter(adapter);

        final Spinner label = (Spinner) rootView.findViewById(R.id.input_fleet_label);
        final AutoCompleteTextView location = (AutoCompleteTextView) rootView.findViewById(R.id.input_fleet_location);
        final CheckBox port = (CheckBox) rootView.findViewById(R.id.port);
        final EditText support = (EditText) rootView.findViewById(R.id.input_fleet_support);
        final Spinner status = (Spinner) rootView.findViewById(R.id.input_fleet_status);

        final Button submit = (Button) rootView.findViewById(R.id.fleetSubmit);
        final Button clear= (Button) rootView.findViewById(R.id.fleetClear);

        String labels[] = Utils.genLabels("F", numFleets);

        ArrayAdapter<String> labelAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, labels);
        label.setAdapter(labelAdapter);

        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.seas);
        location.setAdapter(locationAdapter);

        ArrayAdapter<String> statusAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.statuses);
        status.setAdapter(statusAdapter);

        location.setOnClickListener(Utils.adjustScreen(getActivity()));
        support.setOnClickListener(Utils.adjustScreen(getActivity()));

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Item Format
                //  M: move, S: support, B: Bought, Disband, Neither
                //  "A## M:province  S:A##  B/D/N" [X]
                fleets.add(
                        String.format("%s/,M-%s%s/,C-%s/,%c",
                                label.getSelectedItem().toString(),
                                location.getText().toString(),
                                (port.isChecked()) ? "+P" : "",
                                support.getText().toString(),
                                status.getSelectedItem().toString().charAt(0)
                        )
                );
                adapter.notifyDataSetChanged();

                if (fleets.size() >= numFleets)
                    add.setEnabled(false);
            }
        });

        //add submit function here


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fleets.clear();
                adapter.notifyDataSetChanged();
                location.setText("");
                support.setText("");
            }
        });
    }

    private void initMercTab(){
        final int numMercs = 10;
        mercs = new ArrayList<>();

        final Button add = (Button) rootView.findViewById(R.id.add_merc);
        final Action_Adapter adapter = new Action_Adapter(getContext(),mercs, add);
        ListView list = (ListView) rootView.findViewById(R.id.mercView);
        list.setAdapter(adapter);

        final Spinner label = (Spinner) rootView.findViewById(R.id.input_merc_label);
        final AutoCompleteTextView location = (AutoCompleteTextView) rootView.findViewById(R.id.input_merc_location);
        final EditText support = (EditText) rootView.findViewById(R.id.input_merc_support);
        final Spinner status = (Spinner) rootView.findViewById(R.id.input_merc_status);

        final Button submit = (Button) rootView.findViewById(R.id.mercSubmit);
        final Button clear= (Button) rootView.findViewById(R.id.mercClear);


        String labels[] = Utils.genLabels("M", numMercs+1);
        labels[numMercs] = "C";

        ArrayAdapter<String> labelAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, labels);
        label.setAdapter(labelAdapter);

        ArrayAdapter<String> locationAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.provinces);
        location.setAdapter(locationAdapter);

        ArrayAdapter<String> statusAdapter =
                new ArrayAdapter<String>(getContext(), R.layout.string_list_layout, Utils.statuses);
        status.setAdapter(statusAdapter);

        location.setOnClickListener(Utils.adjustScreen(getActivity()));
        support.setOnClickListener(Utils.adjustScreen(getActivity()));

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Item Format
                //  M: move, S: support, B: Bought, Disband, Neither
                //  "A## M:province  S:A##  B/D/N" [X]
                mercs.add(
                        String.format("%s/,M-%s/,S-%s/,%c",
                                label.getSelectedItem().toString(),
                                location.getText().toString(),
                                support.getText().toString(),
                                status.getSelectedItem().toString().charAt(0)
                        )
                );
                adapter.notifyDataSetChanged();

                if (mercs.size() >= numMercs+1)
                    add.setEnabled(false);
            }
        });

        //add submit function here


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mercs.clear();
                adapter.notifyDataSetChanged();
                location.setText("");
                support.setText("");
            }
        });
    }

}
