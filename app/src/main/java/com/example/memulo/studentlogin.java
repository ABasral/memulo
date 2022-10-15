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

public class studentlogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailf,passf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        mAuth = FirebaseAuth.getInstance();
        emailf = (EditText) findViewById(R.id.sEmail);
        passf = (EditText) findViewById(R.id.spass);

        Button login = (Button) findViewById(R.id.sbutton);

        Button register = (Button) findViewById(R.id.sregister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(studentlogin.this, studentregister.class);
                // myIntent.putExtra("key", value);
                studentlogin.this.startActivity(myIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailf.getText().toString();
                final String password = passf.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(studentlogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent myIntent = new Intent(studentlogin.this, studenthome.class);
                                    // myIntent.putExtra("key", value);
                                    studentlogin.this.startActivity(myIntent);

                                } else {

                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(studentlogin.this, "Authentication failed.",
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
            Intent myIntent = new Intent(studentlogin.this, studenthome.class);
           // myIntent.putExtra("key", value);
            studentlogin.this.startActivity(myIntent);
        }
    }
}