package rs.raf.projekat1.marko_vesovic_rn2417.application

import android.app.Application
import timber.log.Timber

class Project1Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}