package com.example.zaimzanaruddin.derplist;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.example.zaimzanaruddin.derplist.ui.ListActivity;

public class Filter extends AppCompatActivity {

   private RadioGroup radioGroup;
    private RadioButton Highest, Lowest, Relevant;
    private Button BTN_GoBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
      addListenerOnButton();

    }

    public void addListenerOnButton(){

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        BTN_GoBack = (Button) findViewById(R.id.button3);

        Highest = (RadioButton)findViewById(R.id.MostLike);
        Lowest = (RadioButton)findViewById(R.id.LeastLike);
        Relevant = (RadioButton)findViewById(R.id.Relevant);




        BTN_GoBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId== Highest.getId())
                {
                    ListActivity.sort();
                    startActivity(new Intent(Filter.this, ListActivity.class));
                }
                else if(selectedId == Lowest.getId())
                {
                    ListActivity.sortReverse();
                    startActivity(new Intent(Filter.this, ListActivity.class));
                }
                else if(selectedId ==Relevant.getId())
                {
                    startActivity(new Intent(Filter.this, ListActivity.class));
                }

            } });

    }
}
