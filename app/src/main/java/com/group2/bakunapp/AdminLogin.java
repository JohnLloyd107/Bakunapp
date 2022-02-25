package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminLogin extends AppCompatActivity {

    private EditText adminemail_input,adminpassword_input;
    private Button adminrgtbtn,adminloginbtn;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private TextView signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        signup = (TextView) findViewById(R.id.Signup);
        adminemail_input = (EditText) findViewById(R.id.adminlogin_email_input);
        adminpassword_input =(EditText) findViewById(R.id.adminlogin_password_input);
        adminloginbtn = (Button) findViewById(R.id.adminlogin_btn);



        adminloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog loader = new ProgressDialog(AdminLogin.this);

                loader.setTitle("Please Wait");
                loader.setMessage("Logging Account");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

                String email = adminemail_input.getText().toString().trim();
                String password = adminpassword_input.getText().toString().trim();

                if (email.isEmpty()){
                    loader.dismiss();
                    adminemail_input.setError("Email is required!");
                    adminemail_input.requestFocus();
                    return;
                }

                if (password.isEmpty()){
                    loader.dismiss();
                    adminpassword_input.setError("Password is required!");
                    adminpassword_input.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(adminemail_input.getText().toString(),adminpassword_input.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                        if (user.isEmailVerified()){
                            loader.dismiss();

                            checkUserAccessLevel(authResult.getUser().getUid());

                        }
                        else {
                            loader.dismiss();
                            AlertDialog.Builder alert = new AlertDialog.Builder(AdminLogin.this);
                            alert.setTitle("Account not Verified");
                            alert.setMessage("Your account is not yet verified, verify you email account first");
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    return;


                                }
                            });
                            alert.create().show();
                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loader.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(AdminLogin.this);
                        alert.setTitle("Invalid Account");
                        alert.setMessage("Incorrect Email or Password");
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;


                            }
                        });
                        alert.create().show();

                    }
                });
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this, AdminReg.class));
                finish();
            }
        });


    }

    private void checkUserAccessLevel(String uid) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        db.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {




                Users user = dataSnapshot.getValue(Users.class);
                String isadmin = user.isadmin;
                String isuser = user.isuser;

                if (isadmin != null){

                    Toast.makeText(AdminLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(AdminLogin.this, Datausers.class));
                    finish();

                }
                if (isuser != null){
                    Toast.makeText(AdminLogin.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                    mAuth.signOut();
                    return;
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.signOut();
    }
}