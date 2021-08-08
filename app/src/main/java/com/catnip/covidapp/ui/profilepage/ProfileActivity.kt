package com.catnip.covidapp.ui.profilepage

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catnip.covidapp.R
import com.catnip.covidapp.base.GenericViewModelFactory
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.local.sharedpreference.SessionPreference
import com.catnip.covidapp.data.network.datasource.BinarDataSource
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import com.catnip.covidapp.data.network.services.BinarApiServices
import com.catnip.covidapp.databinding.ActivityProfileBinding
import com.catnip.covidapp.utils.StringUtils

class ProfileActivity : AppCompatActivity(), ProfileContract.View {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var sessionPreference: SessionPreference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun setToolbar() {
        supportActionBar?.title = getString(R.string.title_toolbar_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setOnClick() {
        binding.btnChangeProfileData.setOnClickListener {
            if (checkFormValidation()) {
                viewModel.saveProfileData(
                    email = binding.etEmail.text.toString(),
                    username = binding.etUsername.text.toString()
                )
            }
        }
    }

    override fun showContent(isContentVisible: Boolean) {
        binding.groupContent.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }

    override fun showLoading(isLoadingVisible: Boolean) {
        binding.pbLoading.visibility = if (isLoadingVisible) View.VISIBLE else View.GONE
    }

    override fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun checkFormValidation(): Boolean {
        val email = binding.etEmail.text.toString()
        val username = binding.etUsername.text.toString()
        var isFormValid = true
        //for checking is email empty
        when {
            email.isEmpty() -> {
                isFormValid = false
                binding.tilEmail.isErrorEnabled = true
                binding.tilEmail.error = getString(R.string.error_email_empty)
            }
            StringUtils.isEmailValid(email).not() -> {
                isFormValid = false
                binding.tilEmail.isErrorEnabled = true
                binding.tilEmail.error = getString(R.string.error_email_invalid)
            }
            else -> {
                binding.tilEmail.isErrorEnabled = false
            }
        }
        //for checking is username empty
        if (username.isEmpty()) {
            isFormValid = false
            binding.tilUsername.isErrorEnabled = true
            binding.tilUsername.error = getString(R.string.error_username_empty)
        } else {
            binding.tilUsername.isErrorEnabled = false
        }
        return isFormValid
    }

    override fun setDataToField(data: UserResponse) {
        binding.apply {
            etEmail.setText(data.email)
            etUsername.setText(data.username)
        }
    }

    override fun initView() {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setOnClick()
        initViewModel()

    }

    override fun initViewModel() {
        sessionPreference = SessionPreference(this)
        val apiService = BinarApiServices.getInstance(sessionPreference)
        apiService?.let {
            val dataSource = BinarDataSource(it)
            val repository = ProfileRepository(dataSource)
            viewModel = GenericViewModelFactory(ProfileViewModel(repository))
                .create(ProfileViewModel::class.java)
        }
        viewModel.profileDataResponse.observe(this, { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    response.data?.let {
                        setDataToField(it)
                    }
                }
                is Resource.Error -> {
                    showToast(response.message)
                    showLoading(false)
                    showContent(false)
                }
            }
        })
        viewModel.changeProfileResponse.observe(this, { response ->
            when (response) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    response.data?.let {
                        setDataToField(it)
                    }
                    showToast("Change Profile data Success")
                }
                is Resource.Error -> {
                    showToast(response.message)
                    showLoading(false)
                    showContent(true)
                }
            }
        })
        viewModel.getProfileData()
    }
}