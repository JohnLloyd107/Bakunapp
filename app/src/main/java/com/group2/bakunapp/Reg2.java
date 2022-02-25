package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reg2 extends AppCompatActivity {

    private EditText email_input,password_input;
    private Button rgtbtn;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);

        mAuth = FirebaseAuth.getInstance();

        email_input = (EditText) findViewById(R.id.email_reg_input);
        password_input = (EditText)  findViewById(R.id.password_reg_input);
        rgtbtn = (Button) findViewById(R.id.submit_reg_btn);

        AlertDialog.Builder alert = new AlertDialog.Builder(Reg2.this);
        ProgressDialog loader = new ProgressDialog(this);

        loader.setTitle("Please Wait");
        loader.setMessage("Registering Account");
        loader.setCanceledOnTouchOutside(false);


        rgtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText lname = (EditText) findViewById(R.id.lname_reg_input);
                String lastnames = lname.getText().toString();
                EditText fname = (EditText) findViewById(R.id.fname_reg_input);
                String firstnames = fname.getText().toString();
                EditText mname = (EditText) findViewById(R.id.mname_reg_input);
                String middlenames = mname.getText().toString();
                EditText sname = (EditText) findViewById(R.id.sname_reg_input);
                String suffixs = sname.getText().toString();

                String email = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();





                if (email.isEmpty()){
                    email_input.setError("Email is required!");
                    email_input.requestFocus();
                    return;
                }


                if (password.isEmpty()){
                    password_input.setError("Password is required!");
                    password_input.requestFocus();
                    return;
                }

                if (lastnames.isEmpty()){
                    lname.setError("Lastname is required!");
                    lname.requestFocus();
                    return;
                }

                if (firstnames.isEmpty()){
                    fname.setError("First Name is required!");
                    fname.requestFocus();
                    return;
                }

                if (middlenames.isEmpty()){
                    mname.setError("Middle Initial is required!");
                    mname.requestFocus();
                    return;
                }











                alert.setMessage("Are you sure you want to Submit?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        loader.show();

                        mAuth.createUserWithEmailAndPassword(email,password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {


                                        if (task.isSuccessful()){


                                            FirebaseDatabase.getInstance().getReference(Users.class.getSimpleName())
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){
                                                        loader.dismiss();

                                                        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();

                                                        users.sendEmailVerification();

                                                        Toast.makeText(Reg2.this, "Verification has been sent in your email", Toast.LENGTH_SHORT).show();


                                                        Toast.makeText(Reg2.this, "Registered", Toast.LENGTH_SHORT).show();

                                                        Intent nextact = new Intent(Reg2.this, Login.class);
                                                        nextact.putExtra("lastname",lastnames);
                                                        nextact.putExtra("firstname",firstnames);
                                                        nextact.putExtra("middlename",middlenames);
                                                        nextact.putExtra("suffix",suffixs);



                                                        startActivity(nextact);
                                                        finish();

                                                    }

                                                    else {
                                                        Toast.makeText(Reg2.this, "Email already Registered", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });



                                        }
                                    }
                                });

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
        });
    }
}