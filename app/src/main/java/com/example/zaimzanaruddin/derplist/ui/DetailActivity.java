package com.example.zaimzanaruddin.derplist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zaimzanaruddin.derplist.R;

public class DetailActivity extends AppCompatActivity {



    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    private static final String EXTRA_ATTR = "EXTRA_ATTR";
    private static final String EXTRA_TIME = "EXTRA_TIME";
    private static final String EXTRA_DES = "EXTRA_DES";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((TextView)findViewById(R.id.lbl_quote_title)).setText(extras.getString(EXTRA_QUOTE));
        ((TextView)findViewById(R.id.lbl_quote_location)).setText(extras.getString(EXTRA_ATTR));
      //  ((TextView)findViewById(R.id.lbl_quote_date)).setText(extras.getString(EXTRA_DATE));
        ((TextView)findViewById(R.id.lbl_quote_description)).setText(extras.getString(EXTRA_DES));
        ((TextView)findViewById(R.id.lbl_quote_time)).setText(extras.getString(EXTRA_TIME));
    }
}
