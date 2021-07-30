package com.catnip.covidapp.ui.splashscreen

import com.catnip.covidapp.base.BaseContract

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SplashScreenContract {
    interface ViewModel : BaseContract.ViewModel{
        fun getSyncData()
    }
    interface View : BaseContract.View{
        fun navigateToLogin()
        fun navigateToHome()
        fun checkLogin()
        fun deleteSessionLogin()
    }
}