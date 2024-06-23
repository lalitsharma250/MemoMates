package com.example.journalapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn,createAccountBtn;
    private EditText emailET,passwordET;
//    Firebase Auth :
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        Firebase Authentication :
        firebaseAuth=FirebaseAuth.getInstance();
        loginBtn=findViewById(R.id.email_signin);
        createAccountBtn=findViewById(R.id.create_account);
        emailET=findViewById(R.id.email);
        passwordET=findViewById(R.id.password);
        createAccountBtn.setOnClickListener(v->{
            Intent i=new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(i);
        });
        loginBtn.setOnClickListener(v->{
            loginEmailPasswordUser(emailET.getText().toString().trim(),passwordET.getText().toString().trim());
        });
    }
    private void loginEmailPasswordUser(String email,String password){
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    currentUser=firebaseAuth.getCurrentUser();
                    Intent i=new Intent(MainActivity.this,JournalListActivity.class);
                    startActivity(i);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "No User Found !!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(this, "some of the creadentials are missing !!", Toast.LENGTH_SHORT).show();
        }
    }
}