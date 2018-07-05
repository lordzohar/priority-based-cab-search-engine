package com.fw.olauberintegration.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fw.olauberintegration.R;
import com.fw.olauberintegration.model.OlaRideEstimate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kaustubh on 19/4/17.
 */
public class OlaRideEstimateAdapter extends RecyclerView.Adapter<OlaRideEstimateAdapter.ViewHolder> {

    private List<OlaRideEstimate> mOlaRideEstimateList;
    private Context mContext;


    public OlaRideEstimateAdapter(Context context,List<OlaRideEstimate> rideEstimateList) {
        mContext = context;
        mOlaRideEstimateList = rideEstimateList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ride_estimate, parent, false);


        return new ViewHolder(itemView);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!mOlaRideEstimateList.isEmpty()){
            OlaRideEstimate olaRideEstimate = mOlaRideEstimateList.get(position);
//            if (olaRideEstimate.getCategory()=="auto"){
//                holder.mTxtRideName.setText("exec");
//                Log.d("Auto fix","This works 1");
//            }
//            if(olaRideEstimate.getCategory()=="exec")
//            {
//                holder.mTxtRideName.setText("auto");
//                Log.d("exec fix","This works 2");
//            }
//            else {}
            holder.mTxtRideName.setText(olaRideEstimate.getCategory());

//            if (olaRideEstimate.getCategory()!="share") {holder.mTxtRideAmount.setText(mContext.getResources().getString(R.string.rupee_symbol)+olaRideEstimate.getAmount_min()+"-"+olaRideEstimate.getAmount_max());}
//            else {holder.mTxtRideAmount.setText(mContext.getResources().getString(R.string.rupee_symbol)+olaRideEstimate.getFares()); }
            holder.mTxtRideAmount.setText(mContext.getResources().getString(R.string.rupee_symbol)+olaRideEstimate.getAmount_min()+"-"+olaRideEstimate.getAmount_max());

            switch (olaRideEstimate.getCategory()){
//                case "auto":
//                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_mini);
//                    break;
                case "exec":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_micro);
                    break;

                case "erick":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_erick);
                    break;

                case "kaalipeeli":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_kaalipeeli);
                    break;

                case "mini":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_mini);
                    break;

                case "micro":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_micro);
                    break;

                case "sedan":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_sedan);
                    break;

                case "prime":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_prime);
                    break;

                case "suv":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_suv);
                    break;

                case "lux":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_suv);
                    break;

                case "outstation":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_suv);
                    break;

                case "rentals":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_rentals);
                    break;

                case "share":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_share);
                    break;
                case "cool_cab":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_mini);
                    break;

                case "auto":
                    holder.mImgRideLogo.setImageResource(R.mipmap.ic_auto);
                    break;


            }
        }
    }



    @Override
    public int getItemCount() {
        return mOlaRideEstimateList == null ? 0 : mOlaRideEstimateList.size();
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
