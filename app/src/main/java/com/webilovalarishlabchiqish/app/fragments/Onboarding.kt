package com.webilovalarishlabchiqish.app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.webilovalarishlabchiqish.app.R
import com.webilovalarishlabchiqish.app.databinding.FragmentOnboardingBinding

class Onboarding(private val imageRes: Int,private val title: String, private val subtitle: String,private val feature:String,private val subfeature:String ) : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var fragmentContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view =  inflater.inflate(R.layout.fragment_onboarding, container, false)
        binding = FragmentOnboardingBinding.bind(view)

        val imageView: ImageView = binding.imageView
        val titleText: TextView = binding.welcomeText
        val subText: TextView = binding.subText
        val featureText: TextView = binding.featureText
        val featureDesc: TextView = binding.featureDesc

        imageView.setImageResource(imageRes)
        titleText.text = title
        subText.text = subtitle
        featureText.text = feature
        featureDesc.text = subfeature


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = context
    }
}
