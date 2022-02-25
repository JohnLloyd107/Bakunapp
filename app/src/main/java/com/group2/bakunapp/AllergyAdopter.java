package com.group2.bakunapp;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllergyAdopter extends RecyclerView.Adapter<AllergyAdopter.MyViewHolder> {

    private Context aContext;
    private List<AllergyModelClass> aData;

    public AllergyAdopter(Context aContext, List<AllergyModelClass> aData) {
        this.aContext = aContext;
        this.aData = aData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater Inflator = LayoutInflater.from(aContext);
        v = Inflator.inflate(R.layout.allergyitem, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(aData.get(position).getId());
        holder.name.setText(aData.get(position).getName());
        holder.description.setText(aData.get(position).getDescription());
        Glide.with(aContext)
                .load(aData.get(position).getImage())
                .into(holder.image);



        holder.lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.lbtn.getContext())
                        .setContentHolder(new ViewHolder(R.layout.allergyex))
                        .setExpanded(true, 1450)
                        .create();

                View view = dialogPlus.getHolderView();

                AppCompatCheckBox list1 = view.findViewById(R.id.list1);
                AppCompatCheckBox list2 = view.findViewById(R.id.list2);
                AppCompatCheckBox list3 = view.findViewById(R.id.list3);
                AppCompatCheckBox list4 = view.findViewById(R.id.list4);
                EditText otheritems = view.findViewById(R.id.otherlist);
                TextView aname = view.findViewById(R.id.aname);
                Button list = view.findViewById(R.id.listbtn);



                dialogPlus.show();
                list1.setText(aData.get(position).getList1());
                list2.setText(aData.get(position).getList2());
                list3.setText(aData.get(position).getList3());
                list4.setText(aData.get(position).getList4());
                aname.setText(aData.get(position).getName());


                String userid;
                FirebaseUser user;
                DatabaseReference reference;

                user = FirebaseAuth.getInstance().getCurrentUser();
                reference = FirebaseDatabase.getInstance().getReference("Users");
                userid = user.getUid();


                String a = aname.getText().toString();
                String a1 = list1.getText().toString();
                String a2 = list2.getText().toString();
                String a3 = list3.getText().toString();
                String a4 = list4.getText().toString();



                if (a1.isEmpty()){
                    list1.setVisibility(View.GONE);
                }
                if (a2.isEmpty()){
                    list2.setVisibility(View.GONE);
                }
                if (a3.isEmpty()){
                    list3.setVisibility(View.GONE);
                }
                if (a4.isEmpty()){
                    list4.setVisibility(View.GONE);
                }

                list1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list2.setChecked(false);
                        list3.setChecked(false);
                        list4.setChecked(false);
                    }
                });
                list2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list1.setChecked(false);
                        list3.setChecked(false);
                        list4.setChecked(false);
                    }
                });
                list3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list2.setChecked(false);
                        list1.setChecked(false);
                        list4.setChecked(false);
                    }
                });
                list4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list2.setChecked(false);
                        list3.setChecked(false);
                        list1.setChecked(false);
                    }
                });
                otheritems.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list4.setChecked(false);
                        list2.setChecked(false);
                        list3.setChecked(false);
                        list1.setChecked(false);
                    }
                });





                list.setOnClickListener(new View.OnClickListener() {
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
                                String place = "No Yet Available", second = "Not Yet Available", booster = "Not Yet Available", type = "No Vaccine yet";

                                String appointment = null;
                                String birthdate = userprofile.birthdate;



                                if (list1.isChecked()) {
                                    if ("Food Allergy".equals(a)){
                                        foodallergy = list1.getText().toString();

                                    }
                                    if ("Drug Allergy".equals(a)){
                                        drugallergy = list1.getText().toString();
                                    }
                                    if ("Insect Allergy".equals(a)){
                                        insectallergy = list1.getText().toString();
                                    }
                                    if ("Laxer/Rubber Allergy".equals(a)){
                                        laxerrubberallergy = list1.getText().toString();
                                    }
                                    if ("Mold Allergy".equals(a)){
                                        moldallergy = list1.getText().toString();
                                    }
                                    if ("Pet Allergy".equals(a)){
                                        petalalleregy = list1.getText().toString();
                                    }
                                    if ("Pollen Allergy".equals(a)){
                                        pollenallergy = list1.getText().toString();
                                    }
                                    if ("Comorbidities".equals(a)){
                                        comorbidities = list1.getText().toString();
                                    }



                                }

                                else if (list2.isChecked()) {
                                    if ("Food Allergy".equals(a)){
                                        foodallergy = list2.getText().toString();

                                    }
                                    if ("Drug Allergy".equals(a)){
                                        drugallergy = list2.getText().toString();
                                    }
                                    if ("Insect Allergy".equals(a)){
                                        insectallergy = list2.getText().toString();
                                    }
                                    if ("Laxer/Rubber Allergy".equals(a)){
                                        laxerrubberallergy = list2.getText().toString();
                                    }
                                    if ("Mold Allergy".equals(a)){
                                        moldallergy = list2.getText().toString();
                                    }
                                    if ("Pet Allergy".equals(a)){
                                        petalalleregy = list2.getText().toString();
                                    }
                                    if ("Pollen Allergy".equals(a)){
                                        pollenallergy = list2.getText().toString();
                                    }
                                    if ("Comorbidities".equals(a)){
                                        comorbidities = list2.getText().toString();
                                    }


                                }

                                else if (list3.isChecked()) {
                                    if ("Food Allergy".equals(a)){
                                        foodallergy = list3.getText().toString();
                                    }
                                    if ("Drug Allergy".equals(a)){
                                        drugallergy = list3.getText().toString();
                                    }
                                    if ("Insect Allergy".equals(a)){
                                        insectallergy = list3.getText().toString();
                                    }
                                    if ("Laxer/Rubber Allergy".equals(a)){
                                        laxerrubberallergy = list3.getText().toString();
                                    }
                                    if ("Mold Allergy".equals(a)){
                                        moldallergy = list3.getText().toString();
                                    }
                                    if ("Pet Allergy".equals(a)){
                                        petalalleregy = list3.getText().toString();
                                    }
                                    if ("Pollen Allergy".equals(a)){
                                        pollenallergy = list3.getText().toString();
                                    }
                                    if ("Comorbidities".equals(a)){
                                        comorbidities = list3.getText().toString();
                                    }


                                }

                                else if (list4.isChecked()) {
                                    if ("Food Allergy".equals(a)){
                                        foodallergy = list4.getText().toString();
                                    }
                                    if ("Drug Allergy".equals(a)){
                                        drugallergy = list4.getText().toString();
                                    }
                                    if ("Insect Allergy".equals(a)){
                                        insectallergy = list4.getText().toString();
                                    }
                                    if ("Laxer/Rubber Allergy".equals(a)){
                                        laxerrubberallergy = list4.getText().toString();
                                    }
                                    if ("Mold Allergy".equals(a)){
                                        moldallergy = list4.getText().toString();
                                    }
                                    if ("Pet Allergy".equals(a)){
                                        petalalleregy = list4.getText().toString();
                                    }
                                    if ("Pollen Allergy".equals(a)){
                                        pollenallergy = list4.getText().toString();
                                    }
                                    if ("Comorbidities".equals(a)){
                                        comorbidities = list4.getText().toString();
                                    }


                                }

                                if (!list1.isChecked() && !list2.isChecked() && !list3.isChecked() && !list4.isChecked() ) {
                                    if ("Food Allergy".equals(a)){

                                        foodallergy = otheritems.getText().toString();
                                    }
                                    if ("Drug Allergy".equals(a)){

                                        drugallergy = otheritems.getText().toString();
                                    }
                                    if ("Insect Allergy".equals(a)){

                                        insectallergy = otheritems.getText().toString();
                                    }
                                    if ("Laxer/Rubber Allergy".equals(a)){

                                        laxerrubberallergy = otheritems.getText().toString();
                                    }
                                    if ("Mold Allergy".equals(a)){

                                        moldallergy = otheritems.getText().toString();
                                    }
                                    if ("Pet Allergy".equals(a)){

                                        petalalleregy = otheritems.getText().toString();
                                    }
                                    if ("Pollen Allergy".equals(a)){

                                        pollenallergy = otheritems.getText().toString();
                                    }
                                    if ("Comorbidities".equals(a)){

                                        comorbidities = otheritems.getText().toString();
                                    }

                                }

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
                                        Toast.makeText(holder.lbtn.getContext(), "Allergy Save", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });

                            }
                                }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });







                    }


                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return aData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,name,description;
        ImageView image;
        Button lbtn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.allergyid);
            name = itemView.findViewById(R.id.allergyname);
            description = itemView.findViewById(R.id.allergydescription);
            image = itemView.findViewById(R.id.allergyimage);
            lbtn = itemView.findViewById(R.id.lbtn);

        }
    }
}
