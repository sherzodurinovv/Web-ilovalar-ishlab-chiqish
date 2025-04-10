package com.webilovalarishlabchiqish.app

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.webilovalarishlabchiqish.app.databinding.ActivityMainBinding
import com.webilovalarishlabchiqish.app.fragments.About
import com.webilovalarishlabchiqish.app.fragments.LessonsFragment
import com.webilovalarishlabchiqish.app.fragments.PracticeFragment
import com.webilovalarishlabchiqish.app.fragments.SettingsFragment
import com.webilovalarishlabchiqish.app.fragments.SettingsFragment.Companion.KEY_LANGUAGE
import com.webilovalarishlabchiqish.app.fragments.SettingsFragment.Companion.PREFS_NAME
import com.webilovalarishlabchiqish.app.fragments.TestsFragment
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPrefs = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        val darkModeEnabled = sharedPrefs.getBoolean("dark_mode", false)

        if (darkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        updateLocale(applicationContext)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(LessonsFragment())


        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_lessons -> {
                    replaceFragment(LessonsFragment())
                    true
                }
                R.id.nav_practice -> {
                    replaceFragment(PracticeFragment())
                    true
                }
                R.id.nav_tests -> {
                    replaceFragment(TestsFragment())
                    true
                }
                R.id.nav_profile -> {
                    replaceFragment(SettingsFragment())
                    true
                }

                R.id.nav_about -> {
                    replaceFragment(About())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    private fun updateLocale(context: Context): Context {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val langCode = sharedPrefs.getString(KEY_LANGUAGE, "uz") ?: "uz"
        val locale = Locale(langCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}