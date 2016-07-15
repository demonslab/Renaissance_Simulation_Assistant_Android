package avarizia.rengame.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import avarizia.rengame.R;

/**
 * Created by DemonSlab on 5/30/16.
 */
public class Error_Fragment extends Fragment{
    private static View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.rootView = rootView;

        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText("There was an error.");

        return rootView;
    }
}
