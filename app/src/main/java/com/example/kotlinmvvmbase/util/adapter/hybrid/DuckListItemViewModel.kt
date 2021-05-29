package com.example.kotlinmvvmbase.util.adapter.hybrid

class DuckListItemViewModel(val duck: Duck) : ListViewModel() {
    override fun type(listViewTypeFactory: ListViewTypeFactory): Int {
        return listViewTypeFactory.type(duck)
    }
}