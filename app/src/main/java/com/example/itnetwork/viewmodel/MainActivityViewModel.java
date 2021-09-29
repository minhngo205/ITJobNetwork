package com.example.itnetwork.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.itnetwork.dtos.request.LoginDTORequest;
import com.example.itnetwork.dtos.response.LoginDTOResponse;
import com.example.itnetwork.network.ITNetworkApiInterface;
import com.example.itnetwork.network.RetroInstance;
import com.example.itnetwork.view.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = "MainActivityViewModel";

    private final MutableLiveData<LoginDTOResponse> LoginData;

    public MainActivityViewModel() {
        LoginData = new MutableLiveData<>();

    }

    public LiveData<LoginDTOResponse> getLoginData(){
        return LoginData;
    }

    public void Login(LoginDTORequest user){
        ITNetworkApiInterface apiInterface = RetroInstance.getRetroInstance().create(ITNetworkApiInterface.class);
        Log.d(TAG, "Login: "+user.getEmail()+" "+user.getPassword());
        Call<LoginDTOResponse> call = apiInterface.userLogin(user);
        call.enqueue(new Callback<LoginDTOResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginDTOResponse> call, @NonNull Response<LoginDTOResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body().getData());
                    LoginData.postValue(response.body());
                } else {
                    LoginData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginDTOResponse> call, @NonNull Throwable t) {
                LoginData.postValue(null);
            }
        });
    }
}