package com.example.vin.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

import io.realm.Realm;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.vin.myapplication.DashBord_main.task_app_evrsion;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_doctor_master_insert#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_doctor_master_insert extends Fragment implements doctor_degree_FragmentDialog.EditDoctorDegreeDialogListener,
        doctor_specialization_FragmentDialog.EditDoctorSpecializationDialogListener,
        doctor_type_FragmentDialog.EditDoctorTypeDialogListener,
        doctor_patch_FragmentDialog.EditDoctorInsertPacthDialogListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Realm mRealm;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView tv_user;
    TextView tv_doc_id;
    TextView tv_user_full_name;

    private OnFragmentInteractionListener mListener;
    RestService restService;
    Button btn_save_doctor;
    EditText txt_doctor_name;
    //TextView tv_patch_name;
    TextView tv_headquarter;//////////////////

    EditText txt_hospital_name;
    EditText txt_reg_no;
    EditText txt_address;
    EditText txt_city;
    EditText txt_pin_code;
    EditText txt_per_mobile;
    EditText txt_per_phone;
    EditText txt_email;


    LinearLayout select_patch;
    LinearLayout select_patch_1;
    TextView select_patch_2;
    TextView name_select_patch;
    ImageButton select_patch_3;

    LinearLayout select_degree;
    LinearLayout select_degree_1;
    TextView select_degree_2;
    TextView name_select_degree;
    ImageButton select_degree_3;


    LinearLayout select_doctor_specialization;
    LinearLayout select_doctor_specialization_1;
    TextView select_doctor_specialization_2;
    TextView name_select_doctor_specialization;
    ImageButton select_doctor_specialization_3;

    LinearLayout select_doctor_type;
    LinearLayout select_doctor_type_1;
    TextView select_doctor_type_2;
    TextView name_select_doctor_type;
    ImageButton select_doctor_type_3;


    /*tv_user;tv_doc_id
    EditText edit_doctor_name;
    TextView txt_patch_name;
    EditText edit_hospital_name;
    EditText edit_reg_no;
    EditText edit_address;
    EditText edit_city;
    EditText edit_pin_code;
    EditText edit_per_mobile;
    EditText edit_per_phone;
    EditText edit_email;*/

    CheckBox actirab_tab;
    CheckBox actirab_d_cap;
    CheckBox actirab_l_cap;
    CheckBox empower_od_tab;
    CheckBox STAND_SP_TAB;
    CheckBox STAR_T_TAB;
    CheckBox glucolyst_g1_tab;
    CheckBox lycorest_tab;
    CheckBox lycort_1ml_inj;
    CheckBox regain_xt_tab;
    CheckBox lycorest_60ml_susp;
    CheckBox lycolic_10ml_drops;
    CheckBox stand_mf_60ml_susp;
    CheckBox ten_n_30ml_syrup;
    CheckBox wego_gel_20_mg;
    CheckBox wego_gel_30_mg;
    CheckBox trygesic_tab;

    //Spinner spinner_degree;
    //Spinner spinner_doctor_specialization;
    //Spinner spinner_doctor_type;

    /*
    TextView txt_emp_code;
    TextView txt_emp_name;
    TextView txt_doc_id;
    Button btn_add_doc;
    Button btn_delete_patch;
    LinearLayout linear_layout_doc;
    Button btn_add_update_doc;*/
    Bundle bundle;

    private ProgressDialog pDialog;

    public fragment_doctor_master_insert() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_insert_patch_master_1.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_doctor_master_insert newInstance(String param1, String param2) {
        fragment_doctor_master_insert fragment = new fragment_doctor_master_insert();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctor_master_insert, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFinishDoctorInsertPacthDialog(String id, String PatchName) {
        try {
            TextView select_patch_2 = (TextView) getView().findViewById(R.id.select_patch_2);
            select_patch_2.setText(PatchName.toString());

            TextView name_select_patch = (TextView) getView().findViewById(R.id.name_select_patch);
            name_select_patch.setText(id.toString());
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinishDoctorDegreeDialog(String id, String DoctorDegree) {
        try {
            TextView select_degree_2 = (TextView) getView().findViewById(R.id.select_degree_2);
            select_degree_2.setText(DoctorDegree.toString());

            TextView name_select_degree = (TextView) getView().findViewById(R.id.name_select_degree);
            name_select_degree.setText(id.toString());
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinishDoctorSpecializationDialog(String id, String DoctorSpecialization) {
        try {
            TextView select_doctor_specialization_2 = (TextView) getView().findViewById(R.id.select_doctor_specialization_2);
            select_doctor_specialization_2.setText(DoctorSpecialization.toString());

            TextView name_select_doctor_specialization = (TextView) getView().findViewById(R.id.name_select_doctor_specialization);
            name_select_doctor_specialization.setText(id.toString());
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFinishDoctorTypeDialog(String id, String DoctorType) {
        try {
            TextView select_doctor_type_2 = (TextView) getView().findViewById(R.id.select_doctor_type_2);
            select_doctor_type_2.setText(DoctorType.toString());

            TextView name_select_doctor_type = (TextView) getView().findViewById(R.id.name_select_doctor_type);
            name_select_doctor_type.setText(id.toString());
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void onStart() {
        try {
            super.onStart();
            pDialog = new ProgressDialog(getContext());

            init_controls();


            select_patch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_patch();

                }
            });
            select_patch_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_patch();

                }
            });
            select_patch_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_patch();

                }
            });
            select_patch_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_patch();

                }
            });

            /////////////////////
            select_degree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_degree();

                }
            });
            select_degree_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_degree();

                }
            });
            select_degree_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_degree();

                }
            });
            select_degree_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_degree();

                }
            });

            ///////////////////////////////////////
            select_doctor_specialization.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_specialization();

                }
            });
            select_doctor_specialization_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_specialization();

                }
            });
            select_doctor_specialization_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_specialization();

                }
            });
            select_doctor_specialization_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_specialization();

                }
            });

            /////////////////////////////////////////
            select_doctor_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_type();

                }
            });
            select_doctor_type_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_type();
                    //show_dialog_for_select_doctor_type();

                }
            });
            select_doctor_type_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_type();
                    //show_dialog_for_select_doctor_type();

                }
            });
            select_doctor_type_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_select_doctor_type();
                    //show_dialog_for_select_doctor_type();

                }
            });
            ///////////////////////////////////////////////////////


            CHECK_ABM_RBM_ZBM_SM();
            //bind_spinners();

            // btn_add_doc.setText("SAVE");
            bundle = this.getArguments();
            if (bundle != null) {
                ((DashBord_main) getActivity()).setActionBarTitle("UPDATE DOCTOR");
                btn_save_doctor.setText("UPDATE");
                edit_fill();

            } else {
                ((DashBord_main) getActivity()).setActionBarTitle("ADD DOCTOR");
                btn_save_doctor.setText("SAVE");
                final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

                tv_user.setText(app_preferences.getString("name", "default"));
                tv_user_full_name.setText(app_preferences.getString("first_name", "-") + " " + app_preferences.getString("middle_name", "-") + " " + app_preferences.getString("last_name", "-"));
                //tv_headquarter.setText(app_preferences.getString("headquarter", "default"));
            }

            btn_save_doctor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        POJO_Doctor_Master POJO = new POJO_Doctor_Master();

                        final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        //POJO.setPatch();
                        POJO.setReg_no(txt_reg_no.getText().toString());
                        POJO.setCity(txt_city.getText().toString());
                        POJO.setPer_phone(txt_per_phone.getText().toString());
                        POJO.setEmail(txt_email.getText().toString());
                        POJO.setDoctor_name(txt_doctor_name.getText().toString());

                        POJO.setPatch(name_select_patch.getText().toString());///
                        POJO.setPatch_name(select_patch_2.getText().toString());///

                        POJO.setHospital_name(txt_hospital_name.getText().toString());

                        POJO.setAddress(txt_address.getText().toString());

                        POJO.setPin_code(txt_pin_code.getText().toString());
                        POJO.setPer_mobile(txt_per_mobile.getText().toString());

                        POJO.setActirab_tab((actirab_tab.isChecked() == true) ? 1 : 0);
                        POJO.setActirab_d_cap((actirab_d_cap.isChecked() == true) ? 1 : 0);
                        POJO.setActirab_l_cap((actirab_l_cap.isChecked() == true) ? 1 : 0);
                        POJO.setEmpower_od_tab((empower_od_tab.isChecked() == true) ? 1 : 0);
                        POJO.setStand_sp_tab((STAND_SP_TAB.isChecked() == true) ? 1 : 0);
                        POJO.setStart_t_tab((STAR_T_TAB.isChecked() == true) ? 1 : 0);
                        POJO.setGlucolyst_g1_tab((glucolyst_g1_tab.isChecked() == true) ? 1 : 0);
                        POJO.setLycorest_tab((lycorest_tab.isChecked() == true) ? 1 : 0);
                        POJO.setLycort_1ml_inj((lycort_1ml_inj.isChecked() == true) ? 1 : 0);
                        POJO.setRegain_xt_tab((regain_xt_tab.isChecked() == true) ? 1 : 0);
                        POJO.setLycorest_60ml_susp((lycorest_60ml_susp.isChecked() == true) ? 1 : 0);
                        POJO.setLycolic_10ml_drops((lycolic_10ml_drops.isChecked() == true) ? 1 : 0);
                        POJO.setStand_mf_60ml_susp((stand_mf_60ml_susp.isChecked() == true) ? 1 : 0);
                        POJO.setTen_on_30ml((ten_n_30ml_syrup.isChecked() == true) ? 1 : 0);
                        POJO.setWego_gel_20_mg((wego_gel_20_mg.isChecked() == true) ? 1 : 0);
                        POJO.setWego_gel_30_mg((wego_gel_30_mg.isChecked() == true) ? 1 : 0);
                        POJO.setTrygesic_tab((trygesic_tab.isChecked() == true) ? 1 : 0);

                        POJO.setDegree(select_degree_2.getText().toString());
                        POJO.setDoctor_specialization(select_doctor_specialization_2.getText().toString());
                        POJO.setDoctor_type(select_doctor_type_2.getText().toString());

                        //POJO.setDegree(spinner_degree.getSelectedItem().toString());
                        //POJO.setDoctor_specialization(spinner_doctor_specialization.getSelectedItem().toString());
                        //POJO.setDoctor_type(spinner_doctor_type.getSelectedItem().toString());




                    /*POJO.setUser(app_preferences.getString("name", "default"));
                    POJO.setUser_name(app_preferences.getString("name", "default"));

                    POJO.setZone(app_preferences.getString("zone", "default"));
                    POJO.setArea(app_preferences.getString("area", "default"));
                    POJO.setRegion(app_preferences.getString("region", "default"));
                    POJO.setHq(app_preferences.getString("headquarter", "default"));*/
                        //POJO.setCore_of_tbm(1);
                        POJO.setActive(1);
                        //POJO.setEmployee_code("EMP/0107");
                        //POJO.setEmployee_name("RITESH DIWAN");

                        //  pDialog.show();*/

                        if (Validation(POJO) == true) {
                            ///bundle.remove("btn_save_visibility");
                            ///bundle.putString("btn_save_visibility", "");
                            if (bundle != null) {

                                POJO.setName((tv_doc_id.getText().toString() == null) ? "" : tv_doc_id.getText().toString());
                                update_doctor(POJO);
                                ///bundle.putString("name", tv_doc_id.getText().toString());
                            } else {

                                POJO.setName("");
                                POJO.setUser(app_preferences.getString("name", "default"));
                                //POJO.setUser_name(app_preferences.getString("name", "default"));
                                POJO.setUser_name(tv_user_full_name.getText().toString());
                                POJO.setZone(app_preferences.getString("zone", "default"));
                                POJO.setArea(app_preferences.getString("area", "default"));
                                POJO.setRegion(app_preferences.getString("region", "default"));
                                POJO.setHq(app_preferences.getString("headquarter", "default"));

                                insert_doctor(POJO);
                            }

                        } else {
                            //  pDialog.hide();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void init_controls() {
        try {

            select_degree = (LinearLayout) getView().findViewById(R.id.select_degree);
            select_degree_1 = (LinearLayout) getView().findViewById(R.id.select_degree_1);
            select_degree_3 = (ImageButton) getView().findViewById(R.id.select_degree_3);
            select_degree_2 = (TextView) getView().findViewById(R.id.select_degree_2);
            name_select_degree = (TextView) getView().findViewById(R.id.name_select_degree);

            select_doctor_specialization = (LinearLayout) getView().findViewById(R.id.select_doctor_specialization);
            select_doctor_specialization_1 = (LinearLayout) getView().findViewById(R.id.select_doctor_specialization_1);
            select_doctor_specialization_3 = (ImageButton) getView().findViewById(R.id.select_doctor_specialization_3);
            select_doctor_specialization_2 = (TextView) getView().findViewById(R.id.select_doctor_specialization_2);
            name_select_doctor_specialization = (TextView) getView().findViewById(R.id.name_select_doctor_specialization);

            select_doctor_type = (LinearLayout) getView().findViewById(R.id.select_doctor_type);
            select_doctor_type_1 = (LinearLayout) getView().findViewById(R.id.select_doctor_type_1);
            select_doctor_type_3 = (ImageButton) getView().findViewById(R.id.select_doctor_type_3);
            select_doctor_type_2 = (TextView) getView().findViewById(R.id.select_doctor_type_2);
            name_select_doctor_type = (TextView) getView().findViewById(R.id.name_select_doctor_type);


            select_patch = (LinearLayout) getView().findViewById(R.id.select_patch);
            select_patch_1 = (LinearLayout) getView().findViewById(R.id.select_patch_1);
            select_patch_3 = (ImageButton) getView().findViewById(R.id.select_patch_3);
            select_patch_2 = (TextView) getView().findViewById(R.id.select_patch_2);
            name_select_patch = (TextView) getView().findViewById(R.id.name_select_patch);


            //   linear_layout_doc = (LinearLayout) getView().findViewById(R.id.linear_layout_doc);
            txt_doctor_name = (EditText) getView().findViewById(R.id.txt_doctor_name);
            txt_doctor_name.setSingleLine(true);

            //tv_headquarter= (TextView) getView().findViewById(R.id.tv_headquarter);

            //tv_patch_name = (TextView) getView().findViewById(R.id.tv_patch_name);
            txt_hospital_name = (EditText) getView().findViewById(R.id.txt_hospital_name);
            txt_hospital_name.setSingleLine(true);
            txt_reg_no = (EditText) getView().findViewById(R.id.txt_reg_no);
            txt_reg_no.setSingleLine(true);
            txt_address = (EditText) getView().findViewById(R.id.txt_address);
            txt_address.setSingleLine(true);
            txt_city = (EditText) getView().findViewById(R.id.txt_city);
            txt_city.setSingleLine(true);
            txt_pin_code = (EditText) getView().findViewById(R.id.txt_pin_code);
            txt_pin_code.setSingleLine(true);
            txt_per_mobile = (EditText) getView().findViewById(R.id.txt_per_mobile);
            txt_per_mobile.setSingleLine(true);
            txt_per_phone = (EditText) getView().findViewById(R.id.txt_per_phone);
            txt_per_phone.setSingleLine(true);
            txt_email = (EditText) getView().findViewById(R.id.txt_email);
            txt_email.setSingleLine(true);

            actirab_tab = (CheckBox) getView().findViewById(R.id.actirab_tab);
            actirab_d_cap = (CheckBox) getView().findViewById(R.id.actirab_d_cap);
            actirab_l_cap = (CheckBox) getView().findViewById(R.id.actirab_l_cap);
            empower_od_tab = (CheckBox) getView().findViewById(R.id.empower_od_tab);
            STAND_SP_TAB = (CheckBox) getView().findViewById(R.id.STAND_SP_TAB);
            STAR_T_TAB = (CheckBox) getView().findViewById(R.id.STAR_T_TAB);
            glucolyst_g1_tab = (CheckBox) getView().findViewById(R.id.glucolyst_g1_tab);
            lycorest_tab = (CheckBox) getView().findViewById(R.id.lycorest_tab);
            lycort_1ml_inj = (CheckBox) getView().findViewById(R.id.lycort_1ml_inj);
            regain_xt_tab = (CheckBox) getView().findViewById(R.id.regain_xt_tab);
            lycorest_60ml_susp = (CheckBox) getView().findViewById(R.id.lycorest_60ml_susp);
            lycolic_10ml_drops = (CheckBox) getView().findViewById(R.id.lycolic_10ml_drops);
            stand_mf_60ml_susp = (CheckBox) getView().findViewById(R.id.stand_mf_60ml_susp);
            ten_n_30ml_syrup = (CheckBox) getView().findViewById(R.id.ten_n_30ml_syrup);
            wego_gel_20_mg = (CheckBox) getView().findViewById(R.id.wego_gel_20_mg);
            wego_gel_30_mg = (CheckBox) getView().findViewById(R.id.wego_gel_30_mg);
            trygesic_tab = (CheckBox) getView().findViewById(R.id.trygesic_tab);
            //spinner_degree = (Spinner) getView().findViewById(R.id.spinner_degree);
            //spinner_doctor_specialization = (Spinner) getView().findViewById(R.id.spinner_doctor_specialization);
            //spinner_doctor_type = (Spinner) getView().findViewById(R.id.spinner_doctor_type);
            btn_save_doctor = (Button) getView().findViewById(R.id.btn_save_doctor);
            ///btn_add_doc = (Button) getView().findViewById(R.id.btn_add_doc);
            /// btn_delete_patch = (Button) getView().findViewById(R.id.btn_delete_patch);
            //  btn_add_update_doc = (Button) getView().findViewById(R.id.btn_add_update_doc);
            tv_doc_id = (TextView) getView().findViewById(R.id.tv_doc_id);
            tv_user = (TextView) getView().findViewById(R.id.tv_user);
            tv_user_full_name = (TextView) getView().findViewById(R.id.tv_user_full_name);
            ///txt_emp_name = (TextView) getView().findViewById(R.id.txt_emp_name);

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void edit_fill() {
        try {

            Bundle bundle = this.getArguments();

            String name = bundle.getString("name");
            tv_doc_id.setText(bundle.getString("name"));


            pDialog.show();
            //Thread.sleep(sleep_wait);
            restService = new RestService();
            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sid = app_preferences.getString("sid", "default");

            JSONArray jsonArray = new JSONArray();

            jsonArray.put("name");
            jsonArray.put("wego_gel_30_mg");
            jsonArray.put("stand_sp_tab");
            jsonArray.put("creation");
            jsonArray.put("doctor_name");
            jsonArray.put("patch");
            jsonArray.put("patch_name");
            jsonArray.put("actirab_l_cap");
            jsonArray.put("owner");
            jsonArray.put("hospital_name");

            jsonArray.put("empower_od_tab");
            jsonArray.put("city");
            jsonArray.put("lycorest_60ml_susp");
            jsonArray.put("modified_by");
            jsonArray.put("zone");
            jsonArray.put("area");
            jsonArray.put("stand_mf_60ml_susp");
            //jsonArray.put("employee_code");///
            jsonArray.put("reg_no");
            jsonArray.put("per_mobile");
            jsonArray.put("lycorest_tab");
            jsonArray.put("actirab_tab");
            jsonArray.put("lycort_1ml_inj");
            jsonArray.put("docstatus");
            jsonArray.put("doctor_specialization");
            jsonArray.put("email");
            jsonArray.put("doctor_type");
            jsonArray.put("per_phone");
            jsonArray.put("degree");
            jsonArray.put("lycolic_10ml_drops");
            jsonArray.put("hq");
            jsonArray.put("latitude");
            jsonArray.put("start_t_tab");
            jsonArray.put("regain_xt_tab");
//            jsonArray.put("employee_name");////
            jsonArray.put("actirab_d_cap");
            jsonArray.put("ten_on_30ml");
            jsonArray.put("pin_code");
            jsonArray.put("trygesic_tab");
            jsonArray.put("idx");
            jsonArray.put("region");
            jsonArray.put("modified");
            jsonArray.put("longitude");
            jsonArray.put("wego_gel_20_mg");
            jsonArray.put("glucolyst_g1_tab");
            jsonArray.put("address");
            jsonArray.put("active");
            jsonArray.put("user");
            jsonArray.put("user_name");
//            jsonArray.put("approve");////
//            jsonArray.put("approve_note");////
//            jsonArray.put("approve_by");/////


            //  JSONObject ofilter= new JSONObject();
            JSONArray Filters = new JSONArray();
            JSONArray Filter1 = new JSONArray();
            JSONArray Filter2 = new JSONArray();


            Filter1.put("Doctor Master");
            Filter1.put("name");
            Filter1.put("=");
            Filter1.put(tv_doc_id.getText());

            Filters.put(Filter1);


            //"modified desc", 0, 1,

            restService.getService().getDoctor_Update_data(sid, jsonArray, Filters, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {
                    try {
                        pDialog.hide();
                        // ListView lv = (ListView) findViewById(R.id.listView);
                        JsonObject j1 = jsonElement.getAsJsonObject();
                        JsonArray j2 = j1.getAsJsonArray("data");

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<POJO_Doctor_Master>>() {
                        }.getType();
                        List<POJO_Doctor_Master> POJO = gson.fromJson(j2, type);

                        mRealm = Realm.getDefaultInstance();
                        mRealm.beginTransaction();
                        mRealm.copyToRealmOrUpdate(POJO);
                        mRealm.commitTransaction();
                        mRealm.close();
                        if (tv_doc_id.getText().toString().length() > 0) {
                            Bind__data(tv_doc_id.getText().toString());
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void failure(RetrofitError error) {

                    pDialog.hide();

                    String msg = error.getMessage();

                    if (msg.equals("403 FORBIDDEN")) {

                        //SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());    //status make 0 so checkoffline cannot work
                        //SharedPreferences.Editor editor = app_preferences.edit();
                        //editor.putString("status", "0");
                        //editor.commit();

                        //Intent k = new Intent(getContext(), Login.class); //got ot login activity
                        //getContext().startActivity(k);
                        Toast.makeText(getContext(), "Error...", Toast.LENGTH_SHORT).show();
                    } else if (msg.contains("139.59.63.181")) {
                        Toast.makeText(getContext(), "PLEASE CHECK INTERNET CONNECT", Toast.LENGTH_SHORT).show();
                    }

                    if (task_app_evrsion != null) {
                        task_app_evrsion.cancel(true);
                    }
                }
            });

        } catch (Exception ex)

        {
            pDialog.hide();
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void Bind__data(String name) {
        try {
            Realm mRealm = Realm.getDefaultInstance();

            final POJO_Doctor_Master POJO = mRealm.where(POJO_Doctor_Master.class).equalTo("name", name).findFirst();

            tv_doc_id.setText(POJO.getName());

            txt_doctor_name.setText(POJO.getDoctor_name());


            name_select_patch.setText(POJO.getPatch()==null?" SELECT PATCH":POJO.getPatch());
            select_patch_2.setText(POJO.getPatch_name()==null?" SELECT PATCH":POJO.getPatch_name());
            /*if (!(POJO.getPatch() == null)) {
                if (!(POJO.getPatch().toString() == "")) {
                    name_select_patch.setText(POJO.getPatch().toString());
                }
            }

            if (!(POJO.getPatch_name() == null)) {
                if (!(POJO.getPatch_name().toString() == "")) {
                    select_patch_2.setText(POJO.getPatch_name().toString());
                }
            }*/

            //TextView select_patch_2 = (TextView) getView().findViewById(R.id.select_patch_2);
            ////name_select_patch.setText(POJO.getPatch().toString());
            ////select_patch_2.setText(POJO.getPatch_name().toString());
            //tv_patch_name.setText(POJO.getPatch());

            txt_hospital_name.setText(POJO.getHospital_name()==null?"":POJO.getHospital_name());
            txt_reg_no.setText(POJO.getReg_no()==null?"":POJO.getReg_no());
            txt_address.setText(POJO.getAddress()==null?"":POJO.getAddress());
            txt_city.setText(POJO.getCity()==null?"":POJO.getCity());
            txt_pin_code.setText(POJO.getPin_code()==null?"":POJO.getPin_code());
            txt_per_mobile.setText(POJO.getPer_mobile()==null?"":POJO.getPer_mobile());
            txt_per_phone.setText(POJO.getPer_phone()==null?"":POJO.getPer_phone());
            txt_email.setText(POJO.getEmail()==null?"":POJO.getEmail());

            actirab_tab.setChecked(POJO.getActirab_tab().equals(1));
            actirab_d_cap.setChecked(POJO.getActirab_d_cap().equals(1));
            actirab_l_cap.setChecked(POJO.getActirab_l_cap().equals(1));
            empower_od_tab.setChecked(POJO.getEmpower_od_tab().equals(1));
            STAND_SP_TAB.setChecked(POJO.getStand_sp_tab().equals(1));
            STAR_T_TAB.setChecked(POJO.getStart_t_tab().equals(1));
            glucolyst_g1_tab.setChecked(POJO.getGlucolyst_g1_tab().equals(1));
            lycorest_tab.setChecked(POJO.getLycorest_tab().equals(1));
            lycort_1ml_inj.setChecked(POJO.getLycort_1ml_inj().equals(1));
            regain_xt_tab.setChecked(POJO.getRegain_xt_tab().equals(1));
            lycorest_60ml_susp.setChecked(POJO.getLycorest_60ml_susp().equals(1));
            lycolic_10ml_drops.setChecked(POJO.getLycolic_10ml_drops().equals(1));
            stand_mf_60ml_susp.setChecked(POJO.getStand_mf_60ml_susp().equals(1));
            ten_n_30ml_syrup.setChecked(POJO.getTen_on_30ml().equals(1));
            wego_gel_20_mg.setChecked(POJO.getWego_gel_20_mg().equals(1));
            wego_gel_30_mg.setChecked(POJO.getWego_gel_30_mg().equals(1));
            trygesic_tab.setChecked(POJO.getTrygesic_tab().equals(1));

            select_degree_2.setText(POJO.getDegree()==null?" SELECT DEGREE":POJO.getDegree());
            /*if (!(POJO.getDegree() == null)) {
                if (!(POJO.getDegree().toString() == "")) {
                    select_degree_2.setText(POJO.getDegree().toString());
                }
            }*/
            select_doctor_specialization_2.setText(POJO.getDoctor_specialization()==null?" SELECT SPECIALIZATION":POJO.getDoctor_specialization());
            /*if (!(POJO.getDoctor_specialization() == null)) {
                if (!(POJO.getDoctor_specialization().toString() == "")) {
                    select_doctor_specialization_2.setText(POJO.getDoctor_specialization().toString());
                }
            }*/
            select_doctor_type_2.setText(POJO.getDoctor_type()==null?" SELECT TYPE":POJO.getDoctor_type());
            /*if (!(POJO.getDoctor_type() == null)) {
                if (!(POJO.getDoctor_type().toString() == "")) {
                    select_doctor_type_2.setText(POJO.getDoctor_type().toString());
                }
            }*/

            tv_user.setText(POJO.getUser());
            tv_user_full_name.setText(POJO.getUser_name());

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void update_doctor(POJO_Doctor_Master POJO) {
        try {
            pDialog.show();
            restService = new RestService();
            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sid = app_preferences.getString("sid", "default");
            String name = bundle.getString("name").toString();

            restService.getService().doctor_master_update(sid, POJO, name, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {
                    try {
                        update_doctor_master_class(jsonElement);
                        pDialog.hide();

                        Toast.makeText(getContext(), "DOCTOR UPDATE SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Fragment frag = new fragment_Doctor_List();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, frag);
                        //frag.setArguments(bundle);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);

                        ft.commit();
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void failure(RetrofitError error) {
                    pDialog.hide();
                    Toast.makeText(getContext(), "PLEASE ENTER VALID DATA", Toast.LENGTH_SHORT).show();
                    String msg = error.getMessage();
                    if (msg.equals("403 FORBIDDEN")) {
                        Toast.makeText(getContext(), "Error...", Toast.LENGTH_SHORT).show();
                        // onsession_failure();
                    }


                }
            });
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void insert_doctor(POJO_Doctor_Master POJO) {
        try {
            pDialog.show();
            restService = new RestService();
            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sid = app_preferences.getString("sid", "default");
            POJO.setLatitude("0");
            POJO.setLongitude("0");

            restService.getService().doctor_master_insert(sid, POJO, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {
                    try {
                        update_doctor_master_class(jsonElement);
                        pDialog.hide();

                        Toast.makeText(getContext(), "DOCTOR ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                        Fragment frag = new fragment_Doctor_List();
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, frag);
                        ;
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);
                        ft.commit();
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void failure(RetrofitError error) {
                    pDialog.hide();

                    String msg = error.getMessage();
                    if (msg.equals("403 FORBIDDEN")) {
                        Toast.makeText(getContext(), "Error...", Toast.LENGTH_SHORT).show();
                        //onsession_failure();
                    } else if (msg.contains("139.59.63.181")) {
                        Toast.makeText(getContext(), "PLEASE CHECK INTERNET CONNECT", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void update_doctor_master_class(JsonElement jsonElement) {
        try {
            JsonObject j1 = jsonElement.getAsJsonObject();

            JsonElement j2 = j1.getAsJsonObject("data");

            Gson gson = new Gson();
            Type type = new TypeToken<POJO_Doctor_Master>() {
            }.getType();
            POJO_Doctor_Master POJO = gson.fromJson(j2, type);

            mRealm = Realm.getDefaultInstance();
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(POJO);
            mRealm.commitTransaction();
            mRealm.close();

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean Validation(POJO_Doctor_Master POJO) {
        try {
            if (POJO.getDoctor_name().toString().trim().length() < 5) {
                Toast.makeText(getContext(), "DOCTOR NAME IS NOT EMPTY AND LENGHT MUST BE GRATER THAN 5", Toast.LENGTH_SHORT).show();
                return false;
            } else if (POJO.getHospital_name().toString().trim().length() < 3) {
                Toast.makeText(getContext(), "HOSPITAL NAME IS NOT EMPTY AND LENGHT MUST BE GRATER THAN 3 ", Toast.LENGTH_SHORT).show();
                return false;
            } else if (POJO.getAddress().toString().trim().length() < 5) {
                Toast.makeText(getContext(), "ADDRESS IS NOT EMPTY AND LENGHT MUST BE GRATER THAN 5 ", Toast.LENGTH_SHORT).show();
                return false;

            } else if (POJO.getCity().toString().trim().length() < 2) {
                Toast.makeText(getContext(), "CITY IS NOT EMPTY AND LENGHT MUST BE GRATER THAN 2 ", Toast.LENGTH_SHORT).show();
                return false;
            } else if (POJO.getPin_code().toString().trim().length() < 5) {
                Toast.makeText(getContext(), "PINCODE IS NOT EMPTY  ", Toast.LENGTH_SHORT).show();
                return false;
            } else if (POJO.getPer_mobile().toString().trim().length() < 9) {
                Toast.makeText(getContext(), "MOBILE NUMBER IS NOT EMPTY  AND LENGHT MUST BE GRATER THAN 9 ", Toast.LENGTH_SHORT).show();
                return false;

            } /*else if (POJO.getEmployee_code().toString().trim().length() < 1) {
                Toast.makeText(getContext(), "NO EMPLOYEE WITH THIS PATCH ..YOU ARE NOT ASSIGNED EMPLOYEE CODE YET" +
                        "PLEASE CONATACT LYSTEN GLOBAL IT SUPPORT ", Toast.LENGTH_SHORT).show();
                return false;
            }*/ else
                return true;
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void CHECK_ABM_RBM_ZBM_SM() {
        try {
            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String designation = (app_preferences.getString("designation", "default"));
            if (designation.equals("ABM") || designation.equals("RBM") || designation.equals("ZBM") || designation.equals("SM") || designation.equals("CRM")) {
                btn_save_doctor.setVisibility(View.INVISIBLE);
                /*btn_add_doc.setVisibility(View.INVISIBLE);
                btn_delete_patch.setVisibility(View.INVISIBLE);*/
            }
            if (designation.equals("TBM")) {
                btn_save_doctor.setVisibility(View.VISIBLE);
                /*btn_add_doc.setVisibility(View.VISIBLE);
                btn_delete_patch.setVisibility(View.VISIBLE);*/
            } else {
                btn_save_doctor.setVisibility(View.INVISIBLE);
                /*btn_add_doc.setVisibility(View.INVISIBLE);
                btn_delete_patch.setVisibility(View.INVISIBLE);*/
            }
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    ////////////////////////////////////////////////////////////

    private void show_dialog_for_select_degree() {

        try {
            doctor_degree_FragmentDialog dialog = doctor_degree_FragmentDialog.newInstance("Hello world");

            //dialog.setView(layout);

            dialog.setTargetFragment(fragment_doctor_master_insert.this, 300);
            dialog.show(getFragmentManager(), "fdf");

        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void show_dialog_for_select_doctor_specialization() {
        try {

            doctor_specialization_FragmentDialog dialog = doctor_specialization_FragmentDialog.newInstance("Hello world");

            //dialog.setView(layout);

            dialog.setTargetFragment(fragment_doctor_master_insert.this, 300);
            dialog.show(getFragmentManager(), "fdf");
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void show_dialog_for_select_doctor_type() {

        try {
            doctor_type_FragmentDialog dialog = doctor_type_FragmentDialog.newInstance("Hello world");

            //dialog.setView(layout);

            dialog.setTargetFragment(fragment_doctor_master_insert.this, 300);
            dialog.show(getFragmentManager(), "fdf");
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void show_dialog_for_select_patch() {

        try {
            doctor_patch_FragmentDialog dialog = doctor_patch_FragmentDialog.newInstance("Hello world");

            //dialog.setView(layout);

            dialog.setTargetFragment(fragment_doctor_master_insert.this, 300);
            dialog.show(getFragmentManager(), "fdf");
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
