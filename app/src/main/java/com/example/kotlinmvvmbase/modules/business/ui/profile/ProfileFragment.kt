package com.example.kotlinmvvmbase.modules.business.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvvmbase.modules.business.BusinessActivityVM
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentProfileBinding
import com.example.kotlinmvvmbase.util.adapter.hybrid.Duck
import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridRecyclerAdapter
import com.example.kotlinmvvmbase.util.adapter.hybrid.HybridTypeFactory
import com.example.kotlinmvvmbase.modules.business.viewmodel.ProfileFragmentVM
import com.example.kotlinmvvmbase.util.GenericViewModelFactory
import com.example.kotlinmvvmbase.util.adapter.hybrid.item.Human

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileFragmentVM, BusinessActivityVM>(FragmentProfileBinding::inflate, BusinessActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<ProfileFragmentVM>
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: HybridRecyclerAdapter

    override fun initViewModel(): ProfileFragmentVM {
        factory = GenericViewModelFactory({ ProfileFragmentVM() }, ProfileFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(ProfileFragmentVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState).apply {
            linearLayoutManager = LinearLayoutManager(activity)
//            adapter = CustomAdapter(listOf(EvenNumberModel(2), OddNumberModel(1), OddNumberModel(3)))
            adapter = HybridRecyclerAdapter(listOf(Duck(1), Duck(2), Duck(5), Human("Yassien")), HybridTypeFactory())
            binding.recyclerView.apply {
                adapter = this@ProfileFragment.adapter
                layoutManager = linearLayoutManager
            }
        }
    }
}