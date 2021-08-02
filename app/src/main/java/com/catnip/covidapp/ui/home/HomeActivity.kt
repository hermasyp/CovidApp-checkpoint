package com.catnip.covidapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.catnip.covidapp.R
import com.catnip.covidapp.data.local.sharedpreference.SessionPreference
import com.catnip.covidapp.databinding.ActivityMainBinding
import com.catnip.covidapp.ui.home.covidnews.CovidNewsFragment
import com.catnip.covidapp.ui.home.covidspread.CovidSpreadFragment
import com.catnip.covidapp.ui.login.LoginActivity
import com.catnip.covidapp.utils.Constants

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var covidSpreadFragment = CovidSpreadFragment()
    private var covidNewsFragment = CovidNewsFragment()
    private var activeFragment: Fragment = covidSpreadFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    private fun setupFragment() {
        // delete all fragment in fragment manager first
        for (fragment in supportFragmentManager.fragments) {
            supportFragmentManager.beginTransaction().remove(fragment).commit()
        }
        // add fragment to fragment manager
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fl_fragment, covidSpreadFragment, Constants.TAG_FRAGMENT_COVID_SPREAD)
            add(R.id.fl_fragment, covidNewsFragment, Constants.TAG_FRAGMENT_COVID_NEWS).hide(
                covidNewsFragment
            )
        }.commit()
        // set title for first fragment
        supportActionBar?.title = getString(R.string.title_toolbar_menu_covid_info)
        // set click menu for changing fragment
        binding.bottomNavViewHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_covid_menu -> {
                    supportActionBar?.title = getString(R.string.title_toolbar_menu_covid_info)
                    showFragment(covidSpreadFragment)
                    true
                }
                else -> {
                    supportActionBar?.title = getString(R.string.title_toolbar_menu_covid_news)
                    showFragment(covidNewsFragment)
                    true
                }
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(activeFragment)
            .show(fragment)
            .commit()
        activeFragment = fragment
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_homepage, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_logout -> {
                showDialogLogout()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showDialogLogout() {
        AlertDialog.Builder(this)
            .apply {
                setTitle(getString(R.string.text_logout_dialog))
                setPositiveButton(R.string.text_dialog_logout_task_positive) { dialog, id ->
                    logout()
                    dialog.dismiss()
                }
                setNegativeButton(R.string.text_dialog_logout_task_negative) { dialog, id ->
                    dialog.dismiss()
                }
            }.create().show()
    }

    private fun logout() {
        deleteSession()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun deleteSession() {
        SessionPreference(this).deleteSession()
    }
}