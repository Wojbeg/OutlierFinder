package com.wojbeg.outlierfinder.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.wojbeg.outlierfinder.databinding.AboutDialogBinding

class AboutDialog : DialogFragment() {

    private lateinit var binding: AboutDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = AboutDialogBinding.inflate(LayoutInflater.from(context), container, false)

        onOkBtnClick()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun onOkBtnClick(){
        binding.okBtn.setOnClickListener {
            dismiss()
        }
    }

    companion object{
        const val TAG = "AboutDialog"
    }
}