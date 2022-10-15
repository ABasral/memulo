package com.example.memulo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class teacherlogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailf,passf;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);
        mAuth = FirebaseAuth.getInstance();
        emailf = (EditText) findViewById(R.id.tEmail);
        passf = (EditText) findViewById(R.id.tpass);

        login = (Button) findViewById(R.id.tbutton);
        String email = emailf.getText().toString();
        String password = passf.getText().toString();
        Button register = (Button) findViewById(R.id.sregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(teacherlogin.this, teacherregister.class);
                // myIntent.putExtra("key", value);
                teacherlogin.this.startActivity(myIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(teacherlogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent myIntent = new Intent(teacherlogin.this, teacherhome.class);
                                    // myIntent.putExtra("key", value);
                                    teacherlogin.this.startActivity(myIntent);
                                } else {

                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(teacherlogin.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent myIntent = new Intent(teacherlogin.this, teacherhome.class);
            // myIntent.putExtra("key", value);
            teacherlogin.this.startActivity(myIntent);
        }
    }
}