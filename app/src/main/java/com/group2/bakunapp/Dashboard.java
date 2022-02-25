package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userid;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        CardView prof = (CardView) findViewById(R.id.cardinfo);
        CardView updt = (CardView) findViewById(R.id.cardupdate);
        CardView logt = (CardView) findViewById(R.id.cardlogoutr);
        TextView username = (TextView) findViewById(R.id.usersname);

        stringBuilder = new StringBuilder();

        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Info.class));
            }
        });

        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,Update.class));
            }
        });

        logt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Dashboard.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this,Login.class));
            }
        });


        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){
                    String lastname = userprofile.lastname;
                    String firstname = userprofile.firstname;
                    String middlename= userprofile.middlename;

                    stringBuilder.append(firstname);
                    stringBuilder.append(" "+middlename);
                    stringBuilder.append(" "+lastname);

                    username.setText(stringBuilder);

                }
                else{
                    Toast.makeText(Dashboard.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Dashboard.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });




    }
}