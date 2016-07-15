package avarizia.rengame.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DemonSlab on 6/1/16.
 */
public class Utils {
    public static String countries[] =
            {
                    "France",
                    "HRE",
                    "Milan",
                    "Venice",
                    "Naples",
                    "Genoa",
                    "Florence"
            };

    public static String provinces[] =
            {
                    "North Corsica",
                    "South Corsica"
            };

    public static String cities[] =
            {
                    "Florence",
                    "Avignon"
            };

    public static String seas[] =
            {

            };

    public static String statuses[] = {"Buy", "Disband", "N/A"};

    public static View.OnClickListener adjustScreen(final Activity getActivity) {
            return new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            //Adjust screen when typing
                            getActivity.getWindow().setSoftInputMode(
                                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    }
            };
    }

    public static String[] genLabels(String str, int size) {
        String labels[] = new String[size];
        for (int i=0; i<size; i++){
            labels[i] = str+(i+1);
        }
        return labels;
    }

}
