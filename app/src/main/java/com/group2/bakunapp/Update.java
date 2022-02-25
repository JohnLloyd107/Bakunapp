package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    private TextView appro,scheds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        final TextView approve = (TextView) findViewById(R.id.textView10);
        final TextView date1 = (TextView) findViewById(R.id.dose1up);
        final TextView date2 = (TextView) findViewById(R.id.dose2up);
        final TextView boosterdate = (TextView) findViewById(R.id.boosterup);
        final TextView location = (TextView) findViewById(R.id.textView12);
        final TextView typevalue = (TextView) findViewById(R.id.typevalue);
        appro = (TextView) findViewById(R.id.textView13);
        Button bckbtn = (Button)findViewById(R.id.button);
        Button btn = (Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Update.this,AllergyList.class));
            }
        });

        bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });





        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){

                    String appr = userprofile.approve;
                    String dose1 = userprofile.appointment;
                    String dose2 = userprofile.second;
                    String booster = userprofile.booster;
                    String place = userprofile.place;
                    String type = userprofile.type;

                    approve.setVisibility(View.VISIBLE);
                    approve.setText(appr);
                    date1.setVisibility(View.VISIBLE);
                    date1.setText(dose1);
                    date2.setText(dose2);
                    boosterdate.setText(booster);
                    location.setText(place);
                    typevalue.setText(type);
















                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Update.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}