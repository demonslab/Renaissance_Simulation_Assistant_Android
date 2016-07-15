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
 * Created by DemonSlab on 5/31/16.
 */
public class Action_Adapter extends BaseAdapter {

    private ArrayList<String> items;
    private static LayoutInflater inflater;
    private Context context;
    private Action_Adapter thisAdapter;
    private Button addItem;

    public Action_Adapter(Context context, ArrayList<String> itemList, Button addItem){
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
        TextView label;
        TextView move;
        TextView support;
        TextView purchase;
        ImageButton closeBtn;

        public rowObject(){}
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        rowObject rowItem = new rowObject();
        View rowView = inflater.inflate(R.layout.item_list_layout, null);

        rowItem.label = (TextView) rowView.findViewById(R.id.label);
        rowItem.move = (TextView) rowView.findViewById(R.id.move);
        rowItem.support = (TextView) rowView.findViewById(R.id.support);
        rowItem.purchase = (TextView) rowView.findViewById(R.id.purchase);
        rowItem.closeBtn = (ImageButton) rowView.findViewById(R.id.close);

        String vals[] = items.get(position).split("/,");
        rowItem.label.setText(vals[0]);
        rowItem.move.setText(vals[1]);
        rowItem.support.setText(vals[2]);
        rowItem.purchase.setText(vals[3]);

        rowItem.closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.remove(position);
                thisAdapter.notifyDataSetChanged();
                addItem.setEnabled(true);        //In case there was a limit to the number of items
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
