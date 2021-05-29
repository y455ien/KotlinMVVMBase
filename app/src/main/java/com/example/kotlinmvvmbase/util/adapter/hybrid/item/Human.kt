package com.example.kotlinmvvmbase.util.adapter.hybrid.item

import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridTypeFactory

data class Human(val name: String): HybridAdapterItem {
    override fun type(hybridTypeFactory: HybridTypeFactory): Int {
        return hybridTypeFactory.type(this)
    }
}