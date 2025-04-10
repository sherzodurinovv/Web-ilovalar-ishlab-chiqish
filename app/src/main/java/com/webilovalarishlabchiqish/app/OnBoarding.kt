package com.webilovalarishlabchiqish.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.webilovalarishlabchiqish.app.adapters.OnBoardingAdapter
import com.webilovalarishlabchiqish.app.fragments.Onboarding
import com.webilovalarishlabchiqish.app.databinding.ActivityOnboardingBinding

class OnBoarding : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var skipButton: Button
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.onboarding)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        viewPager = findViewById(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        skipButton = findViewById(R.id.skipButton)

        val fragments = listOf(
            Onboarding(R.drawable.onboarding1,getString(R.string.welcomeText1), getString(R.string.subText1),getString(R.string.featureText1),getString(R.string.featureDesc1) ),
            Onboarding(R.drawable.onboarding2,getString(R.string.welcomeText1), getString(R.string.subText1),getString(R.string.featureText2),getString(R.string.featureDesc2) ),
            Onboarding(R.drawable.onboarding3,getString(R.string.welcomeText1), getString(R.string.subText1),getString(R.string.featureText3),getString(R.string.featureDesc3) ),
            Onboarding(R.drawable.onboarding4,getString(R.string.welcomeText1), getString(R.string.subText1),getString(R.string.featureText4),getString(R.string.featureDesc4) ),
            Onboarding(R.drawable.onboarding5,getString(R.string.welcomeText1), getString(R.string.subText1),getString(R.string.featureText5),getString(R.string.featureDesc5) ),
        )

        val adapter = OnBoardingAdapter(this, fragments)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        skipButton.setOnClickListener {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}