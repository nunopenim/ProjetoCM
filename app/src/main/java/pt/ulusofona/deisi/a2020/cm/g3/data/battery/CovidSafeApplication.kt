package pt.ulusofona.deisi.a2020.cm.g3.data.battery

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class CovidSafeApplication : Application(), OnBatteryCurrentListener {
    override fun onCreate(){
        super.onCreate()
        //FusedLocation.start(this)
        Battery.start(this, this)
    }
    override fun OnCurrentChanged (current: Int){

        if(current < 20){
            Log.i(TAG,  "battery $current")
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

}