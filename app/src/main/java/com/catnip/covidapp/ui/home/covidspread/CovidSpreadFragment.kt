package com.catnip.covidapp.ui.home.covidspread

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.catnip.covidapp.R
class CovidSpreadFragment : Fragment(), CovidSpreadContract.View {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_covid_spread, container, false)
    }

    override fun initView() {

    }

    override fun initViewModel() {

    }
}