package com.group2.bakunapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class NewAdaptor extends FirebaseRecyclerAdapter<Users,NewAdaptor.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public NewAdaptor(@NonNull FirebaseRecyclerOptions<Users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Users model) {

        holder.name.setText(model.firstname);
        holder.date.setText(model.appointment);
        holder.approve.setText(model.approve);
        if (model.isadmin != null){
            holder.view.setVisibility(View.INVISIBLE);
        }
        if (model.isuser != null){
            holder.view.setVisibility(View.VISIBLE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.userinfo))
                        .setExpanded(true, 1450)
                        .create();

//                dialogPlus.show();

                View view = dialogPlus.getHolderView();

                TextView name = view.findViewById(R.id.namedatas);
                TextView age = view.findViewById(R.id.agedatas);
                TextView birthdates = view.findViewById(R.id.birthdatedatas);
                TextView sex = view.findViewById(R.id.sexdatas);
                TextView address = view.findViewById(R.id.addressdatas);
                TextView contactno = view.findViewById(R.id.contactnodatas);

                TextView category= view.findViewById(R.id.categorydatas);
                TextView selectedid = view.findViewById(R.id.selectediddatas);
                TextView idno = view.findViewById(R.id.idnumberdatas);
                TextView civilstatus = view.findViewById(R.id.civilstatusdatas);
                TextView pregnancy = view.findViewById(R.id.pregnancydatas);
                TextView menstrual = view.findViewById(R.id.menstrualdatas);
                TextView drugallergy = view.findViewById(R.id.drugallergydatas);
                TextView foodallergy = view.findViewById(R.id.foodallergydatas);
                TextView insectallergy = view.findViewById(R.id.insectallergydatas);
                TextView laxerrubberallergy =  view.findViewById(R.id.laxerrubberallergydatas);
                TextView moldallergy = view.findViewById(R.id.moldallergydatas);
                TextView petalallergy =  view.findViewById(R.id.petallergydatas);
                TextView pollenallergy = view.findViewById(R.id.pollenallergydatas);
                TextView comorbidities = view.findViewById(R.id.comorbiditiesdatas);
                TextView hypertension = view.findViewById(R.id.hypertensiondatas);
                TextView heartdisease = view.findViewById(R.id.heartdiseasedatas);
                TextView kidneydisease = view.findViewById(R.id.kidneydiseasedatas);
                TextView diabetesmellitus = view.findViewById(R.id.diabetesmellitusdatas);
                TextView broncialasthma = view.findViewById(R.id.broncialasthmadatas);
                TextView immunodificiency = view.findViewById(R.id.immunodeficiencydatas);
                TextView cancer = view.findViewById(R.id.cancerdatas);
                TextView other = view.findViewById(R.id.otherdiseasedatas);
                TextView approval = view.findViewById(R.id.apprup);
                EditText uappointments = view.findViewById(R.id.dose1st);
                EditText seconddose = view.findViewById(R.id.dose2nd);
                EditText booster = view.findViewById(R.id.boosterup);
                EditText location = view.findViewById(R.id.locationup);
                EditText vaccinetype = view.findViewById(R.id.vaccvalue);
                Button approvebtn = view.findViewById(R.id.apprbtn);
                Button notapprovebtn = view.findViewById(R.id.notapprbtn);


                Button updbtn = view.findViewById(R.id.svbtn);
                String lastname,mname,fname;

                StringBuilder stringBuilder = new StringBuilder();
                fname = model.firstname;
                mname = model.middlename;
                lastname = model.lastname;

                stringBuilder.append(fname);
                stringBuilder.append(" "+mname);
                stringBuilder.append(" "+lastname);


                name.setText(stringBuilder);
                address.setText(model.getAddress());
                age.setText(model.getAge());
                birthdates.setText(model.getBirthdate());
                sex.setText(model.getSex());
                category.setText(model.getCategory());
                selectedid.setText(model.getSelectedid());
                idno.setText(model.getIdnumber());
                civilstatus.setText(model.getCivilstatus());
                pregnancy.setText(model.getPregnancy());
                menstrual.setText(model.getMenstrual());

                drugallergy.setText(model.getDrugallergy());
                foodallergy.setText(model.getFoodallergy());
                insectallergy.setText(model.getInsectallergy());
                laxerrubberallergy.setText(model.getLaxerrubberallergy());
                moldallergy.setText(model.getMoldallergy());
                petalallergy.setText(model.getPetalalleregy());
                pollenallergy.setText(model.getPollenallergy());
                comorbidities.setText(model.getComorbidities());
                hypertension.setText(model.getHypertension());
                heartdisease.setText(model.getHeartdisease());
                kidneydisease.setText(model.getKidneydisease());
                diabetesmellitus.setText(model.getDiabetesmellitus());
                broncialasthma.setText(model.getBroncialasthma());
                immunodificiency.setText(model.getImmunodeficiency());
                cancer.setText(model.getCancer());
                other.setText(model.getOther());
                contactno.setText(model.getContactno());
                uappointments.setText(model.getAppointment());
                approval.setText(model.getApprove());
                seconddose.setText(model.getSecond());
                booster.setText(model.getBooster());
                location.setText(model.getPlace());
                vaccinetype.setText(model.getType());


                if (model.isadmin != null){
                    dialogPlus.dismiss();
                }
                if (model.isuser != null){
                    dialogPlus.show();
                }

                approvebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String apprtext="Approved";
                        Toast.makeText(holder.approve.getContext(), "Approved", Toast.LENGTH_SHORT).show();
                        approval.setText(apprtext);
                    }
                });

                notapprovebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String notapprtext ="Not Approved";
                        Toast.makeText(holder.approve.getContext(), "Not Approved", Toast.LENGTH_SHORT).show();
                        approval.setText(notapprtext);
                    }
                });

                updbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("approve",approval.getText().toString());
                        map.put("appointment",uappointments.getText().toString());
                        map.put("second",seconddose.getText().toString());
                        map.put("booster",booster.getText().toString());
                        map.put("place",location.getText().toString());
                        map.put("type",vaccinetype.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(position).getKey())
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(holder.approve.getContext(), "Successfully Save", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.approve.getContext(), "Saving Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView name,date,approve;
        Button view;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textusername);
            date = itemView.findViewById(R.id.textappointments);
            approve = itemView.findViewById(R.id.textappr);
            view = itemView.findViewById(R.id.btnview);
        }


    }

}
