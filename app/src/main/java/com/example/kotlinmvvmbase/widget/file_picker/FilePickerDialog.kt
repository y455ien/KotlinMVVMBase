package com.intcore.aerbagprovider.widget.file_picker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.intcore.aerbagprovider.databinding.FragmentFilePickerDialogBinding
import com.intcore.aerbagprovider.enum.FilePickerType
import com.intcore.aerbagprovider.util.extension.setNavigationResult
import java.io.File


class FilePickerDialog : BottomSheetDialogFragment() {
    private var _binding: FragmentFilePickerDialogBinding? = null
    private val binding get() = _binding!!
//    private val args: FilePickerDialogArgs by navArgs()
    private lateinit var intent: Intent
    private var pickedFile: File? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilePickerDialogBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pickImageTitle.setOnClickListener() {
            openSAFPicker()
        }
    }

    private fun openSAFPicker() {
        intent = Intent(Intent.ACTION_GET_CONTENT)
        //ToDo: Add all mimi types not only first
        intent.type = (allMIMEType.first())
        startActivityForResult(Intent.createChooser(intent, "Choose file"), 10)
    }

    override fun onActivityResult(
            requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                handleData(resultData)
            }
        }
    }

    private fun handleData(resultData: Intent?) {
        resultData?.data?.let {
            it.path?.let { path ->
                pickedFile = File(path)
            }
        }
        pickedFile?.let {
            this.setNavigationResult("key", pickedFile)
        }
    }

    private companion object {
        private val fileMIMEType =
                listOf<String>("application/msword",
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                        "application/pdf")
        private val imageMIMEType =
                listOf<String>("image/*")

        private val allMIMEType =
                listOf<String>("*/*")

        fun getMIMEType(type: FilePickerType): List<String> = when (type) {
            FilePickerType.FILE -> fileMIMEType
            FilePickerType.IMAGE -> imageMIMEType
            FilePickerType.ALL -> allMIMEType
        }
    }
}