package com.example.memulo;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class teacherregister extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailf,passf,confirm_passf;
    Button stnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherregister);
        emailf = (EditText) findViewById(R.id.temail);
        passf = (EditText) findViewById(R.id.tpass);
        confirm_passf = (EditText) findViewById(R.id.tconfirmpass);


        Button stnext = (Button) findViewById(R.id.tnext);
        stnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailf.getText().toString();
                final String pass = passf.getText().toString();
                final String confirm_pass = confirm_passf.getText().toString();
                if(!email.equals(""))
                {
                    if(pass.equals(confirm_pass))
                    {

                        mAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(teacherregister.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(teacherregister.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(teacherregister.this,"Passwords doesn't match",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(teacherregister.this,"Email can't be empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
            startActivity(getIntent());
        }
    }
}