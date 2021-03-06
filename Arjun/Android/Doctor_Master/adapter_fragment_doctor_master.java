package com.example.vin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;

/**
 * Created by vin on 31/01/2017.
 */
public class adapter_fragment_doctor_master extends ArrayAdapter<POJO_Doctor_Master> {
    private Realm mRealm;
    public Context _context;

    public adapter_fragment_doctor_master(Context context, int resource, List<POJO_Doctor_Master> POJO_Doctor_Master) {
        super(context, resource, POJO_Doctor_Master);
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

            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String designation = (app_preferences.getString("designation", "default"));

            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.adapter_docctor_master, parent, false);
            }

            POJO_Doctor_Master Doctor_Master = getItem(position);

            if (Doctor_Master != null) {


                TextView doctor_name = (TextView) v.findViewById(R.id.doctor_name);
                TextView tvname = (TextView) v.findViewById(R.id.name);
                TextView address = (TextView) v.findViewById(R.id.address);
                TextView city_name = (TextView) v.findViewById(R.id.city_name);
                TextView patch_name = (TextView) v.findViewById(R.id.patch_name);
                ///TextView employee_name = (TextView) v.findViewById(R.id.employee_name);
                //TextView doctor_code = (TextView) v.findViewById(R.id.doctor_code);
                TextView doctor_serial_no = (TextView) v.findViewById(R.id.doctor_serial_no);

                TextView tv_lbl_employee_name = (TextView) v.findViewById(R.id.tv_lbl_employee_name);
                TextView tv_employee_name = (TextView) v.findViewById(R.id.tv_employee_name);


                doctor_serial_no.setText((position + 1) + ". " + "DR.");
                //doctor_code.setText(Doctor_Master.getName() + " ]");
                doctor_name.setText(Doctor_Master.getDoctor_name());
                address.setText(Doctor_Master.getAddress());
                city_name.setText(Doctor_Master.getCity());
                patch_name.setText(Doctor_Master.getPatch_name()==""||Doctor_Master.getPatch_name()==null?"-":Doctor_Master.getPatch_name());
                ///employee_name.setText(Doctor_Master.getEmployee_code());

                if(designation.equals("TBM")||designation.equals("KBM"))
                {
                     tv_lbl_employee_name.setVisibility(View.GONE);
                     tv_employee_name.setVisibility(View.GONE);
                }
                else {
                    tv_lbl_employee_name.setVisibility(View.VISIBLE);
                    tv_employee_name.setVisibility(View.VISIBLE);
                    tv_employee_name.setText(Doctor_Master.getUser_name());
                }

                tvname.setText(Doctor_Master.getEmail());

                /*mRealm = Realm.getDefaultInstance();
                POJO_Patch_master POJO = mRealm.where(POJO_Patch_master.class).equalTo("name", Doctor_Master.getPatch()).findFirst();
                if (POJO != null) {
                    patch_name.setText(POJO.getPatch_name());
                } else {
                    patch_name.setText("-");
                }*/

            }

            return v;
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return v;
        }
    }
}
