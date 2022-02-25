package com.group2.bakunapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class A5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a5);

        Button rgstbtn = (Button) findViewById(R.id.nextbtn);
        Button backtbtn = (Button) findViewById(R.id.backbtn);

        rgstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView a5 = (TextView) findViewById(R.id.textView5);
                String categ = a5.getText().toString();

                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");



                Intent nextact = new Intent(A5.this, Register.class);
                nextact.putExtra("category",categ);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);


                startActivity(nextact);
                finish();


            }
        });

        backtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(A5.this, Category.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();

            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        startActivity(new Intent(A5.this,Category.class));

    }
}