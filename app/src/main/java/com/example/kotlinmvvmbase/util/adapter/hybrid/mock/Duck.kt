package com.example.kotlinmvvmbase.util.adapter.hybrid.mock

import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridTypeFactory
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.HybridAdapterItem

data class Duck(val value: Int) : HybridAdapterItem {
    override fun type(hybridTypeFactory: HybridTypeFactory): Int {
        return hybridTypeFactory.type(this)
    }
}
