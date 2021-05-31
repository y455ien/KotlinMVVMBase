package com.example.kotlinmvvmbase.modules.business.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinmvvmbase.R
import com.example.kotlinmvvmbase.core.base.navigation.Destination
import com.example.kotlinmvvmbase.modules.business.BusinessActivityVM
import com.example.kotlinmvvmbase.core.base.ui.BaseFragment
import com.example.kotlinmvvmbase.databinding.FragmentHomeBinding
import com.example.kotlinmvvmbase.modules.business.viewmodel.HomeFragmentVM
import com.example.kotlinmvvmbase.service.bound.BasicBoundService
import com.example.kotlinmvvmbase.service.bound.ServiceCommunicator
import com.example.kotlinmvvmbase.service.foreground.BasicForegroundService
import com.example.kotlinmvvmbase.util.GenericViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeFragmentVM, BusinessActivityVM>(FragmentHomeBinding::inflate, BusinessActivityVM::class.java) {
    private lateinit var factory: GenericViewModelFactory<HomeFragmentVM>
    private lateinit var serviceCommunicator: ServiceCommunicator
    private var progress: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceCommunicator = ServiceCommunicator()
    }

    override fun initViewModel(): HomeFragmentVM {
        factory = GenericViewModelFactory({ HomeFragmentVM() }, HomeFragmentVM::class.java)
        return ViewModelProvider(this, factory).get(HomeFragmentVM::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goInternalButton.setOnClickListener() {
            vm.navigateWithAction(Destination.Builder(HomeFragmentDirections.actionHomeFragmentToHomeInternal()).build())
        }
        binding.changeLanguageButton.setOnClickListener {

        }
        binding.startForegroundService.setOnClickListener {
            activity?.startService(Intent(view.context, BasicForegroundService::class.java))
        }
        binding.stopForegroundService.setOnClickListener {
            activity?.stopService(Intent(view.context, BasicForegroundService::class.java))
        }
        binding.bindService.setOnClickListener {
            Intent(view.context, BasicBoundService::class.java).also {
                activity?.bindService(it, serviceCommunicator, Context.BIND_AUTO_CREATE)
            }
        }
        binding.sendActionToBoundService.setOnClickListener {
//            serviceCommunicator.action1(progress)
//            progress+=10
        }
        binding.unbindService.setOnClickListener {
            activity?.unbindService(serviceCommunicator)
        }
    }
}