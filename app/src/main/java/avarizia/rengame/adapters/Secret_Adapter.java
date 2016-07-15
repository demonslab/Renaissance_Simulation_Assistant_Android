package avarizia.rengame.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import avarizia.rengame.R;

/**
 * Created by DemonSlab on 6/18/16.
 */
public class Secret_Adapter extends BaseAdapter {

    private ArrayList<String> items;
    private static LayoutInflater inflater;
    private Context context;
    private Secret_Adapter thisAdapter;
    private Button addItem;

    public Secret_Adapter(Context context, ArrayList<String> itemList, Button addItem){
        this.items = itemList;
        this.context = context;
        this.thisAdapter = this;
        this.addItem = addItem;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class rowObject{
        TextView string;
        ImageButton remove;

        public rowObject(){}
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        rowObject rowItem = new rowObject();
        View rowView = inflater.inflate(R.layout.str_rem_list_layout, null);

        rowItem.string = (TextView) rowView.findViewById(R.id.sec_str);
        rowItem.remove = (ImageButton) rowView.findViewById(R.id.sec_rem);

        rowItem.string.setText(items.get(position));

        rowItem.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                thisAdapter.notifyDataSetChanged();
                addItem.setEnabled(true);           //In case a limit was set on the number of items
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rowView;
    }

}
