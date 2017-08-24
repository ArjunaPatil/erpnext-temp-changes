package com.example.vin.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by vin on 31/01/2017.
 */


public class adapter_user_list_hierarchy_for_dialog extends ArrayAdapter<POJO_User_S> {
    public Context _context;
    public adapter_user_list_hierarchy_for_dialog(Context context, int resource, List<POJO_User_S> POJO_User_S) {
        super(context, resource, POJO_User_S);
        this._context=context;
        try {
        } catch (Exception ex) {
            //Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        try {

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.adapter_user_list_hirarchy_for_dialog, parent, false);
        }

        final POJO_User_S POJO_User_S = getItem(position);

        if (POJO_User_S != null) {

            TextView tvname = (TextView) v.findViewById(R.id.name);
            //TextView head = (TextView) v.findViewById(R.id.head);
            TextView sub = (TextView) v.findViewById(R.id.sub);

            //head.setText(User.getFull_name());
            sub.setText(POJO_User_S.getFirst_name()+" "+POJO_User_S.getLast_name()+"("+POJO_User_S.getDesignation()+")");

            tvname.setText(POJO_User_S.getName());
        }

        return v;
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return v;
        }
    }
}
