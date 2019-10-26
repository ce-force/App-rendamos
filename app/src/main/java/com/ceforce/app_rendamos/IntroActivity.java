package com.ceforce.app_rendamos;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        addSlide(AppIntroFragment.newInstance("","Paso 1. Inicio de sesión",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("","Paso 2.",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("","Paso 3",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
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
