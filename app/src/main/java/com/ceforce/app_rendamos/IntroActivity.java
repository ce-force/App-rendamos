package com.ceforce.app_rendamos;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ceforce.app_rendamos.login.LoginActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.activity_app_intro_activity);
        addSlide(AppIntroFragment.newInstance("First page","This is the First Page Description",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Second page","This is the Second Page Description",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance("Third page","This is the Third Page Description",R.drawable.logo03, ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary)));
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
