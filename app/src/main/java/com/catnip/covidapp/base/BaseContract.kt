package com.catnip.covidapp.base

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface BaseContract {
    interface ViewModel{

    }
    interface View{
        fun initView()
        fun initViewModel()
    }
}