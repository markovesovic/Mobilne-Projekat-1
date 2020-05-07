package rs.raf.projekat1.marko_vesovic_rn2417.view.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat1.marko_vesovic_rn2417.view.viewpager.PagerAdapter
import rs.raf.projekat1.marko_vesovic_rn2417.R
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val LOGGED_IN = "loggedIn"
        const val NAME = "name"
        const val LAST_NAME = "lastName"
        const val HOSPITAL_NAME = "hospitalName"
        const val PIN = "pin"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    private fun init() {
        initViewPager()
        initNavigation()
        initListeners()
    }

    private fun initListeners() {

    }


    private fun initViewPager() {
        viewPager.adapter = PagerAdapter(supportFragmentManager)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.navigation_1 -> {
                    viewPager.setCurrentItem(PagerAdapter.STATE_FRAGMENT, false)
                }
                R.id.navigation_2 -> {
                    viewPager.setCurrentItem(PagerAdapter.INPUT_FRAGMENT, false)
                }
                R.id.navigation_3 -> {
                    viewPager.setCurrentItem(PagerAdapter.LIST_FRAGMENT, false)
                }
                R.id.navigation_4 -> {
                    viewPager.setCurrentItem(PagerAdapter.PROFILE_FRAGMENT, false)
                }
            }
            true
        }

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).
        setIcon(R.drawable.icon_profile).
        setTitle("Warning!").
        setMessage("Are you sure you wonna quit").
        setPositiveButton("Yes", DialogInterface.OnClickListener { _: DialogInterface, _: Int ->
            finish()
        }).
        setNegativeButton("No", null).
        show()

    }
}
