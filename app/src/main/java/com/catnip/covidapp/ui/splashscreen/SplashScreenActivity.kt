package com.catnip.covidapp.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.covidapp.R

class SplashScreenActivity : AppCompatActivity(), SplashScreenContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}