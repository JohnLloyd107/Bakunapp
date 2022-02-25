package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Info extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    public String fullname;
    private StringBuilder stringBuilder;

    private String userid;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();
        mAuth = FirebaseAuth.getInstance();

        final TextView name = (TextView) findViewById(R.id.namedata);
        final TextView age = (TextView) findViewById(R.id.agedata);
        final TextView birthdates = (TextView)  findViewById(R.id.birthdatedata);
        final TextView sex = (TextView) findViewById(R.id.sexdata);
        final TextView address = (TextView) findViewById(R.id.addressdata);
        final TextView contactno = (TextView) findViewById(R.id.contactnodata);
        final  TextView appointments = (TextView)  findViewById(R.id.appointmentdata);

        final TextView category= (TextView) findViewById(R.id.categorydata);
        final TextView selectedid = (TextView) findViewById(R.id.selectediddata);
        final TextView idno = (TextView) findViewById(R.id.idnumberdata);
        final TextView civilstatus = (TextView) findViewById(R.id.civilstatusdata);
        final TextView pregnancy = (TextView) findViewById(R.id.pregnancydata);
        final TextView menstrual = (TextView) findViewById(R.id.menstrualdata);
        final TextView drugallergy = (TextView) findViewById(R.id.drugallergydata);
        final TextView foodallergy = (TextView) findViewById(R.id.foodallergydata);
        final TextView insectallergy = (TextView) findViewById(R.id.insectallergydata);
        final TextView laxerrubberallergy = (TextView) findViewById(R.id.laxerrubberallergydata);
        final TextView moldallergy = (TextView) findViewById(R.id.moldallergydata);
        final TextView petalallergy = (TextView) findViewById(R.id.petallergydata);
        final TextView pollenallergy = (TextView) findViewById(R.id.pollenallergydata);
        final TextView comorbidities = (TextView) findViewById(R.id.comorbiditiesdata);
        final TextView hypertension = (TextView) findViewById(R.id.hypertensiondata);
        final TextView heartdisease = (TextView) findViewById(R.id.heartdiseasedata);
        final TextView kidneydisease = (TextView) findViewById(R.id.kidneydiseasedata);
        final TextView diabetesmellitus = (TextView) findViewById(R.id.diabetesmellitusdata);
        final TextView broncialasthma = (TextView) findViewById(R.id.broncialasthmadata);
        final TextView immunodificiency = (TextView) findViewById(R.id.immunodeficiencydata);
        final TextView cancer = (TextView) findViewById(R.id.cancerdata);
        final TextView other = (TextView) findViewById(R.id.otherdiseasedata);

        ImageView lgoutbtn = (ImageView) findViewById(R.id.lgoutbtn);
        ImageView updbtn = (ImageView) findViewById(R.id.updatebtn);

        stringBuilder = new StringBuilder();

        updbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Info.this,Update.class));
            }
        });

        lgoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Info.this,Dashboard.class));
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



                    stringBuilder.append(firstname);
                    stringBuilder.append(" "+middlename);
                    stringBuilder.append(" "+lastname);











                    name.setText(stringBuilder);
                    birthdates.setText(birthdate);
                    age.setText(ages);
                    sex.setText(sexs);
                    address.setText(addresss);
                    contactno.setText(contactnos);
                    appointments.setText(appointment);
                    category.setText(categorys);
                    selectedid.setText(selectedids);
                    idno.setText(idnos);
                    civilstatus.setText(civilstatuss);
                    pregnancy.setText(pregnancys);
                    menstrual.setText(menstruals);
                    drugallergy.setText(drugallergys);
                    foodallergy.setText(foodallergys);
                    insectallergy.setText(insectallergys);
                    laxerrubberallergy.setText(laxerrubberallergys);
                    moldallergy.setText(moldallergys);
                    petalallergy.setText(petalallergys);
                    pollenallergy.setText(pollenallergys);
                    comorbidities.setText(comorbiditiess);
                    hypertension.setText(hypertensions);
                    heartdisease.setText(hearthdiseases);
                    kidneydisease.setText(kidneydiseases);
                    diabetesmellitus.setText(diabetesmellituss);
                    broncialasthma.setText(broncialasthmas);
                    immunodificiency.setText(immunodificiencys);
                    cancer.setText(cancers);
                    other.setText(others);

                }
                else {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(Info.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Info.this,MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Info.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });







    }
    @Override
    protected void onStart(){
        super.onStart();
        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userprofile = snapshot.getValue(Users.class);

                if (userprofile != null){






                }
                else if (userprofile == null){
                    Toast.makeText(Info.this, "Logout", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Info.this,MainActivity.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Info.this, "Connection Timeout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Info.this,MainActivity.class));



            }
        });
    }
}