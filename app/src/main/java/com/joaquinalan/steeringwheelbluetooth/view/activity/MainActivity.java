package com.joaquinalan.steeringwheelbluetooth.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.joaquinalan.steeringwheelbluetooth.R;
import com.joaquinalan.steeringwheelbluetooth.presenter.MainPresenter;
import com.joaquinalan.steeringwheelbluetooth.presenter.MvpMainPresenter;
import com.joaquinalan.steeringwheelbluetooth.view.MvpMainView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MvpMainView {
    private static final int REQUEST_DEVICE_CODE = 0;
    private FloatingActionButton mFloatingActionButtonBluetooth;
    private MvpMainPresenter mPresenter;
    private TextView mTextViewState;
//    private SteeringWheelSensor mSteeringWheelSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        mFloatingActionButtonBluetooth = (FloatingActionButton) findViewById(R.id.floatingactionbutton_main_bluetooth);
        mTextViewState = (TextView) findViewById(R.id.textview_main_steeringwheelstate);

        mFloatingActionButtonBluetooth.setOnClickListener(this);

        //mSteeringWheelSensor.start();

//
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
//
//        if (mToolbar != null) {
//            setSupportActionBar(mToolbar);
//            getSupportActionBar().setTitle(R.string.app_name);
//            getSupportActionBar().setIcon(R.drawable.ic_main_steeringwheel);
//        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingactionbutton_main_bluetooth:
                mPresenter.onBluetoothButtonClicked();
        }
    }

    @Override
    public void startBluetoothActivity() {
        Intent bluetoothIntent = new Intent(this, BluetoothActivity.class);
        startActivityForResult(bluetoothIntent, REQUEST_DEVICE_CODE);
    }

    @Override
    public void showSteeringWheelState(String state) {
        mTextViewState.setText(state);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

//    @Override
//    public void onSteeringWheelChanged(int steeringWheelState) {
//        mPresenter.onSteeringWheelChanged(steeringWheelState);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item_optionsmenu_bluetooth:
//                Intent bluetoothIntent = new Intent(this, BluetoothActivity.class);
//                startActivity(bluetoothIntent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
