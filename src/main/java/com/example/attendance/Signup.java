package com.example.attendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    TextView Login;
    EditText Uname, emailTextView, passwordTextView, confirmpass, Phone;
    Button signup;
    private final static int RC_SIGN_IN = 1;
    private final static String TAG = "GOOGLEAUTH";
    protected GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    protected ProgressDialog pd;
    protected SignInButton sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_in_button= (SignInButton) findViewById(R.id.sign_in_button);

        firebaseAuth = FirebaseAuth.getInstance();
        // Configure Google Sign In
        GoogleSignInOptions gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });



        signup = (Button)findViewById(R.id.signup);
        Uname=(EditText)findViewById(R.id.Username);
        emailTextView=(EditText)findViewById(R.id.Gmail);
        passwordTextView=(EditText)findViewById(R.id.password);
        confirmpass=(EditText)findViewById(R.id.cnfrmpass);
        Phone=(EditText)findViewById(R.id.number);
        Login=(TextView)findViewById(R.id.login);
        FirebaseAuth  firebaseAuth=FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        LoginAcitivity.class);
                startActivity(i);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               registerNewUser();
                Intent j = new Intent(getApplicationContext(),
                        MyBoard.class);
                startActivity(j);               /* firebaseAuth.createUserWithEmailAndPassword(emailTextView.getText().toString(),
                        passwordTextView.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            firebaseAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(Signup.this, "Register Sucessfully, Please Check your gmail for verification", Toast.LENGTH_SHORT).show();
                                        emailTextView.setText("");
                                        passwordTextView.setText("");
                                    }
                                    else
                                    {
                                        Toast.makeText(Signup.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                        }
                        else {
                            Toast.makeText(Signup.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
*/
            }
            private void registerNewUser() {
                String email, password,username,ConPass,phone;
                username=Uname.getText().toString();
                email = emailTextView.getText().toString();
                password = passwordTextView.getText().toString();
                ConPass=confirmpass.getText().toString();
                phone=Phone.getText().toString();
                Phone.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(validateMobile(Phone.getText().toString())){
                            signup.setEnabled(true);

                        }
                        else
                        {
                            signup.setEnabled(false);
                            Phone.setError("Invalid Mobile Number");

                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                if (username.isEmpty()) {
                    Uname.setError("please enter User Name");
                    Uname.requestFocus();
                }
                else if (email.isEmpty()) {
                    emailTextView.setError("please enter email Id");
                    emailTextView.requestFocus();
                }
                else if (phone.isEmpty()) {
                    Phone.setError("please enter Phone number");
                    Phone.requestFocus();
                }
                else if (password.isEmpty()) {
                    passwordTextView.setError("please enter Password");
                    passwordTextView.requestFocus();
                }
                else if (ConPass.isEmpty()) {
                    confirmpass.setError("please enter Confirm Password");
                    confirmpass.requestFocus();
                }
                else if (!password.equals(ConPass)) {
                    Toast.makeText(Signup.this,"Confirm Password isn't match with Password",Toast.LENGTH_SHORT).show();

                }

                else if(username.isEmpty() && email.isEmpty() && phone.isEmpty() && password.isEmpty() && ConPass.isEmpty() ){
                    Toast.makeText(Signup.this,"fields are empty",Toast.LENGTH_SHORT).show();

                }
            }


        });


    }
    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                //pd.dismiss();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    //startActivity(new Intent(getApplicationContext(), SignOut.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    //finish();
                    //pd.dismiss();
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                    //updateUI(null);

                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean validateMobile(String input){
        Pattern p= Pattern.compile("[6-9][0-9][9]");
        Matcher m=p.matcher(input);
        return m.matches();

}


}
