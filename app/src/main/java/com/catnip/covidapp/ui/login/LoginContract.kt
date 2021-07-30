package com.catnip.covidapp.ui.login

import com.catnip.covidapp.base.BaseContract
import com.catnip.covidapp.data.network.entity.requests.authentification.LoginRequest

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface LoginContract {
    interface ViewModel : BaseContract.ViewModel{
        fun loginUser(loginRequest: LoginRequest)
    }
    interface View : BaseContract.View{
        fun setToolbar()
        fun setOnClick()
        fun navigateToHome()
        fun navigateToRegister()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation() : Boolean
        fun saveSessionLogin(authToken : String)
    }
}