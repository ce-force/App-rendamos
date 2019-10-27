package com.ceforce.app_rendamos;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ceforce.app_rendamos.login.LoginActivity;
import com.ceforce.app_rendamos.login.SaveSharedPreference;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_app_intro_activity);
        if (SaveSharedPreference.IsLoged(this)){
            //Toast.makeText(this, "Previamente logeado", Toast.LENGTH_LONG).show();
            Intent intSignUp = new Intent(this, MainActivity.class);
            startActivity(intSignUp);
        }
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Revise su conexión a wifi")
                    .setTitle("Wifi no detectado");

            AlertDialog dialog = builder.create();

            dialog.show();
        }

        addSlide(AppIntroFragment.newInstance("","Paso 1. Inicio de sesión",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("","Paso 2. Seleccione un estudiante de la lista.",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("","Paso 3. Realice el Test",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

    }

}
