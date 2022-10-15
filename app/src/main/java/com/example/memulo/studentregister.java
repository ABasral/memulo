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

public class studentregister extends AppCompatActivity {

    EditText emailf,passf,confirm_passf;
    Button stnext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_studentregister);

        emailf = (EditText) findViewById(R.id.stemail);
        passf = (EditText) findViewById(R.id.stpass);
        confirm_passf = (EditText) findViewById(R.id.stconfirmpass);


        stnext = (Button) findViewById(R.id.stnext);
        stnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailf.getText().toString();
                final String pass = passf.getText().toString();

                final String confirm_pass = confirm_passf.getText().toString();
                Log.d(TAG, "##########################");
                Log.d(TAG, email);

                    if(pass.equals(confirm_pass))
                    {
                        mAuth.createUserWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(studentregister.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d(TAG, "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            Intent myIntent = new Intent(studentregister.this, studentform.class);
                                            // myIntent.putExtra("key", value);
                                            studentregister.this.startActivity(myIntent);

                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(studentregister.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(studentregister.this,"Passwords doesn't match",Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

}