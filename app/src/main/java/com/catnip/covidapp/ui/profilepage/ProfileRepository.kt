package com.catnip.covidapp.ui.profilepage

import com.catnip.covidapp.data.network.datasource.BinarDataSource
import com.catnip.covidapp.data.network.entity.responses.authentification.BaseAuthResponse
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ProfileRepository(private val binarDataSource: BinarDataSource) {

    suspend fun getProfileData(): BaseAuthResponse<UserResponse, String> {
        return binarDataSource.getProfileData()
    }

    suspend fun editProfileData(
        email: String,
        username: String
    ): BaseAuthResponse<UserResponse, String> {
        return binarDataSource.putProfileData(email, username)
    }
}