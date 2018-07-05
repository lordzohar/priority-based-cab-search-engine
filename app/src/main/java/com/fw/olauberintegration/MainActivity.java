package com.fw.olauberintegration;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fw.olauberintegration.api.ApiRequestHandler;
import com.fw.olauberintegration.api.request.base.ErrorResponseData;
import com.fw.olauberintegration.api.request.base.Request;
import com.fw.olauberintegration.api.request.olarideestimate.OlaRideEstimateResponseData;
import com.fw.olauberintegration.api.request.uberrideestimate.UberRideEstimateResponseData;
import com.fw.olauberintegration.fragment.OlaRideFragment;
import com.fw.olauberintegration.fragment.UberRideFragment;
import com.fw.olauberintegration.model.OlaRideEstimate;
import com.fw.olauberintegration.model.UberRideEstimate;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import android.content.DialogInterface;
//import android.widget.RelativeLayout;
//import butterknife.OnClick;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    private FirebaseAuth firebaseAuth;
//    TextView count;
//    private Button buttonLogout;
    //olabutton,uberbutton;



//    @BindView(R.id.etPickLocation) AutoCompleteTextView mEtPickLocation;
//    @BindView(R.id.etDropLocation) AutoCompleteTextView mEtDropLocation;
    @BindView(R.id.sliding_tabs) TabLayout mSlidingTabLayout;
    @BindView(R.id.viewpager) ViewPager mRideViewPager;
    //@BindView(R.id.btnNext) Button nextbutton;

    protected GoogleApiClient mGoogleApiClient;
//    private PlaceAutocompleteAdapter mAdapter;
    private LatLng pickLatLng, dropLatLng;
    private String pickAdd,dropAdd;
    private List<OlaRideEstimate> olaRideEstimateList = new ArrayList<>();
    private List<UberRideEstimate> uberRideEstimateList = new ArrayList();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog progressDialog= ProgressDialog.show(MainActivity.this, "",
                "Loading. Please wait...", true);

        RelativeLayout rel= (RelativeLayout) findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) rel.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        ButterKnife.bind(this);
        ArrayList<LatLng> myList = (ArrayList<LatLng>) getIntent().getSerializableExtra("mylist");

        pickLatLng=  myList.get(0);
        dropLatLng=  myList.get(1);
        Bundle bundle = getIntent().getExtras();
        pickAdd = bundle.getString("pickup");
        dropAdd = bundle.getString("drop");

//        bundle.putParcelableArrayList("geolist", myList);
//        Fragment fragobj=new OlaRideFragment();
//        fragobj.setArguments(bundle);

        Log.d("The List is", String.valueOf(myList));
        firebaseAuth = FirebaseAuth.getInstance();


        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, login.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        TextView textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        //buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome "+user.getEmail());






        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0, this)
                .addApi(Places.GEO_DATA_API)
                .build();


        ApiRequestHandler.fetchAllOlaRideEstimate(pickLatLng, dropLatLng, "",new Request.RequestDelegate<OlaRideEstimateResponseData>() {
            @Override
            public void onSuccess(OlaRideEstimateResponseData result) {
                Toast.makeText(MainActivity.this, "Data Loaded.......", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                Log.e("Ola Estimate", result.getRideEstimateList().size()+"");

                olaRideEstimateList = result.getRideEstimateList();
                fetchUberEstimates();
            }



            @Override
            public void onError(ErrorResponseData errorResponse) {

            }

            @Override
            public void onFailed(Throwable t) {

            }
        });



        mRideViewPager.beginFakeDrag();
        mSlidingTabLayout.setupWithViewPager(mRideViewPager);




    }



    private void fetchUberEstimates(){

        Log.d("Uber pickup", String.valueOf(pickLatLng));
        Log.d("Uber pickup", String.valueOf(dropLatLng));
        ApiRequestHandler.fetchAllUberRideEstimate(pickLatLng, dropLatLng, new Request.RequestDelegate<UberRideEstimateResponseData>() {
            @Override
            public void onSuccess(UberRideEstimateResponseData result) {
                Log.e("Uber Estimate", result.getRideEstimateList().size()+"");
                uberRideEstimateList = result.getRideEstimateList();

                Log.d("The Uber List", String.valueOf(uberRideEstimateList));

                mRideViewPager.setAdapter(new RidePagerAdapter(getSupportFragmentManager(),
                        MainActivity.this));



            }

            @Override
            public void onError(ErrorResponseData errorResponse) {

            }

            @Override
            public void onFailed(Throwable t) {

            }
        });
    }




    /**
     * Listener that handles selections from suggestions from the AutoCompleteTextView that
     * displays Place suggestions.
     * Gets the place id of the selected item and issues a request to the Places Geo Data API
     * to retrieve more details about the place.
     *
     * @see com.google.android.gms.location.places.GeoDataApi#getPlaceById(com.google.android.gms.common.api.GoogleApiClient,
     * String...)
     */
