package com.example.mahesh.BookRent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginPage extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private Button login,signupText;
    private GoogleSignIn gsignin;
    private EditText emailText,passwordText;
    private RadioGroup radioGroup;
    private RadioButton buyerRadio,sellerRadio;
    private ProgressDialog progressDialog;
    private TextView statusText;
    private String emailStr="",passStr="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.loginButton);
        emailText = findViewById(R.id.emailID);
        passwordText = findViewById(R.id.passwordID);
        radioGroup = findViewById(R.id.radioGroup);
        buyerRadio =findViewById(R.id.buRadio);
        sellerRadio = findViewById(R.id.seRadio);
        firebaseAuth = FirebaseAuth.getInstance();
        statusText = findViewById(R.id.statusView);
        signupText = findViewById(R.id.signup);
       // gsignin = (GoogleSignIn)findViewById(R.id.`);


        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPage.this,otpmain.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 emailStr = emailText.getText().toString().trim();
                 passStr = passwordText.getText().toString().trim();

                 if(emailStr.isEmpty())
                 {

                        // statusText.setTextColor(Color.RED);
                         emailText.setError("Email reqiured");
                         emailText.requestFocus();
                         //statusText.setText("Email is Missing");


                     }

                     else if( passStr.isEmpty())
                     {
                        // statusText.setTextColor(Color.RED);
                         passwordText.setError("Password reqiured");
                         passwordText.requestFocus();
                     }

                   else {
                     statusText.setTextColor(Color.RED);
                     signIn(emailStr, passStr);

                 }

            }
        });


    }

    private void signIn(final String email, String password)
    {
        progressDialog = new ProgressDialog(LoginPage.this);
        progressDialog.setTitle("Authenticating");
        progressDialog.setMessage("Just A Moment");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)  {


                if (!task.isSuccessful())
                {
                    progressDialog.dismiss();
                    try
                    {
                        throw task.getException();

                    }catch (FirebaseAuthInvalidUserException e1)
                    {
                        statusText.setTextColor(Color.RED);
                        statusText.setText("Invalid Email");


                        statusText.requestFocus();
                        e1.printStackTrace();
                        return;

                    }
                    catch (FirebaseAuthInvalidCredentialsException e2)
                    {
                        statusText.setTextColor(Color.RED);
                        statusText.setText("Invalid Password");
                        statusText.requestFocus();
                        e2.printStackTrace();
                        return;
                    }
                    catch (FirebaseNetworkException e3)
                    {
                        statusText.setTextColor(Color.RED);
                        e3.printStackTrace();
                        statusText.setText("No Internet");
                        return;

                    }catch (Exception e4)
                    {
                        e4.printStackTrace();
                        statusText.setTextColor(Color.RED);
                        statusText.setText("Exception Occured");
                        return;
                    }
                }
                else
                {
                    progressDialog.dismiss();


                    statusText.setTextColor(Color.GREEN);
                    statusText.setText("Successfully Signed In");
                    if(buyerRadio.isChecked())
                    {
                        Intent buyerintent = new Intent(LoginPage.this,Buyer.class);
                        startActivity(buyerintent);
                    }
                    else if(sellerRadio.isChecked())
                    {
                        Intent sellerintent = new Intent(LoginPage.this,Seller.class);
                        startActivity(sellerintent);
                    }
                    else
                    {
                        Toast.makeText(LoginPage.this,"Select Buyer or Seller",Toast.LENGTH_SHORT);
                    }


                }
            }
        });
    }

}
