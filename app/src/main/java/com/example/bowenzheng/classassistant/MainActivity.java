package com.example.bowenzheng.classassistant;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import mobi.inthepocket.android.beacons.ibeaconscanner.Beacon;
import mobi.inthepocket.android.beacons.ibeaconscanner.Error;
import mobi.inthepocket.android.beacons.ibeaconscanner.IBeaconScanner;

public class MainActivity extends AppCompatActivity implements IBeaconScanner.Callback
{
    private ProgressBar loadingSpinner;
    private ListView syllabus;
    private TextView loadingText;
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        String[] values = {"Math 10","Chapter 9.2","Chapter 9.3","Project Proposal",
                "Hw Chapter 8.2","Hw Chapter 8.3","Hw Chapter 8.4"};
        loadingSpinner = (ProgressBar) findViewById(R.id.progress_bar);
        syllabus = (ListView) findViewById(R.id.syllabus_list);
        loadingText = (TextView) findViewById(R.id.textView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        syllabus.setAdapter(adapter);
        IBeaconScanner.getInstance().setCallback(this);

        IBeaconScanner.getInstance().startMonitoring(Beacon.newBuilder()
                .setUUID("48DE4980-968D-11E4-B4A9-0800200C9A66")
                .setMajor(1)
                .setMinor(47993)
                .build());
        //IBeaconScanner.getInstance().stop();
    }

    //region Callback

    @Override
    public void didEnterBeacon(final Beacon beacon)
    {
//        final String logMessage = String.format("Entered beacon with UUID %s and major %s and minor %s.", beacon.getUUID(), beacon.getMajor(), beacon.getMinor());
//        System.out.print(logMessage);

        loadingSpinner.setVisibility(View.GONE);
        loadingText.setVisibility(View.GONE);
        syllabus.setVisibility(View.VISIBLE);
    }

    @Override
    public void didExitBeacon(final Beacon beacon)
    {
        loadingSpinner.setVisibility(View.VISIBLE);
        loadingText.setVisibility(View.VISIBLE);
        syllabus.setVisibility(View.GONE);
    }

    @Override
    public void monitoringDidFail(final Error error)
    {
        Toast.makeText(MainActivity.this, "Could not scan due to " + error.name(), Toast.LENGTH_LONG).show();
    }


}
