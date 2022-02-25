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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText email,password;
    private TextView regbtn;
    private Button login_btn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.login_email_input);
        password = (EditText) findViewById(R.id.login_password_input);
        login_btn = (Button) findViewById(R.id.login_btn);
        regbtn = (TextView) findViewById(R.id.regbtn);


        mAuth = FirebaseAuth.getInstance();

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Reg2.class));
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();


            }
        });



    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(Login.this,MainActivity.class));

    }

    private void userLogin(){
        ProgressDialog loader = new ProgressDialog(this);

        loader.setTitle("Please Wait");
        loader.setMessage("Logging Account");
        loader.setCanceledOnTouchOutside(false);
        loader.show();

        String lemail = email.getText().toString().trim();
        String lpassword = password.getText().toString().trim();

        if (lemail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(lemail).matches()){
            email.setError("Please Enter valid Email!");
            email.requestFocus();
            return;
        }

        if (lpassword.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }



        mAuth.signInWithEmailAndPassword(lemail,lpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


                    if (user.isEmailVerified()){
                        DatabaseReference reference;
                        String userid;
                        reference = FirebaseDatabase.getInstance().getReference("Users");
                        userid = user.getUid();
                        Map<String,Object> userinfo = new HashMap<>();


                        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Users userprofile = snapshot.getValue(Users.class);


                                if (userprofile != null){
                                    FirebaseUser adminuser = mAuth.getCurrentUser();
                                    DatabaseReference df = FirebaseDatabase.getInstance().getReference("Users").child(adminuser.getUid());
                                    Map<String,Object> userinfo = new HashMap<>();
                                    userinfo.put("isadmin", null);
                                    userinfo.put("isuser", "1");

                                    String isadmin = userprofile.isadmin;
                                    String isuser = userprofile.isuser;

                                    if (isuser != null){

                                        loader.dismiss();

                                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();

                                        Intent nextact = new Intent(Login.this, Category.class);

                                        startActivity(nextact);
                                        finish();}

                                    if (isadmin != null){
                                        loader.dismiss();
                                        Toast.makeText(Login.this, "Invalid Account", Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                        return;
                                    }

                                }
                                else{
                                    loader.dismiss();
                                    Bundle bundle = getIntent().getExtras();

                                    String lastnames = bundle.getString("lastname");
                                    String firstnames = bundle.getString("firstname");
                                    String middlenames = bundle.getString("middlename");
                                    String suffixs = bundle.getString("suffix");


                                    Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_LONG).show();

                                    Intent nextact = new Intent(Login.this, Category.class);
                                    nextact.putExtra("lastname",lastnames);
                                    nextact.putExtra("firstname",firstnames);
                                    nextact.putExtra("middlename",middlenames);
                                    nextact.putExtra("suffix",suffixs);



                                    startActivity(nextact);
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {



                            }
                        });


                    }
                    else {
                        loader.dismiss();
                        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
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
                else {
                    loader.dismiss();
                    Toast.makeText(Login.this, "Invalid Account", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}