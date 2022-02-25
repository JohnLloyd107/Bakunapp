package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Datausers extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    NewAdaptor newAdaptor;
    ArrayList<Users> list;
    Button adminlogoutbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datausers);

        recyclerView = findViewById(R.id.Userlist);
        mAuth = FirebaseAuth.getInstance();
        adminlogoutbtn = (Button) findViewById(R.id.adminlogoutbtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance().getReference("Users");


        FirebaseRecyclerOptions<Users> options =
                new FirebaseRecyclerOptions.Builder<Users>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), Users.class)
                        .build();

        newAdaptor = new NewAdaptor(options);
        recyclerView.setAdapter(newAdaptor);

        adminlogoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Datausers.this, AdminLogin.class));
                finish();
            }
        });





        

    }

    @Override
    protected void onStart() {
        super.onStart();
        newAdaptor.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        newAdaptor.stopListening();
    }

    @Override
    public void onBackPressed(){

        mAuth.signOut();
        startActivity(new Intent(Datausers.this,Login.class));
        finish();

    }
}