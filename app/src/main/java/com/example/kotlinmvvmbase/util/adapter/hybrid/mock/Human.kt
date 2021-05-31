package com.example.kotlinmvvmbase.util.adapter.hybrid.mock

import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridTypeFactory
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.HybridAdapterItem

data class Human(val name: String): HybridAdapterItem {
    override fun type(hybridTypeFactory: HybridTypeFactory): Int {
        return hybridTypeFactory.type(this)
    }
}