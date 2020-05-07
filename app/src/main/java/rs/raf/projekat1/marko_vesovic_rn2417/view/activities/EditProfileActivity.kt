package rs.raf.projekat1.marko_vesovic_rn2417.view.activities

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import rs.raf.projekat1.marko_vesovic_rn2417.R

class EditProfileActivity :AppCompatActivity(R.layout.activity_edit_profile) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    private fun init() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        initListeners()
        initEditTexts()
    }
    private fun initEditTexts() {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        nameEt.setText(sharedPreferences.getString(MainActivity.NAME, ""))
        lastNameEt.setText(sharedPreferences.getString(MainActivity.LAST_NAME, ""))
        hospitalEt.setText(sharedPreferences.getString(MainActivity.HOSPITAL_NAME, ""))
    }
    private fun initListeners() {
        cancelB.setOnClickListener {
            finish()
            Toast.makeText(this, "Canceled!", Toast.LENGTH_LONG).show()
        }
        confirmB.setOnClickListener {
            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            if(nameEt.text.toString() == "" || lastNameEt.text.toString() == "" || hospitalEt.text.toString() == "") {
                Toast.makeText(this, "Popuniti sva polja!", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString(MainActivity.NAME, nameEt.text.toString())
                editor.putString(MainActivity.LAST_NAME, lastNameEt.text.toString())
                editor.putString(MainActivity.HOSPITAL_NAME, hospitalEt.text.toString())
                editor.commit()
                finish()
                Toast.makeText(this, "Uspesno sacuvano!", Toast.LENGTH_LONG).show()
            }

        }
    }

}
