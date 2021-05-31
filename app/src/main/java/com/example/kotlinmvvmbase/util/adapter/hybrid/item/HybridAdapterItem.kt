package com.example.kotlinmvvmbase.util.adapter.hybrid.item

import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridTypeFactory

interface HybridAdapterItem {
    fun type(hybridTypeFactory: HybridTypeFactory): Int
}