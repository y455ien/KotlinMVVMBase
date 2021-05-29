package com.example.kotlinmvvmbase.util.adapter.hybrid

abstract class ListViewModel {
    abstract fun type(listViewTypeFactory: ListViewTypeFactory): Int
}