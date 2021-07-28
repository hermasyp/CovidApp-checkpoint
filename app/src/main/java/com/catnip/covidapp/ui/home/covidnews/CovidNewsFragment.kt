package com.catnip.covidapp.ui.home.covidnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catnip.covidapp.R

class CovidNewsFragment : Fragment(),CovidNewsContract.View {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_news, container, false)
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }

}