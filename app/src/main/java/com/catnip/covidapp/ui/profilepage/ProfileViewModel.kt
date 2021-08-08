package com.catnip.covidapp.ui.profilepage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.covidapp.base.Resource
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProfileViewModel(private val repository: ProfileRepository) : ViewModel(),
    ProfileContract.ViewModel {
    val profileDataResponse = MutableLiveData<Resource<UserResponse>>()
    val changeProfileResponse = MutableLiveData<Resource<UserResponse>>()

    override fun saveProfileData(email: String, username: String) {
        //todo : set payload to loading
        changeProfileResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.editProfileData(email, username)
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        changeProfileResponse.value = Resource.Success(response.data)
                    } else {
                        changeProfileResponse.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    changeProfileResponse.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getProfileData() {
        //todo : set payload to loading
        profileDataResponse.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getProfileData()
                viewModelScope.launch(Dispatchers.Main) {
                    //checking if response success
                    if (response.success) {
                        profileDataResponse.value = Resource.Success(response.data)
                    } else {
                        profileDataResponse.value = Resource.Error(response.errors)
                    }
                }
            } catch (e: Exception) {
                //set value to error message
                viewModelScope.launch(Dispatchers.Main) {
                    profileDataResponse.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

}