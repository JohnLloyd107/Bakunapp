package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText lastname_input,firstname_input,middlename_input,suffix_input,address_input,contact_no_input,email_input,password_input,idno_input;
    private EditText pregnancy_input,menstrual_input,drugallergy_input,foodallergy_input,insect_input,luxerrubber_input,mold_input,petal_input,pollen_input,comorbidities_input;
    private EditText hyper_input,heart_input,kidney_input,diabes_input,broncial_input,immuno_input,cancer_input,other_input;
    private TextView categ_input,approve_input,date_input, birthdate_input,age_input;

    DatePickerDialog.OnDateSetListener setListener;


    private Button sbtbtn;
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private Spinner selected_id_input,sex_input;
    Spinner civil_input;
    private String selectedid,civilstatus,sex,userid;



    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();







        lastname_input = (EditText) findViewById(R.id.lastname_input);
        firstname_input = (EditText) findViewById(R.id.firstname_input);
        middlename_input = (EditText) findViewById(R.id.middlename_input);
        suffix_input = (EditText) findViewById(R.id.suffix_input);
        birthdate_input = (TextView) findViewById(R.id.birthdate_input);
        age_input = (TextView) findViewById(R.id.age_input);
        sex_input = (Spinner) findViewById(R.id.sex_input);
        address_input = (EditText) findViewById(R.id.address_input);
        contact_no_input = (EditText) findViewById(R.id.contactno_input);

        categ_input = (TextView) findViewById(R.id.category_input);
        selected_id_input= (Spinner) findViewById(R.id.selectedid_input);
        idno_input = (EditText) findViewById(R.id.idnumber_input);
        civil_input = (Spinner) findViewById(R.id.civilstatus_input);
        pregnancy_input = (EditText) findViewById(R.id.pregnancy_input);
        menstrual_input = (EditText) findViewById(R.id.menstrual_input);

        hyper_input = (EditText) findViewById(R.id.hypertension_input);
        heart_input = (EditText) findViewById(R.id.heartdisease_input);
        kidney_input = (EditText) findViewById(R.id.kidneydisease_input);
        diabes_input = (EditText) findViewById(R.id.diabetesmellitus_input);
        broncial_input = (EditText) findViewById(R.id.broncialasthma_input);
        immuno_input = (EditText) findViewById(R.id.immunodeficiency_input);
        cancer_input = (EditText) findViewById(R.id.cancer_input);
        other_input = (EditText) findViewById(R.id.otherdisease_input);
        approve_input = (TextView) findViewById(R.id.appr);
        date_input = (TextView) findViewById(R.id.date);




        sbtbtn = (Button) findViewById(R.id.submitbtn);

        Bundle bundle = getIntent().getExtras();

        String category = bundle.getString("category");

        categ_input.setText(category);

        String lastnames = bundle.getString("lastname");
        String firstnames = bundle.getString("firstname");
        String middlenames = bundle.getString("middlename");
        String suffixs = bundle.getString("suffix");

        lastname_input.setText(lastnames);
        firstname_input.setText(firstnames);
        middlename_input.setText(middlenames);
        suffix_input.setText(suffixs);

        Calendar calendar = Calendar.getInstance();
        int year =  calendar.get(Calendar.YEAR);
        int month =  calendar.get(Calendar.MONTH);
        int day =  calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());


        birthdate_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int cYear = c.get(Calendar.YEAR);
                int cMonth = c.get(Calendar.MONTH);
                int cDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(v.getContext(), datePickerListener, cYear, cMonth, cDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }
        });






        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);

        List<String> ID = new ArrayList<>();
        ID.add(0,"Please select ID...");
        ID.add("School ID");
        ID.add("Philhealth ID");
        ID.add("Driver License ID");
        ID.add("Postal ID");
        ID.add("Voter's ID");
        ID.add("Senior Citizen ID");

        List<String> Gender = new ArrayList<>();
        Gender.add(0,"...");
        Gender.add("Male");
        Gender.add("Female");



        List<String> Status = new ArrayList<>();
        Status.add(0,"Please select Civil Status...");
        Status.add("Single");
        Status.add("Married");
        Status.add("Divorced");
        Status.add("Widowed");

        ArrayAdapter<String> myAdapter;
        myAdapter = new ArrayAdapter<>(Register.this, android.R.layout.simple_spinner_item,ID);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(Register.this, android.R.layout.simple_spinner_item, Status);
        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(Register.this, android.R.layout.simple_spinner_item, Gender);

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selected_id_input.setAdapter(myAdapter);

        selected_id_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select ID")){

                }
                else {
                    selectedid = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        civil_input.setAdapter(myAdapter2);

        civil_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Civil Status")){

                }
                else {
                    civilstatus = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sex_input.setAdapter(myAdapter3);

        sex_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Sex")){

                }
                else {
                    sex = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();


        int testByte= 1;
        while( testByte == 0){
            databaseReference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Users userprofile = snapshot.getValue(Users.class);

                    if (userprofile != null){
                        String food = userprofile.foodallergy;

                        foodallergy_input.setText(food);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }











        sbtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String none ="N/A";





                String lastname = lastname_input.getText().toString().trim();
                String firstname = firstname_input.getText().toString().trim();
                String middlename = middlename_input.getText().toString().trim();
                String suffix = suffix_input.getText().toString().trim();

                String age = age_input.getText().toString().trim();

                String address = address_input.getText().toString().trim();
                String contactno= contact_no_input.getText().toString().trim();
                String email = null,password= null,appointment= null;

                String category = categ_input.getText().toString().trim();

                String idnumber = idno_input.getText().toString().trim();

                String pregnancy = pregnancy_input.getText().toString().trim();
                String menstrual = menstrual_input.getText().toString().trim();
                String drugallergy = "N/A";
                String foodallergy = "N/A";
                String insectallergy = "N/A";
                String laxerrubberallergy = "N/A";
                String moldallergy = "N/A";
                String petalalleregy = "N/A";
                String pollenallergy = "N/A";
                String comorbidities = "N/A";
                String hypertension = hyper_input.getText().toString().trim();
                String heartdisease = heart_input.getText().toString().trim();
                String kidneydisease = kidney_input.getText().toString().trim();
                String diabetesmellitus = diabes_input.getText().toString().trim();
                String broncialasthma = broncial_input.getText().toString().trim();
                String immunodeficiency = immuno_input.getText().toString().trim();
                String cancer = cancer_input.getText().toString().trim();
                String other = other_input.getText().toString().trim();
                String approve = approve_input.getText().toString().trim();
                String date = date_input.getText().toString().trim();
                String birthdate = birthdate_input.getText().toString().trim();
                String isadmin = null, isuser = "1";
                String place = "No Location Yet", second = "Not Yet Available", booster = "Not Yet Available", type = "No Vaccine yet";

                if (lastname.isEmpty()){
                    lastname_input.setError("LastName is required!");
                    lastname_input.requestFocus();
                    return;
                }

                if (firstname.isEmpty()){
                    firstname_input.setError("FirstName is required!");
                    firstname_input.requestFocus();
                    return;
                }

                if (middlename.isEmpty()){
                    middlename_input.setError("MiddleName is required!");
                    middlename_input.requestFocus();
                    return;
                }

                if (selectedid.isEmpty()){
                    Toast.makeText(Register.this, "Please select ID", Toast.LENGTH_SHORT).show();
                    selected_id_input.requestFocus();
                    return;
                }

                if (birthdate.isEmpty()){
                    birthdate_input.setError("Birthdate is required!");
                    birthdate_input.requestFocus();
                    return;
                }



                if (age.isEmpty()){
                    age_input.setError("Age is required!");
                    age_input.requestFocus();
                    return;
                }




                if (address.isEmpty()){
                    address_input.setError("Address is required!");
                    address_input.requestFocus();
                    return;
                }

                if (contactno.isEmpty()){
                    contact_no_input.setError("Contact No. is required!");
                    contact_no_input.requestFocus();
                    return;
                }




                if (category.isEmpty()){
                    categ_input.setError("Category is required!");
                    categ_input.requestFocus();
                    return;
                }



                if (idnumber.isEmpty()){
                    idno_input.setError("ID No. is required!");
                    idno_input.requestFocus();
                    return;
                }



                if (pregnancy.isEmpty()){
                    pregnancy_input.setError("Pregnancy is required!");
                    pregnancy_input.requestFocus();
                    return;
                }

                if (menstrual.isEmpty()){
                    menstrual_input.setError("Menstrual is required!");
                    menstrual_input.requestFocus();
                    return;
                }

                if (drugallergy.isEmpty()){
                    drugallergy_input.setError("Drug Allergy required!");
                    drugallergy_input.requestFocus();
                    return;
                }



                if (insectallergy.isEmpty()){
                    insect_input.setError("Insect Allergy is required!");
                    insect_input.requestFocus();
                    return;
                }

                if (laxerrubberallergy.isEmpty()){
                    luxerrubber_input.setError("Laxer/Rubber Allergy is required!");
                    luxerrubber_input.requestFocus();
                    return;
                }

                if (moldallergy.isEmpty()){
                    mold_input.setError("Mold Allergy is required!");
                    mold_input.requestFocus();
                    return;
                }

                if (petalalleregy.isEmpty()){
                    petal_input.setError("Petal Allergy is required!");
                    petal_input.requestFocus();
                    return;
                }

                if (pollenallergy.isEmpty()){
                    pollen_input.setError("Pollen Allergy is required!");
                    pollen_input.requestFocus();
                    return;
                }

                if (comorbidities.isEmpty()){
                    comorbidities_input.setError("Comorbidities is required!");
                    comorbidities_input.requestFocus();
                    return;
                }

                if (hypertension.isEmpty()){
                    hyper_input.setError("Hypertension is required!");
                    hyper_input.requestFocus();
                    return;
                }

                if (heartdisease.isEmpty()){
                    heart_input.setError("Heart Disease is required!");
                    heart_input.requestFocus();
                    return;
                }

                if (kidneydisease.isEmpty()){
                    kidney_input.setError("Kidney Disease is required!");
                    kidney_input.requestFocus();
                    return;
                }

                if (diabetesmellitus.isEmpty()){
                    diabes_input.setError("Diabetesmellitus is required!");
                    diabes_input.requestFocus();
                    return;
                }

                if (broncialasthma.isEmpty()){
                    broncial_input.setError("Broncial Asthma is required!");
                    broncial_input.requestFocus();
                    return;
                }

                if (immunodeficiency.isEmpty()){
                    immuno_input.setError("Immunodeficiency is required!");
                    immuno_input.requestFocus();
                    return;
                }

                if (cancer.isEmpty()){
                    cancer_input.setError("Cancer is required!");
                    cancer_input.requestFocus();
                    return;
                }

                if (other.isEmpty()){
                    other_input.setError("Other Disease is required!");
                    other_input.requestFocus();
                    return;
                }



                alert.setMessage("Are you sure you want to Submit?");
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                        Users user = new Users(lastname,firstname,middlename,suffix,age,
                                                    sex,address,contactno,email,password,
                                                    category,selectedid,idnumber,civilstatus,pregnancy,
                                                    menstrual,drugallergy,foodallergy,insectallergy,laxerrubberallergy,
                                                    moldallergy,petalalleregy,pollenallergy,comorbidities,hypertension,
                                                    heartdisease,kidneydisease,diabetesmellitus,broncialasthma,immunodeficiency,
                                                    cancer,other,approve,date,appointment,birthdate,isadmin,isuser,place,second,booster,type);

                                            FirebaseDatabase.getInstance().getReference(Users.class.getSimpleName())
                                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()){


                                                        Toast.makeText(Register.this, "Successfully Submit", Toast.LENGTH_LONG).show();
                                                        startActivity(new Intent(Register.this,AllergyList.class));


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

    @Override
    public void onBackPressed(){

        AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
        alert.setTitle("Discard");
        alert.setMessage("Are you sure you want to Exit?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = getIntent().getExtras();

                String lastnames = bundle.getString("lastname");
                String firstnames = bundle.getString("firstname");
                String middlenames = bundle.getString("middlename");
                String suffixs = bundle.getString("suffix");





                Intent nextact = new Intent(Register.this, Category.class);
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

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format =  new SimpleDateFormat("dd MMM yyyy").format(c.getTime());
            birthdate_input.setText(format);
            age_input.setText(Integer.toString(calculatedAge(c.getTimeInMillis())));
        }
    };

    int calculatedAge(long date){
        Calendar cd = Calendar.getInstance();
        cd.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - cd.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_MONTH) < cd.get(Calendar.DAY_OF_MONTH)){
            age--;

        }

        return age;
    }


}