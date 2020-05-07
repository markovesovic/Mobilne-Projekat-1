package rs.raf.projekat1.marko_vesovic_rn2417.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import rs.raf.projekat1.marko_vesovic_rn2417.R

class LoginScreenActivity : AppCompatActivity(R.layout.activity_login_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initButton()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    private fun initButton() {

        // Logika prijavljivanja

        logInB.setOnClickListener {
            val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            if(nameEt.text.toString() == "" || lastNameEt.text.toString() == "" || hospitalEt.text.toString() == "") {
                Toast.makeText(this, "Morate popuniti sva polja!", Toast.LENGTH_SHORT).show()
            } else if(pinEt.text.toString().length != 4) {
                Toast.makeText(this, "Pin nije odgovarajuce duzine!", Toast.LENGTH_SHORT).show()
            } else if(pinEt.text.toString().toInt() != sharedPreferences.getInt(MainActivity.PIN, 0)) {
                Toast.makeText(this, "Pin nije dobar!", Toast.LENGTH_SHORT).show()
            } else {
                val editor = sharedPreferences.edit()
                editor.putString(MainActivity.NAME, nameEt.text.toString())
                editor.putString(MainActivity.LAST_NAME, lastNameEt.text.toString())
                editor.putString(MainActivity.HOSPITAL_NAME, hospitalEt.text.toString())
                editor.putBoolean(MainActivity.LOGGED_IN, true)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, "Uspesno logovanje!", Toast.LENGTH_LONG).show()
            }
        }
    }
}