package com.joaquinalan.steeringwheelbluetooth.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joaquinalan.steeringwheelbluetooth.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaquinalan on 21/05/2017.
 */

public class FoundDevicesAdapter extends RecyclerView.Adapter<FoundDevicesAdapter.BluetoothDeviceViewHolder> {
    private static final String TAG = "Adapter RecyclerView";
    //private ArrayList<BluetoothDevice> mDevices;
    private List<String> mDevices;
    private final ListItemClickListener mOnClickListener;

    public FoundDevicesAdapter(List devices, ListItemClickListener mOnClickListener) {
        this.mDevices = devices;
        this.mOnClickListener = mOnClickListener;
    }

    public FoundDevicesAdapter(ListItemClickListener mOnClickListener) {
        mDevices = new ArrayList<>();
        this.mOnClickListener = mOnClickListener;
    }

    public void updateData(List devices) {
        mDevices.clear();
        mDevices.addAll(devices);
        notifyDataSetChanged();
    }

//    public void addDevice(ArrayList devices) {
//        mDevices = devices;
//        //mDevices.add(device);
//        notifyDataSetChanged();
//        //notifyItemInserted(mDevices.size() - 1);
////        for (BluetoothDevice dev : mDevices) {
////            Log.d(TAG, dev.getName());
////        }
//
//    }

    @Override
    public BluetoothDeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForNumberItem = R.layout.view_bluetooth_founddevicecardview;
        LayoutInflater inflater = LayoutInflater.from(context);

        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForNumberItem, parent, shouldAttachToParentImmediately);
        BluetoothDeviceViewHolder viewHolder = new BluetoothDeviceViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BluetoothDeviceViewHolder holder, int position) {
        holder.mTextViewDeviceName.setText(mDevices.get(position));
    }

    @Override
    public int getItemCount() {
        return mDevices.size();
    }

    class BluetoothDeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView mTextViewDeviceName;

        BluetoothDeviceViewHolder(View itemView) {
            super(itemView);
            mTextViewDeviceName = (TextView) itemView.findViewById(R.id.textview_bluetooth_devicename);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClicked(getAdapterPosition());
        }
    }

    public interface ListItemClickListener {
        void onListItemClicked(int clickedItemIndex);
    }
}
