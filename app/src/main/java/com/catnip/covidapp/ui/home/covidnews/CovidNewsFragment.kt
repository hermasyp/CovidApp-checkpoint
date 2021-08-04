package com.catnip.covidapp.ui.home.covidnews

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.covidapp.R
import com.catnip.covidapp.base.GenericViewModelFactory
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.datasource.CovidDataSource
import com.catnip.covidapp.data.network.datasource.NewsDataSource
import com.catnip.covidapp.data.network.entity.responses.kawalcorona.TotalCaseProvinceResponse
import com.catnip.covidapp.data.network.entity.responses.news.News
import com.catnip.covidapp.data.network.services.CovidApiServices
import com.catnip.covidapp.data.network.services.NewsApiServices
import com.catnip.covidapp.databinding.FragmentCovidNewsBinding
import com.catnip.covidapp.ui.home.covidspread.CasesListAdapter
import com.catnip.covidapp.ui.home.covidspread.CovidSpreadRepository
import com.catnip.covidapp.ui.home.covidspread.CovidSpreadViewModel

class CovidNewsFragment : Fragment(),CovidNewsContract.View {
    private lateinit var binding : FragmentCovidNewsBinding
    private lateinit var adapter: NewsListAdapter
    private lateinit var viewModel: CovidNewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCovidNewsBinding.inflate(inflater,container,false)
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
        val apiServices = NewsApiServices.getInstance()
        apiServices?.let {
            val dataSource = NewsDataSource(it)
            val repository = CovidNewsRepository(dataSource)
            viewModel = GenericViewModelFactory(CovidNewsViewModel(repository))
                .create(CovidNewsViewModel::class.java)
        }
        viewModel.newsData.observe(viewLifecycleOwner, {
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
                    it.data?.let { data ->
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
        viewModel.getNews()
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
            viewModel.getNews()
        }
    }

    override fun setupList() {
        adapter = NewsListAdapter {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = it.webUrl?.toUri() // Uri.parse(it.webUrl)
            context?.startActivity(intent)
        }
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CovidNewsFragment.adapter
        }
    }

    override fun setListData(data: List<News>) {
        adapter.items = data
    }


}