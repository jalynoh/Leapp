package com.example.zaimzanaruddin.derplist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zaimzanaruddin.derplist.R;

public class EventPage extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_LOC = "EXTRA_LOC";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_TIME = "EXTRA_TIME";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((TextView)findViewById(R.id.lbl_quote_title)).setText(extras.getString(EXTRA_TITLE));
        ((TextView)findViewById(R.id.lbl_quote_location)).setText(extras.getString(EXTRA_LOC));
      // ((TextView)findViewById(R.id.lbl_quote_date)).setText(extras.getString(EXTRA_DATE));
        ((TextView)findViewById(R.id.lbl_quote_time)).setText(extras.getString(EXTRA_TIME));
    }
}
