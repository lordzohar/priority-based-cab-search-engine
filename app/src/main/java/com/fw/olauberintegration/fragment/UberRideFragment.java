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

public class UberRideFragment extends Fragment {

    @BindView(R.id.recycler_view)

    RecyclerView recycler_view;

    @BindView(R.id.btnNextUber) Button uberbtn;
    private LatLng pickLatLng, dropLatLng;
    private String pickupAdd,dropAdd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_uber_ride_estimate,container,false);
        ButterKnife.bind(this,view);




        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List uberRideEstimateList = (List) getArguments().getSerializable("uber_ride");
        Log.e("Fragment uber", uberRideEstimateList.size()+"");

        pickLatLng= getArguments().getParcelable("pickup");
        dropLatLng=getArguments().getParcelable("drop");
        pickupAdd=getArguments().getString("pickupAdd");
        dropAdd=getArguments().getString("dropAdd");

        //final String newpickupAdd=pickupAdd.replaceAll(",","%2C").replaceAll(" ","%20");

        //final String newdropAdd=dropAdd.replaceAll(",","%2C").replaceAll(" ","%20");

        //        .putExtra("url", "https://m.uber.com/looking/finalize?pickup={\"latitude\":"+pickLatLng.latitude+",\"longitude\":"+pickLatLng.longitude+",\"id\":\"ChIJWf12_vy45zsRgwLF94V9Ns8\",\"provider\":\"google_places\",\"fullAddress\":null,\"addressLine1\":\""+newpickupAdd[0]+"\",\"addressLine2\":\"Maharashtra\",\"title\":null}drop={\"id\":\"ChIJQxclnz_O5zsRJ9UlCTg7Ut0\",\"provider\":\"google_places\",\"fullAddress\":null,\"addressLine1\":\""+newdropAdd[1]+"\",\"addressLine2\":\"Mumbai, Maharashtra\",\"title\":null}"));
        //                .putExtra("url", "https://m.uber.com/looking/finalize?pickup=%7B%22latitude%22%3A"+pickLatLng.latitude+",%22longitude%22%3A"+pickLatLng.longitude+",%22shortAddress%22:%22"+newpickupAdd+"%22,%22longAddress%22:"+newpickupAdd+"%22,%22nickname%22:%22%22%7D&destination=%7B%22latitude%22:"+dropLatLng.latitude+",%22longitude%22:"+dropLatLng.longitude+",%22title%22:%22"+newdropAdd+"%22%7D\n"));
        //         .putExtra("url", "https://m.uber.com/looking/finalize?pickup=%7B%22latitude%22%3A"+pickLatLng.latitude+"%2C%22longitude%22%3A"+pickLatLng.longitude+"%2C%22id%22%3A%22ChIJx3QWzT_O5zsRYcQQar0gg6M%22%2C%22provider%22%3A%22google_places%22%2C%22fullAddress%22%3A"+newpickupAdd+"%22%2C%22title%22%3Anull%7D&destination=%7B%22id%22%3A%22EkYzNSwgQmVsbGFzaXMgUm9hZCwgRGFsYWwgRXN0YXRlLCBNYWRhbnB1cmEsIE11bWJhaSwgTWFoYXJhc2h0cmEsIEluZGlh%22%2C%22provider%22%3A%22google_places%22%2C%22fullAddress%22%3A"+newdropAdd+"%22title%22%3Anull%7D"));

        uberbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UberRideFragment.this.getActivity(),WebViewActivity.class)
                        .putExtra("url", "https://m.uber.com/looking/finalize?pickup=%7B%22latitude%22%3A19.0005536%2C%22longitude%22%3A73.1042018%2C%22id%22%3A%22ChIJefH7Njro5zsRbsXoUVCQsLs%22%2C%22provider%22%3A%22google_places%22%2C%22fullAddress%22%3Anull%2C%22addressLine1%22%3A%22AIKTC%20COLLEGE%22%2C%22addressLine2%22%3A%22Sector%2016%2C%20Khanda%20Colony%2C%20Panvel%2C%20Navi%20Mumbai%2C%20Maharashtra%22%2C%22title%22%3Anull%7D&destination=%7B%22id%22%3A%22EllQYW52ZWwsIFBhbnZlbCBDby1vcCBIb3VzaW5nIFNvY2lldHksIE9sZCBQYW52ZWwsIFBhbnZlbCwgTmF2aSBNdW1iYWksIE1haGFyYXNodHJhLCBJbmRpYQ%22%2C%22provider%22%3A%22google_places%22%2C%22fullAddress%22%3Anull%2C%22addressLine1%22%3A%22Panvel%22%2C%22addressLine2%22%3A%22Panvel%20Co-op%20Housing%20Society%2C%20Old%20Panvel%2C%20Panvel%2C%20Navi%20Mumbai%2C%20Maharashtra%22%2C%22title%22%3Anull%7D"));


            }
        });

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(horizontalLayoutManagaer);

        UberRideEstimateAdapter mRideAdapter = new UberRideEstimateAdapter(uberRideEstimateList);
        recycler_view.setAdapter(mRideAdapter);
        mRideAdapter.notifyDataSetChanged();

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setUserVisibleHint(true);
    }
}
