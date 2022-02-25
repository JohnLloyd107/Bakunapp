package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Category extends AppCompatActivity {


    private String userid;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();
        mAuth = FirebaseAuth.getInstance();

        CardView a1 = (CardView) findViewById(R.id.a1category);
        CardView a2 = (CardView) findViewById(R.id.a2category);
        CardView a3 = (CardView) findViewById(R.id.a3category);
        CardView a4 = (CardView) findViewById(R.id.a4category);
        CardView a5 = (CardView) findViewById(R.id.a5category);
        CardView roap = (CardView) findViewById(R.id.roapcategory);


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, A1.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, a2.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, A3.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, A4.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, A5.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        roap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, Roap.class);
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

        AlertDialog.Builder alert = new AlertDialog.Builder(Category.this);
        alert.setTitle("Warning");
        alert.setMessage("Are you sure you want to Exit?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");

                Intent nextact = new Intent(Category.this, A4.class);
                nextact.putExtra("lastname",lastnames);
                nextact.putExtra("firstname",firstnames);
                nextact.putExtra("middlename",middlenames);
                nextact.putExtra("suffix",suffixs);



                startActivity(nextact);
                finish();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alert.create().show();

    }

    @Override
    protected void onStart(){
        super.onStart();

        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){
                    Toast.makeText(Category.this, "Welcome Back", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Category.this,Dashboard.class));




                }
                else if(userprofile == null){
                    Toast.makeText(Category.this, "Please Choose a Category", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Category.this, "Please Choose a Category", Toast.LENGTH_SHORT).show();



            }
        });



    }



}