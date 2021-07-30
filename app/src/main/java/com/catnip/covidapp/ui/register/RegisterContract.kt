package com.catnip.covidapp.ui.register

import com.catnip.covidapp.base.BaseContract
import com.catnip.covidapp.data.network.entity.requests.authentification.RegisterRequest

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface RegisterContract {
    interface ViewModel : BaseContract.ViewModel {
        fun registerUser(registerRequest: RegisterRequest)
    }

    interface View : BaseContract.View {
        fun setToolbar()
        fun setOnClick()
        fun setLoadingState(isLoadingVisible: Boolean)
        fun checkFormValidation() : Boolean
        fun navigateToLogin()
    }
}