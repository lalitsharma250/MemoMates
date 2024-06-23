package com.example.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity {
//    widgets :
    private EditText password_create ,username_create ,email_create ;
    private Button createBtn;
//    firebase Auth :
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser currentUser;
//    Establishing connection with firebase :
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference collectionReference =db.collection("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        password_create=findViewById(R.id.password_create);
        username_create =findViewById(R.id.username_create_ET);
        email_create=findViewById(R.id.email_create);
        createBtn=findViewById(R.id.acc_signUp_btn);
        firebaseAuth=FirebaseAuth.getInstance();
//        authStateListener listens for changes in the authentication state and responds  accordingly  to the state changes :
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser=firebaseAuth.getCurrentUser();
                if(currentUser!=null){
//                    User is already logged in :
                }else{
//                    user is signed out :
                }
            }
        };
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(username_create.getText().toString()) && !TextUtils.isEmpty(email_create.getText().toString()) && !TextUtils.isEmpty(password_create.getText().toString())){
                    String email=email_create.getText().toString().trim();
                    String password=password_create.getText().toString().trim();
                    String username=username_create.getText().toString().trim();
                    createUserEmailAccount(email,password,username);
                }else{
                    Toast.makeText(SignUpActivity.this, "Some of the fields are empty!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUserEmailAccount(String email, String password, String username) {
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this, "User created Successfully !!", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(SignUpActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }
            });
        }
    }


}
