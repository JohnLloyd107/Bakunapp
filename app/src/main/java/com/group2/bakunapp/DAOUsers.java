package com.group2.bakunapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUsers {
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userid;
    public DAOUsers()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();

        databaseReference = db.getReference(Users.class.getSimpleName());






    }
    public Task<Void> add(Users user)
    {

        return databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);

    }
}
