package com.catnip.covidapp.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class RegisterViewModel(private val repository: RegisterRepository) : ViewModel(),
    RegisterContract.ViewModel {
    val registerResponse = MutableLiveData<Resource<UserResponse>>()

    override fun registerUser(registerRequest: RegisterRequest) {
        //todo : set payload to loading
        registerResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.postRegisterData(registerRequest)
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        registerResponse.value = Resource.Success(response.data)
                    } else {
                        registerResponse.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    registerResponse.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }


}