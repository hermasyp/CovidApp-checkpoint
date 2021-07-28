package com.catnip.covidapp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.covidapp.R

class RegisterActivity : AppCompatActivity(), RegisterContract.View {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}