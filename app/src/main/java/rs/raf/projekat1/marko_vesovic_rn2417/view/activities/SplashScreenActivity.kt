package rs.raf.projekat1.marko_vesovic_rn2417.view.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(MainActivity.PIN, 1234)
        editor.commit()

        val loggedIn = sharedPreferences.getBoolean(MainActivity.LOGGED_IN, false)


        this.intent = if (loggedIn) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, LoginScreenActivity::class.java)
        }

        startActivity(this.intent)
        finish()
    }
}