package com.example.itnetwork.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.itnetwork.databinding.ActivityMainBinding;
import com.example.itnetwork.dtos.request.LoginDTORequest;
import com.example.itnetwork.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding loginBinding;
    private MainActivityViewModel loginViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = loginBinding.getRoot();
        setContentView(viewRoot);

        loginViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        loginViewModel.getLoginData().observe(this, loginDTOResponse -> {
            if(loginDTOResponse==null){
                Toast.makeText(MainActivity.this, "Login fail: Invalid username or password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Welcome "+loginDTOResponse.getData().getRole()+" login successful", Toast.LENGTH_SHORT).show();
            }
        });

        loginBinding.btnLogin.setOnClickListener(view -> onLogin());


    }

    public void onLogin() {
        String username = loginBinding.etloginEmail.getText().toString();
        String password = loginBinding.etloginPassword.getText().toString();

        if(username.trim().isEmpty()){
            loginBinding.etloginEmail.setError("Need username");
            loginBinding.etloginEmail.requestFocus();
            return;
        }
        if(password.trim().isEmpty()){
            loginBinding.etloginPassword.setError("Need password");
            loginBinding.etloginPassword.requestFocus();
            return;
        }
        LoginDTORequest login = new LoginDTORequest(username,password);
        Log.d(TAG, "onLogin: "+login.getEmail()+" "+login.getPassword());
        loginViewModel.Login(login);
    }
}