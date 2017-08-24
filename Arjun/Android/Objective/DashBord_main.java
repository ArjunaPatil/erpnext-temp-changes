        fragment_DashBoard.OnFragmentInteractionListener,
        fragment_doctor_call.OnFragmentInteractionListener,
        fragment_doctor_call_new.OnFragmentInteractionListener,
        fragment_chemist_call.OnFragmentInteractionListener,
        fragment_chemist_call_new.OnFragmentInteractionListener,
        fragment_booking_call.OnFragmentInteractionListener,
        fragment_booking_call_new.OnFragmentInteractionListener,
        fragment_User_Detail.OnFragmentInteractionListener,
        fragment_Doctor_List.OnFragmentInteractionListener,
        fragment_doctor_master_insert.OnFragmentInteractionListener,
        fragment_objective_list.OnFragmentInteractionListener,
        fragment_Patch_List.OnFragmentInteractionListener,
        fragment_patch_master_insert.OnFragmentInteractionListener,
        fragment_Chemist_List.OnFragmentInteractionListener,
        fragment_chemist_master_insert.OnFragmentInteractionListener,
        fragment_HeadquarterWise_Stockiest_List.OnFragmentInteractionListener,
        fragment_Stockiest_Users_List.OnFragmentInteractionListener,
        fragment_stockiest_users_master_insert.OnFragmentInteractionListener,
        fragment_Hierarchy_Users_List.OnFragmentInteractionListener,
        fragment_Report_View.OnFragmentInteractionListener,
        fragment_Hierarchy_Headquarter_List.OnFragmentInteractionListener
        
     private void displaySelectedScreen(int itemId) {
        try {

            //creating fragment object
            Fragment fragment = null;

            //initializing the fragment object which is selected
            switch (itemId) {

                case R.id.nav_home:
                    fragment = new fragment_DashBoard();
                    break;

                case R.id.nav_my_objective:

                    Bundle bundle = new Bundle();
                    bundle.putString("nav_bar", "nav_bar");

                    FragmentManager fm = getSupportFragmentManager();

                    popup_todays_objective_new_DialogFragment editNameDialogFragment = popup_todays_objective_new_DialogFragment.newInstance("Some Title");
                    editNameDialogFragment.setArguments(bundle);
                    editNameDialogFragment.show(fm, "fragment_edit_name");
                    //show_dialog_today_objective();
                    break;

                case R.id.nav_my_profile:
                    fragment = new fragment_User_Detail();
                    break;

                case R.id.nav_doctor_call:
                    fragment = new fragment_doctor_call();
                    break;

                case R.id.nav_chemist_call:
                    fragment = new fragment_chemist_call();
                    break;

                case R.id.nav_campaign_booking:
                    fragment = new fragment_booking_call();
                    break;
                case R.id.nav_reports:
                    //fragment = new fragment_Chemist_List();
                    break;
                case R.id.nav_doctor_list:
                    fragment = new fragment_Doctor_List();
                    break;
                case R.id.nav_patch_list:
                    fragment = new fragment_Patch_List();
                    break;
                case R.id.nav_chemist_list:
                    fragment = new fragment_Chemist_List();
                    break;
                case R.id.nav_stockist_list:
                    fragment = new fragment_HeadquarterWise_Stockiest_List();
                    //fragment = new fragment_Stockiest_Users_List();
                    break;
                case R.id.nav_hierarchy_list:
                    fragment = new fragment_Hierarchy_Users_List();
                    //fragment = new fragment_Report_View();
                    break;
                case R.id.nav_headquarter_list:
                    fragment = new fragment_Hierarchy_Headquarter_List();
                    break;
            /*case nav_Logout:
                SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = app_preferences.edit();
                editor.putString("status", "0");
                editor.putString("email", "");
                editor.putString("password", "");

                editor.commit();
              *//*  i
                if (task2.isCancelled() == false) {
                    task2.cancel(true);
                }*//*
                Intent k = new Intent(DashBord_main.this, Login.class);
                startActivity(k);
                break;*/
            }

            //replacing the fragment
            if (fragment != null) {
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack("ss");
                ft.commit();
            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception ex) {
            //Log.i("Error Is:", ex.getMessage().toString());
        }
    }
    
    
    public void Load_BackgroundData() {
        try {
            SharedPreferences app_preferences = PreferenceManager.getDefaultSharedPreferences(this);

            task_app_evrsion = new Async_Class_Load_AppVersions_in_Realm(this, true, false, false);
            task_app_evrsion.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            task_user = new Async_Class_Load_User_in_Realm(null, this, true, false, false);
            task_user.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        } catch (Exception ex) {
            //Log.i("Error Is:", ex.getMessage().toString());
        }
    }
