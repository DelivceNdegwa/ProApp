package com.example.proapp;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.proapp.utils.PreferenceStorage;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class AuthenticationActivity extends AppCompatActivity {
    TextInputEditText inputEmail, inputConfirm, inputPassword, inputNumber, inputName;
    ImageView imgProfile;

    final int GALLERY_REQUEST_CODE = 100;

    PreferenceStorage preferenceStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        preferenceStorage = new PreferenceStorage(this);

        Button btnCreateAccount, btnLogin;
        btnCreateAccount = findViewById(R.id.button_create_account);
        btnLogin = findViewById(R.id.button_login);
        imgProfile = findViewById(R.id.img_attachment);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyPermissions();
            }
        });

        inputConfirm = findViewById(R.id.input_confirm);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        inputNumber = findViewById(R.id.input_number);
        inputName = findViewById(R.id.input_name);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInput()){

                    String name = inputName.getText().toString();

                    preferenceStorage.saveUserData(
                            name,
                            inputEmail.getText().toString(),
                            inputNumber.getText().toString(),
                            inputPassword.getText().toString()
                    );

                    preferenceStorage.setLoggedInStatus(true);

                    Intent goToHome = new Intent(AuthenticationActivity.this, HomeActivity.class);
                    startActivity(goToHome);
                    finish();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });
    }

    private void showLoginDialog() {
        TextInputEditText inputLoginUserName, inputLoginPassword;
        Button btnLogin, btnReset;

        final BottomSheetDialog loginDialog = new BottomSheetDialog(this);
        loginDialog.setContentView(R.layout.login_dialog);
        inputLoginUserName = loginDialog.findViewById(R.id.input_login_name);
        inputLoginPassword = loginDialog.findViewById(R.id.input_login_password);
        btnLogin = loginDialog.findViewById(R.id.button_login);
        btnReset = loginDialog.findViewById(R.id.button_reset);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputLoginUserName.getText().toString().trim();
                String password = inputLoginPassword.getText().toString().trim();

                if(validateLoginInputs(inputLoginUserName, inputLoginPassword)
                        && preferenceStorage.authenticate(username, password)
                ){
                    preferenceStorage.setLoggedInStatus(true);
                    Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Snackbar.make(view, "Wrong Credentials", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
            }
        });

        loginDialog.show();
    }

    private boolean validateLoginInputs(TextInputEditText inputName, TextInputEditText inputPassword) {
        boolean result = true;

        if(inputName.getText().toString().trim().isEmpty()){
            inputName.setError("Please enter a name");
            result=false;
        }
        else if(inputPassword.getText().toString().trim().isEmpty()){
            inputName.setError("Please enter a password");
            result = false;
        }
        else{
            result = true;
        }

        return result;

    }

    public boolean validateInput(){
        boolean response = false;
        if(inputName.getText().toString().trim().isEmpty()){
            inputName.setError("Please enter your name");
        }
        else if(inputEmail.getText().toString().trim().isEmpty()){
            inputEmail.setError("Please enter your email");
        }
        else if(!inputEmail.getText().toString().contains("@")){
            inputEmail.setError("A email field must have an '@'");
        }
        else if(inputNumber.getText().toString().trim().isEmpty()){
            inputNumber.setError("Please enter your number");
        }
        else if(inputPassword.getText().toString().trim().isEmpty()){
            inputPassword.setError("Please enter a password");
        }
        else if(inputConfirm.getText().toString().trim().isEmpty()){
            inputConfirm.setError("Please enter a password confirm");
        }
        else if(!inputConfirm.getText().toString().contentEquals(inputPassword.getText().toString().trim())){
            inputConfirm.setError("Your passwords do not match");
        }
        else{
            response = true;
        }
        return response;
    }

    public void verifyPermissions(){
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if(
                ContextCompat.checkSelfPermission(
                        this.getApplicationContext(), permissions[0])
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                        this.getApplicationContext(), permissions[1])
                        == PackageManager.PERMISSION_GRANTED
        ){
            pickImageFromGallery();
        }
        else{
            ActivityCompat.requestPermissions(this, permissions, 134);
        }
    }

    private void pickImageFromGallery() {
        Intent pickFromGallery = new Intent(Intent.ACTION_PICK);
        pickFromGallery.setType("image/*");
        String[] mimeTypes = {
               "image/jpeg",
               "image/png",
               "image/pdf",        };

        try{
            startActivityForResult(pickFromGallery, GALLERY_REQUEST_CODE);
        }catch(ActivityNotFoundException e){
            //
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            imgProfile.setImageURI(uri);
        }
    }
}