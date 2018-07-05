package com.fw.olauberintegration.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.olauberintegration.R;
import com.fw.olauberintegration.model.UberRideEstimate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UberRideEstimateAdapter extends RecyclerView.Adapter<UberRideEstimateAdapter.ViewHolder> {

    private List<UberRideEstimate> mUberRideEstimateList;

    public UberRideEstimateAdapter(List<UberRideEstimate> rideEstimateList) {
        mUberRideEstimateList = rideEstimateList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ride_estimate, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!mUberRideEstimateList.isEmpty()) {
            UberRideEstimate uberRideEstimate = mUberRideEstimateList.get(position);
            holder.mTxtRideName.setText(uberRideEstimate.getDisplay_name());
            holder.mTxtRideAmount.setText(uberRideEstimate.getEstimate());

        }
    }

    @Override
    public int getItemCount() {
        return mUberRideEstimateList == null ? 0 : mUberRideEstimateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ride_logo) ImageView mImgRideLogo;
        @BindView(R.id.ride_amount) TextView mTxtRideAmount;
        @BindView(R.id.ride_name) TextView mTxtRideName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
