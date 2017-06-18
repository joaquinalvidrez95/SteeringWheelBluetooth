package com.joaquinalan.steeringwheelbluetooth.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joaquinalan.steeringwheelbluetooth.R;
import com.joaquinalan.steeringwheelbluetooth.presenter.BluetoothPresenter;
import com.joaquinalan.steeringwheelbluetooth.presenter.MvpBluetoothPresenter;
import com.joaquinalan.steeringwheelbluetooth.view.BluetoothView;
import com.joaquinalan.steeringwheelbluetooth.view.adapter.FoundDevicesAdapter;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener, BluetoothView, FoundDevicesAdapter.ListItemClickListener {
    private RecyclerView mRecyclerViewFoundDevices;
    private FoundDevicesAdapter mFoundDevicesAdapter;
    private MvpBluetoothPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        FloatingActionButton mFloatingActionButtonToogleBluetooth;
        mFloatingActionButtonToogleBluetooth = (FloatingActionButton) findViewById(R.id.floatingactionbutton_bluetooth_tooglebluetooth);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar_bluetooth);

        mRecyclerViewFoundDevices = (RecyclerView) findViewById(R.id.reciclerview_bluetooth);

        mFoundDevicesAdapter = new FoundDevicesAdapter(this);

        setUpRecyclerView();

        setUpToolbar(toolbar);

        mFloatingActionButtonToogleBluetooth.setOnClickListener(this);

        mPresenter = new BluetoothPresenter(this);
    }

    private void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.bluetooth_toolbartitle);
        getSupportActionBar().setIcon(R.drawable.ic_main_bluetooth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewFoundDevices.setLayoutManager(layoutManager);
        mRecyclerViewFoundDevices.setHasFixedSize(true);
        mRecyclerViewFoundDevices.setAdapter(mFoundDevicesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingactionbutton_bluetooth_tooglebluetooth:
                mPresenter.onToogleButtonClicked();
        }
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {

    }
}
