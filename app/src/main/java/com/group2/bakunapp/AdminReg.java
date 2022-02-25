package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdminReg extends AppCompatActivity {

    EditText fullname,contact,email,pass,address;
    Button regbtn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_reg);

        mAuth = FirebaseAuth.getInstance();

        fullname = (EditText) findViewById(R.id.adminlname_reg_input);
        contact = (EditText)  findViewById(R.id.admincontact_input);
        email = (EditText)  findViewById(R.id.adminemail_reg_input);
        pass = (EditText) findViewById(R.id.adminpassword_reg_input);
        address = (EditText) findViewById(R.id.adminladdress_reg_input);

        regbtn = (Button) findViewById(R.id.adminsubmit_reg_btn);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog loader = new ProgressDialog(AdminReg.this);

                loader.setTitle("Please Wait");
                loader.setMessage("Registering Account");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

                mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser adminuser = mAuth.getCurrentUser();
                        String appointment = null,approval = "Admin",lastname = " ",middlename = "";



                        DatabaseReference df = FirebaseDatabase.getInstance().getReference("Users").child(adminuser.getUid());
                        Map<String,Object> userinfo = new HashMap<>();
                        userinfo.put("firstname",fullname.getText().toString());
                        userinfo.put("contactno",contact.getText().toString());
                        userinfo.put("adminemail",email.getText().toString());
                        userinfo.put("adminpassword",pass.getText().toString());
                        userinfo.put("address", address.getText().toString());
                        userinfo.put("approve", approval);
                        userinfo.put("lastname", lastname);
                        userinfo.put("middlename", middlename);


                        userinfo.put("isadmin", "1");

                        df.setValue(userinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                loader.dismiss();
                                adminuser.sendEmailVerification();


                                Toast.makeText(AdminReg.this, "Account Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AdminReg.this, AdminLogin.class));
                                finish();
                            }
                        });


                        startActivity(new Intent(AdminReg.this, AdminLogin.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        loader.dismiss();
                        Toast.makeText(AdminReg.this, "Failed to create Account", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });

            }
        });


    }
}