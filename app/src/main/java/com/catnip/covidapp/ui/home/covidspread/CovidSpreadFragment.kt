package com.catnip.covidapp.ui.home.covidspread

import android.os.Bundle
import android.os.Environment
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.catnip.covidapp.utils.Constants

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener


class CovidSpreadFragment : Fragment(), CovidSpreadContract.View {
    private lateinit var binding: FragmentCovidSpreadBinding
    private lateinit var viewModel: CovidSpreadViewModel
    private lateinit var adapter: CasesListAdapter

    private lateinit var player: YouTubePlayer
    private var isYoutubeHeaderExpanded = false

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
        setupYoutube()
        setClickEventYoutubeHeader()
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


    override fun setClickEventYoutubeHeader() {
        binding.cvHeaderYoutubePlayer.setOnClickListener {
            if(isYoutubeHeaderExpanded){
                //tutup
                isYoutubeHeaderExpanded = false
                binding.youtubePlayerView.visibility = View.GONE
                binding.tvHeaderYoutubePlayer.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_collapse,0)
                player.pause()
            }else{
                //buka
                isYoutubeHeaderExpanded = true
                binding.youtubePlayerView.visibility = View.VISIBLE
                binding.tvHeaderYoutubePlayer.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_expand,0)
                playVideo()
            }

        }
    }

    override fun setupSwipeRefresh() {
        binding.srlContent.setOnRefreshListener {
            binding.srlContent.isRefreshing = false
            viewModel.getCovidSpreadData()
            player.pause()
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            player.pause()
        }
    }

    override fun setupYoutube() {
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                this@CovidSpreadFragment.player = youTubePlayer
            }
        })
        binding.youtubePlayerView.addFullScreenListener(object : YouTubePlayerFullScreenListener{
            override fun onYouTubePlayerEnterFullScreen() {
                //hide toolbar

            }

            override fun onYouTubePlayerExitFullScreen() {
                TODO("Not yet implemented")
                //show toolbar
            }
        })
    }

    override fun playVideo() {
        player.loadVideo(Constants.VIDEO_ID_KOMPAS_LIVE,0F)
    }

}