package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class UsersProfile extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    public String fullname;
    private StringBuilder stringBuilder;

    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        TextView username = (TextView) findViewById(R.id.username);
        TextView sex = (TextView) findViewById(R.id.sex);
        TextView contact = (TextView) findViewById(R.id.contact);
        TextView address = (TextView) findViewById(R.id.address1);
        TextView appointments = (TextView) findViewById(R.id.appointment);
        TextView appr = (TextView) findViewById(R.id.approved);
        Button saved = (Button) findViewById(R.id.change);


        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){

                    String lastname = userprofile.lastname;
                    String firstname = userprofile.firstname;
                    String middlename= userprofile.middlename;

                    String ages = userprofile.age;
                    String sexs = userprofile.sex;
                    String addresss = userprofile.address;
                    String contactnos = userprofile.contactno;

                    String categorys = userprofile.category;
                    String selectedids= userprofile.selectedid;
                    String civilstatuss = userprofile.civilstatus;
                    String idnos = userprofile.idnumber;
                    String pregnancys = userprofile.pregnancy;
                    String menstruals = userprofile.menstrual;
                    String drugallergys = userprofile.drugallergy;
                    String foodallergys = userprofile.foodallergy;
                    String insectallergys = userprofile.insectallergy;
                    String laxerrubberallergys = userprofile.laxerrubberallergy;
                    String moldallergys = userprofile.moldallergy;
                    String petalallergys= userprofile.petalalleregy;
                    String pollenallergys = userprofile.pollenallergy;
                    String comorbiditiess = userprofile.comorbidities;
                    String hypertensions = userprofile.hypertension;
                    String hearthdiseases = userprofile.heartdisease;
                    String kidneydiseases = userprofile.kidneydisease;
                    String diabetesmellituss = userprofile.diabetesmellitus;
                    String broncialasthmas = userprofile.broncialasthma;
                    String immunodificiencys  = userprofile.immunodeficiency;
                    String cancers = userprofile.cancer;
                    String others = userprofile.other;
                    String appointment = userprofile.appointment;
                    String birthdate = userprofile.birthdate;
                    String approveds = userprofile.approve;



                    stringBuilder.append(firstname);
                    stringBuilder.append(" "+middlename);
                    stringBuilder.append(" "+lastname);


                    username.setText(stringBuilder);

                    sex.setText(sexs);
                    address.setText(addresss);
                    contact.setText(contactnos);
                    appointments.setText(appointment);
                    appr.setText(approveds);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UsersProfile.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });



    }
}