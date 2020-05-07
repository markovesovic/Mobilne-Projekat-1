package rs.raf.projekat1.marko_vesovic_rn2417.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_profile.*
import rs.raf.projekat1.marko_vesovic_rn2417.R
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.EditProfileActivity
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.LoginScreenActivity
import rs.raf.projekat1.marko_vesovic_rn2417.view.activities.MainActivity


class ProfileFragment: Fragment(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    private fun init() {
        initListeners()
        initTexts()
    }

    override fun onResume() {
        super.onResume()
        initTexts()
    }
    private fun initTexts() {
        val sharedPreferences = this.activity?.getSharedPreferences(this.activity?.packageName, Context.MODE_PRIVATE)

        nameTv.text = sharedPreferences?.getString(MainActivity.NAME, "")
        lastNameTv.text = sharedPreferences?.getString(MainActivity.LAST_NAME, "")
        hospitalTv.text = sharedPreferences?.getString(MainActivity.HOSPITAL_NAME, "")
    }

    private fun initListeners() {

        signOutB.setOnClickListener {
            val sharedPreferences = context?.getSharedPreferences(this.activity?.packageName, Context.MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putBoolean(MainActivity.LOGGED_IN, false)
            editor?.commit()
            val intent = Intent(this.requireActivity(), LoginScreenActivity::class.java)
            startActivity(intent)
            this.activity?.finish()
            Toast.makeText(this.requireContext(), "Uspesno odjavljivanje!", Toast.LENGTH_LONG).show()
        }
        editB.setOnClickListener {
            val intent = Intent(this.requireActivity(), EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
