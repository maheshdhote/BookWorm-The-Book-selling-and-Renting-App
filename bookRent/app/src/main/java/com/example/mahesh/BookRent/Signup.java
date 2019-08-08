package com.example.mahesh.BookRent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Signup extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button signup;
    private Uri filepath;
    private ImageView imageView;
    private EditText emailText,passwordText,confirmText;

    private String emailStr="",passStr="",confirmStr="";

    private Button choose;
    static final int PICK_IMAGE_REQUEST = 71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        signup = findViewById(R.id.signupID);


        choose =findViewById(R.id.chooseIDD);
        emailText=findViewById(R.id.emailID);
        passwordText = findViewById(R.id.passwordID);
        confirmText=findViewById(R.id.confirmPassID);
        imageView = findViewById(R.id.imageView);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailStr = emailText.getText().toString().trim();
                passStr = passwordText.getText().toString().trim();
                confirmStr=confirmText.getText().toString().trim();

                if(emailStr.isEmpty())
                {
                    //Toast.makeText(Signup.this,"Email is Empty",Toast.LENGTH_LONG).show();
                    emailText.setError("Email required");
                    emailText.requestFocus();

                }
                else if (passStr.isEmpty())
                {
                   // Toast.makeText(Signup.this,"Password is Empty",Toast.LENGTH_LONG).show();
                    passwordText.setError("Password required");
                    passwordText.requestFocus();

                }

                else if(confirmStr.isEmpty())
                {
                    //Toast.makeText(Signup.this,"Confirm Password",Toast.LENGTH_LONG).show();
                    confirmText.setError("password reqiured");
                    confirmText.requestFocus();

                }
                else if(!confirmStr.equals(passStr))
                {
                   // Toast.makeText(Signup.this,"Passord Not matching",Toast.LENGTH_LONG).show();
                    confirmText.setError("password not match");
                    confirmText.requestFocus();

                }
                else
                {
                    signup(emailStr,passStr);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Signup.this,LoginPage.class);
                            startActivity(intent);
                        }
                    },3000);
                }

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void signup(String email,String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(Signup.this, "Authentication Successfully.",
                            Toast.LENGTH_LONG).show();

                    Toast.makeText(Signup.this, "Directing to Login.",
                            Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(Signup.this, "Authentication Unsuccessfully.",
                            Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
            {
                filepath =data.getData();
                try
                {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                    imageView.setImageBitmap(bitmap);
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }
}
