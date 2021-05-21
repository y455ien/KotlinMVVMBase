package com.example.kotlinmvvmbase.modules.business.ui.profile

class OddNumberModel(val value: Int) : ItemViewModel {
    override fun getData(): Int {
        return value
    }
}