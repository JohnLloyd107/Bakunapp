package com.group2.bakunapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Appointment extends AppCompatActivity {

    private String userid;
    private FirebaseUser user;
    private DatabaseReference reference;

    CalendarView calendar;
    TextView dateview,yearview,appointment_data;
    Button setappointment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userid = user.getUid();

        calendar = (CalendarView) findViewById(R.id.calendarView);
        dateview = (TextView) findViewById(R.id.dateview);
        yearview = (TextView) findViewById(R.id.yearview);
        setappointment = (Button)  findViewById(R.id.button);
        appointment_data = (TextView) findViewById(R.id.appointment_data);

        String currentdate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

        int cdate = Calendar.getInstance().get(Calendar.MONTH);
        String cdates = null;
        switch (cdate) {

            case 1:
                cdates = "Jan";
                break;
            case 2:
                cdates = "Feb";
                break;
            case 3:
                cdates = "Mar";
                break;
            case 4:
                cdates = "April";
                break;
            case 5:
                cdates = "May";
                break;
            case 6:
                cdates = "June";
                break;
            case 7:
                cdates = "July";
                break;
            case 8:
                cdates = "Aug";
                break;
            case 9:
                cdates = "Sept";
                break;
            case 10:
                cdates = "Oct";
                break;
            case 11:
                cdates = "Nov";
                break;
            case 12:
                cdates = "Dec";
                break;
        }

        int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int y = Calendar.getInstance().get(Calendar.YEAR);
        String dy = String.valueOf(d);
        String yr = String.valueOf(y);
        String current = cdates+ " "+ dy + ", "+ yr;
        dateview.setText(currentdate);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String months = null;



                switch (month) {
                    case 0:
                        months = "Jan";
                        break;
                    case 1:
                        months = "Feb";
                        break;
                    case 2:
                        months = "Mar";
                        break;
                    case 3:
                        months = "April";
                        break;
                    case 4:
                        months = "May";
                        break;
                    case 5:
                        months = "June";
                        break;
                    case 6:
                        months = "July";
                        break;
                    case 7:
                        months = "Aug";
                        break;
                    case 8:
                        months = "Sept";
                        break;
                    case 9:
                        months = "Oct";
                        break;
                    case 10:
                        months = "Nov";
                        break;
                    case 11:
                        months = "Dec";
                        break;
                }

                String date = months + "," + dayOfMonth;
                String years = String.valueOf(year);
                dateview.setText(date);
                yearview.setText(years);

                String dates = months + " " + dayOfMonth + ", " + year;
                appointment_data.setText(dates);





            }
        });

        calendar.setMinDate(System.currentTimeMillis()- 1000);



        setappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users userprofile = snapshot.getValue(Users.class);

                        if (userprofile != null){

                            String lastname = userprofile.lastname;
                            String firstname = userprofile.firstname;
                            String middlename= userprofile.middlename;
                            String suffix = userprofile.suffix;


                            String age = userprofile.age;
                            String sex = userprofile.sex;
                            String address = userprofile.address;
                            String contactno = userprofile.contactno;

                            String category = userprofile.category;
                            String selectedid= userprofile.selectedid;
                            String civilstatus = userprofile.civilstatus;
                            String idnumber = userprofile.idnumber;
                            String pregnancy = userprofile.pregnancy;
                            String menstrual = userprofile.menstrual;
                            String drugallergy = userprofile.drugallergy;
                            String foodallergy = userprofile.foodallergy;
                            String insectallergy = userprofile.insectallergy;
                            String laxerrubberallergy = userprofile.laxerrubberallergy;
                            String moldallergy = userprofile.moldallergy;
                            String petalalleregy = userprofile.petalalleregy;
                            String pollenallergy = userprofile.pollenallergy;
                            String comorbidities = userprofile.comorbidities;
                            String hypertension = userprofile.hypertension;
                            String heartdisease = userprofile.heartdisease;
                            String kidneydisease = userprofile.kidneydisease;
                            String diabetesmellitus = userprofile.diabetesmellitus;
                            String broncialasthma = userprofile.broncialasthma;
                            String immunodeficiency  = userprofile.immunodeficiency;
                            String cancer = userprofile.cancer;
                            String other = userprofile.other;
                            String approve = userprofile.approve;
                            String date = userprofile.date;
                            String email= null, password = null, isadmin = null,isuser = "1";
                            String place = "Not Yet Available", second = "Not Yet Available", booster = "Not Yet Available", type = "No Vaccine yet";

                            String appointment = appointment_data.getText().toString().trim();
                            String birthdate = userprofile.birthdate;



                            Users user = new Users(lastname,firstname,middlename,suffix,age,
                                    sex,address,contactno,email,password,
                                    category,selectedid,idnumber,civilstatus,pregnancy,
                                    menstrual,drugallergy,foodallergy,insectallergy,laxerrubberallergy,
                                    moldallergy,petalalleregy,pollenallergy,comorbidities,hypertension,
                                    heartdisease,kidneydisease,diabetesmellitus,broncialasthma,immunodeficiency,
                                    cancer,other,approve,date,appointment,birthdate,isadmin,isuser,place,second,booster, type);

                            FirebaseDatabase.getInstance().getReference(Users.class.getSimpleName())
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){

                                        Toast.makeText(Appointment.this, "Registered", Toast.LENGTH_LONG).show();
                                        Toast.makeText(Appointment.this, "Successfully Submit", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Appointment.this,Dashboard.class));
                                        finish();

                                    }
                                }
                            });


                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Appointment.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }










            });
        }
        });


    }
}