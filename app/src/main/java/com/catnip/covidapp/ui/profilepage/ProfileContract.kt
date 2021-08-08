package com.catnip.covidapp.ui.profilepage

import com.catnip.covidapp.base.BaseContract
import com.catnip.covidapp.data.network.entity.responses.authentification.UserResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ProfileContract {
    interface ViewModel : BaseContract.ViewModel {
        fun saveProfileData(email: String, username: String)
        fun getProfileData()
    }

    interface View : BaseContract.View {
        fun setToolbar()
        fun setOnClick()
        fun showLoading(isLoadingVisible: Boolean)
        fun showContent(isContentVisible: Boolean)
        fun showToast(msg : String?)
        fun checkFormValidation(): Boolean
        fun setDataToField(data: UserResponse)
    }
}