package com.catnip.covidapp.ui.home.covidspread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.covidapp.R
import com.catnip.covidapp.base.GenericViewModelFactory
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.datasource.CovidDataSource
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseResponse
import com.catnip.covidapp.data.network.services.CovidApiServices
import com.catnip.covidapp.databinding.FragmentCovidSpreadBinding

class CovidSpreadFragment : Fragment(), CovidSpreadContract.View {
    private lateinit var binding: FragmentCovidSpreadBinding
    private lateinit var viewModel: CovidSpreadViewModel
    private lateinit var adapter: CasesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCovidSpreadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    override fun initView() {
        setupSwipeRefresh()
        setupList()
    }

    override fun initViewModel() {
        val apiServices = CovidApiServices.getInstance()
        apiServices?.let {
            val dataSource = CovidDataSource(it)
            val repository = CovidSpreadRepository(dataSource)
            viewModel = GenericViewModelFactory(CovidSpreadViewModel(repository))
                .create(CovidSpreadViewModel::class.java)
        }
        viewModel.covidData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showError(false, null)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showError(false, null)
                    showContent(true)
                    it.data?.first?.let { data ->
                        bindDataHeader(data)
                    }
                    it.data?.second?.let { data ->
                        setListData(data)
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showError(true, it.message)
                    showContent(false)
                }
            }
        })
        viewModel.getCovidSpreadData()
    }

    override fun showContent(isContentVisible: Boolean) {
        binding.srlContent.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }

    override fun showLoading(isLoading: Boolean) {
        binding.pbLoading.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        binding.tvErrorMessage.visibility = if (isErrorEnabled) View.VISIBLE else View.GONE
        binding.tvErrorMessage.text = msg
    }

    override fun setupSwipeRefresh() {
        binding.srlContent.setOnRefreshListener {
            binding.srlContent.isRefreshing = false
            viewModel.getCovidSpreadData()
        }
    }

    override fun setupList() {
        adapter = CasesListAdapter {}
        binding.rvCovidSpread.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CovidSpreadFragment.adapter
        }
    }

    override fun setListData(data: List<TotalCaseProvinceResponse>) {
        adapter.items = data
    }

    override fun bindDataHeader(data: TotalCaseResponse) {
        binding.tvTotalPositive.text =
            getString(R.string.text_total_positive_case, data.positif)
        binding.tvTotalCured.text =
            getString(R.string.text_total_recovered_case, data.sembuh)
        binding.tvTotalTreated.text =
            getString(R.string.text_total_treated_case, data.dirawat)
        binding.tvTotalDeath.text =
            getString(R.string.text_total_death_case, data.meninggal)
    }

}