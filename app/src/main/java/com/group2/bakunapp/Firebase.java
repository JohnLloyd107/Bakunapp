package com.group2.bakunapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {

    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userid;
    public Firebase()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        databaseReference = db.getReference(helperclass.class.getSimpleName());

        userid = user.getUid();



    }
    public Task<Void> add(helperclass users)
    {

        return databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(users);

    }
}
