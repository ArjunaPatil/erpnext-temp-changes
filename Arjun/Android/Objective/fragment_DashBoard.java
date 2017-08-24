public class fragment_DashBoard extends Fragment implements popup_todays_objective_new_DialogFragment.Today_Obj_after_save

@Override
    public void onStart() {
        try {
            super.onStart();

            int versionCode = BuildConfig.VERSION_CODE;
            String versionName = BuildConfig.VERSION_NAME;
            ((DashBord_main) getActivity()).setActionBarTitle("DASHBOARD");
            loaddate();
            load_events();

            get_todays_objective(edit_selected_date.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void loaddate() {
        try {
            edit_selected_date = (EditText) getView().findViewById(R.id.edit_selected_date);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            edit_selected_date.setText(sdf.format(date));
            edit_selected_date.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //  showDialog(999);
                    Toast.makeText(getContext(), "ca",
                            Toast.LENGTH_SHORT)
                            .show();

                    return false;
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
    
    public void load_events() {
        try {
            tv_todays_objective = (TextView) getView().findViewById(R.id.tv_todays_objective);
            ////Objective Section Start

            CustomImageButton btn_objective_add = (CustomImageButton) getView().findViewById(R.id.btn_objective_add);
            btn_objective_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_dialog_for_todays_objective();
                }
            });

            CustomImageButton btn_objective_list = (CustomImageButton) getView().findViewById(R.id.btn_objective_list);
            btn_objective_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        String Designation = app_preferences.getString("designation", "default");

                        if ((Designation.equals("ABM")) || (Designation.equals("RBM")) || (Designation.equals("CRM")) || (Designation.equals("ZBM")) || (Designation.equals("SM")) || ((Designation.equals("NBM")) || (Designation.equals("Head of Marketing and Sales")) || (Designation.equals("HR Manager")))) {

                            Fragment frag = new fragment_objective_list();
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, frag);

                            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                            ft.addToBackStack("fragment_objective_list");

                            ft.commit();

                        } else {
                            Toast.makeText(getContext(), "ACCESS DENIEDED...", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception ex) {
                        Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            ////Objective Section END
            
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

    }
    
    /*Section Start Objective Of the Date */
    @Override
    public void onFinish_Today_Obj_Save(String FlagOpr, String Today_Obj) {
        // Toast.makeText(getContext(), "Hi, " + inputText, Toast.LENGTH_SHORT).show();

        if (FlagOpr.equals("Y")) {
            Toast.makeText(getContext(), "Save Successfully...", Toast.LENGTH_SHORT).show();
            //TextView tv_todays_objective = (TextView) getView().findViewById(tv_todays_objective);
            get_todays_objective(edit_selected_date.getText().toString());
            //tv_todays_objective.setText(Today_Obj.toString());
        } else {
            Toast.makeText(getContext(), "PLEASE Try Again...", Toast.LENGTH_SHORT).show();
        }

        /*TextView name_doctor_of_TBM = (TextView) getView().findViewById(R.id.name_doctor_of_TBM);
        name_doctor_of_TBM.setText(id.toString());*/

    }

    private void show_dialog_for_todays_objective() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("nav_bar", "dash");

            popup_todays_objective_new_DialogFragment dialog = popup_todays_objective_new_DialogFragment.newInstance("Hello world");
            dialog.setArguments(bundle);
            //dialog.setView(layout);
            dialog.setTargetFragment(fragment_DashBoard.this, 300);
            dialog.show(getFragmentManager(), "fdf");
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void get_todays_objective(String dd) {
        try {

            restService = new RestService();
            final SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String sid = app_preferences.getString("sid", "default");
            String user_id = app_preferences.getString("name", "default");

            JSONArray jsonArray = new JSONArray();

            /*jsonArray.put("name");
            jsonArray.put("select_date");
            jsonArray.put("objective");*/


            //jsonArray.put("modified_by");
            //jsonArray.put("name");
            //jsonArray.put("creation");
            //jsonArray.put("modified");
            jsonArray.put("select_date");
            //jsonArray.put("idx");
            jsonArray.put("objective");
            jsonArray.put("user");
            //jsonArray.put("owner");
            //jsonArray.put("docstatus");
            jsonArray.put("user_name");

            JSONArray Filters = new JSONArray();
            JSONArray Filter1 = new JSONArray();
            JSONArray Filter2 = new JSONArray();

            /*Filter1.put("Objective");
            Filter1.put("owner");
            Filter1.put("=");
            Filter1.put(emp_email_id.getText());*/

            Filter1.put("Objective");
            Filter1.put("user");
            Filter1.put("=");
            Filter1.put(user_id);


            Filter2.put("Objective");
            Filter2.put("select_date");
            Filter2.put("=");
            Filter2.put(dd);


            Filters.put(Filter1);
            Filters.put(Filter2);


            restService.getService().getObjective(sid, jsonArray, Filters, new Callback<JsonElement>() {
                @Override
                public void success(JsonElement jsonElement, Response response) {

                    JsonObject j1 = jsonElement.getAsJsonObject();
                    JsonArray j22 = j1.getAsJsonArray("data");

                    if (j22.size() > 0) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<POJO_objective>>() {
                        }.getType();
                        List<POJO_objective> POJO = gson.fromJson(j22, type);

                        if (POJO.size() > 0) {

                            POJO_objective firstA = POJO.get(0);
                            tv_todays_objective.setText(firstA.getObjective().toString());
                        }
                    } else {

                    }

                }

                @Override
                public void failure(RetrofitError error) {

                    String msg = error.getMessage();

                    if (msg.equals("403 FORBIDDEN")) {

                        Intent k = new Intent(getContext(), Login.class); //got ot login activity
                        getContext().startActivity(k);

                    } else if (msg.contains("139.59.63.181")) {
                        Toast.makeText(getContext(), "PLEASE CHECK INTERNET CONNECT", Toast.LENGTH_SHORT).show();

                    }

                    if (task_app_evrsion != null) {
                        task_app_evrsion.cancel(true);
                    }

                    if (task_user != null) {
                        task_user.cancel(true);
                    }
                }
            });
        } catch (Exception ex) {

        }
    }

    /*Section End Objective Of the Date */