//    private AdapterView.OnItemClickListener mAutocompleteClickListener
//            = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            /*
//             Retrieve the place ID of the selected item from the Adapter.
//             The adapter stores each Place suggestion in a AutocompletePrediction from which we
//             read the place ID and title.
//              */
//            final AutocompletePrediction item = mAdapter.getItem(position);
//            final String placeId = item.getPlaceId();
//            final CharSequence primaryText = item.getPrimaryText(null);
//
//            Log.i("Places Adapter", "Autocomplete item selected: " + primaryText);
//
//            /*
//             Issue a request to the Places Geo Data API to retrieve a Place object with additional
//             details about the place.
//              */
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                    .getPlaceById(mGoogleApiClient, placeId);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//
////            Toast.makeText(getApplicationContext(), "Clicked: " + primaryText,Toast.LENGTH_SHORT).show();
//            Log.i("Places Adapter", "Called getPlaceById to get Place details for " + placeId);
//        }
//    };
//
//    /**
//     * Callback for results from a Places Geo Data API query that shows the first place result in
//     * the details view on screen.
//     */
//    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback
//            = new ResultCallback<PlaceBuffer>() {
//        @Override
//        public void onResult(PlaceBuffer places) {
//            if (!places.getStatus().isSuccess()) {
//                // Request did not complete successfully
//                Log.e("Places Adapter", "Place query did not complete. Error: " + places.getStatus().toString());
//                places.release();
//                return;
//            }
//            // Get the Place object from the buffer.
//            final Place place = places.get(0);
//
//            Log.i("Places Adapter", "Place details received: " + place.getName());
//
//            places.release();
//        }
//    };

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("Google Api Client", "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        // TODO(Developer): Check error code and notify the user of error state and resolution.
        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }

    private class RidePagerAdapter extends FragmentPagerAdapter  {

        private String tabTitles[] = { "Ola", "Uber" };
        private int[] imageResId = {
                R.mipmap.logomark,
                R.mipmap.logomark,
        };
        private Context context;

        RidePagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(final int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new OlaRideFragment();
                    Bundle bo = new Bundle();
                    bo.putSerializable("ola_ride", (Serializable) olaRideEstimateList);
                    bo.putParcelable("pickup", pickLatLng);
                    bo.putParcelable("drop", dropLatLng);
                    bo.putString("pickupAdd",pickAdd);
                    bo.putString("dropAdd",dropAdd);
                    fragment.setArguments(bo);
 //                   bo.putSerializable("geolist", (Serializable) myList);
//                    bo.putParcelableArrayList("geolist", myList);


//                    Button olabutton=(Button)mRideViewPager.findViewById(R.id.btnNextOla);
//                    olabutton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(MainActivity.this,WebViewActivity.class
//                            ).putExtra("url", "https://corporate.olacabs.com/login"));
//                        }
//                    });

                    break;
                case 1:
                    fragment = new UberRideFragment();
                    Bundle bu = new Bundle();
                    bu.putSerializable("uber_ride", (Serializable) uberRideEstimateList);
                    bu.putParcelable("pickup", pickLatLng);
                    bu.putParcelable("drop", dropLatLng);
                    bu.putString("pickupAdd",pickAdd);
                    bu.putString("dropAdd",dropAdd);
                    fragment.setArguments(bu);


//                    Button uberbutton=(Button)mRideViewPager.findViewById(R.id.btnNextUber);
//
//                    uberbutton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            startActivity(new Intent(MainActivity.this,WebViewActivity.class
//                            ).putExtra("url", "https://www.uber.com/en-IN/sign-in"));
//                        }
//                    });
                    break;
            }



            return fragment;
        }



        @Override
        public CharSequence getPageTitle(int position) {
            Drawable image = context.getResources().getDrawable(imageResId[position]);
            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
            SpannableString sb = new SpannableString("   " + tabTitles[position]);
            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        }


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            firebaseAuth.signOut();
            startActivity(new Intent(this, login.class));
            //closing activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        finish();
    }


    @Override
    public void onClick(View view) {
        //if logout is pressed
//        if(view == buttonLogout){
//            //logging out the user
//            firebaseAuth.signOut();
//            startActivity(new Intent(this, login.class));
//            //closing activity
//            finish();
//            //starting login activity
//
//        }
    }

}
