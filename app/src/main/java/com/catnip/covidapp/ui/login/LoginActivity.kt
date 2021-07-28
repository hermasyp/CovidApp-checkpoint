package com.catnip.covidapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.covidapp.R

class LoginActivity : AppCompatActivity(),LoginContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}