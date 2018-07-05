package com.fw.olauberintegration.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fw.olauberintegration.R;
import com.fw.olauberintegration.WebViewActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import java.util.ArrayList;

/**
 * Created by kaustubh on 19/4/17.
 */
public class OlaRideFragment extends Fragment {

    //Bundle listbundle;
    @BindView(R.id.recycler_view) RecyclerView recycler_view;
    @BindView(R.id.btnNextOla) Button olabtn;
    @Nullable
    private LatLng pickLatLng, dropLatLng;
    private String pickupAdd,dropAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ola_ride_estimate,container,false);
        ButterKnife.bind(this,view);

        //.putExtra("url", "https://book.olacabs.com/confirm-ride-p2p?utm_source=book_now_top_right&lat=" + pickLatLng.latitude + "&lng=" + pickLatLng.longitude + "&drop_lat=" + dropLatLng.latitude + "&drop_lng=" + dropLatLng.longitude)
        //.putExtra("url", "https://book.olacabs.com/confirm-ride-p2p?utm_source=book_now_top_right&pickup_name=Thane%2C%20Maharashtra%20India&lat=19.2183307&lng=72.9780897&drop_lat=19.0599836&drop_lng=72.889999&drop_name=Kurla%2C%20Mumbai%20Maharashtra%20Indian")
        //.putExtra("url", "https://book.olacabs.com/confirm-ride-p2p?utm_source=book_now_top_right&pickup_name="+pickupAdd+"&lat=" + pickLatLng.latitude + "&lng=" + pickLatLng.longitude + "&drop_lat=" + dropLatLng.latitude + "&drop_lng=" + dropLatLng.longitude+"&drop_name="+dropAdd)
        //.putExtra("url", "http://book.olacabs.com/?lat="+pickLatLng.latitude+"&lng="+pickLatLng.longitude+"&category=auto&utm_source=12343&landing_page=bk&bk_act=rn&drop_lat="+dropLatLng.latitude+"&drop_lng="+dropLatLng.longitude+"&dsw=yes")
        //  .putExtra("url", "https://book.olacabs.com/confirm-ride-p2p?utm_source=book_now_top_right&pickup_name="+newpickupAdd+"&lat=" + pickLatLng.latitude + "&lng=" + pickLatLng.longitude + "&drop_lat=" + dropLatLng.latitude + "&drop_lng=" + dropLatLng.longitude+"&drop_name="+newdropAdd)
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ArrayList myList = (ArrayList) getArguments().getParcelableArrayList("geolist");

        //List myList = (List) getArguments().getSerializable("ola_ride");
        pickLatLng= getArguments().getParcelable("pickup");
        dropLatLng=getArguments().getParcelable("drop");
        pickupAdd=getArguments().getString("pickupAdd");
        dropAdd=getArguments().getString("dropAdd");
        //        dropLatLng= (LatLng) myList.get(1);
        //Log.d("OLA web pickup", String.valueOf(pickLatLng));

        final String newpickupAdd=pickupAdd.replaceFirst(",","%2C").replaceAll(",","").replaceAll(" ","%20");

        final String newdropAdd=dropAdd.replaceFirst(",","%2C").replaceAll(",","").replaceAll(" ","%20");
        olabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pickupAdd=pickupAdd.replaceAll(",","%2C")
                assert dropLatLng != null;
                assert pickLatLng != null;
                startActivity(new Intent(OlaRideFragment.this.getActivity(), WebViewActivity.class)
                .putExtra("url","https://book.olacabs.com/?utm_source=old-website&utm_medium=redirect&utm_campaign=web&drop_lat="+dropLatLng.latitude+"&drop_lng="+dropLatLng.longitude+"&drop_name="+newdropAdd+"&pickup_name="+newpickupAdd+"&lat="+pickLatLng.latitude+"&lng="+pickLatLng.longitude+"&pickup=")
                );

            }
        });


        List olaRideEstimateList = (List) getArguments().getSerializable("ola_ride");
        Log.e("Fragment uber", olaRideEstimateList.size()+"");
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(horizontalLayoutManagaer);

        OlaRideEstimateAdapter mRideAdapter = new OlaRideEstimateAdapter(getActivity(), olaRideEstimateList);


        recycler_view.setAdapter(mRideAdapter);


        mRideAdapter.notifyDataSetChanged();




    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
}
