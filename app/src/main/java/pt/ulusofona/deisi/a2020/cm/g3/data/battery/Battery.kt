package pt.ulusofona.deisi.a2020.cm.g3.data.battery

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate

class Battery private constructor(private val context: Context) : Runnable{

    private val TAG = Battery::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 15 * 1000L

    private var hasStarted = false
    private var isDark = false

    companion object{
        private var instance: Battery? = null
        private var currentListener: OnBatteryCurrentListener? = null
        private val handler = Handler()
        fun start (context: Context, onBatteryCurrentListener: OnBatteryCurrentListener){
            currentListener = onBatteryCurrentListener
            instance =if (instance == null) Battery(context) else instance
            instance?.start()
        }
    }
    private fun start(){
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }
    private fun getBaterryCurrentNow(): Int{
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    //De acordo com o Piazza, podemos usar isto: https://piazza.com/class/kl2dr8a92j2a5?cid=95
    //https://developer.android.com/training/monitoring-device-state/battery-monitoring
    private fun batteryLevel() : Float{
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            context.registerReceiver(null, ifilter)
        }
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }
        return batteryPct!!
    }

    override fun run() {
        if (!hasStarted) {
            val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (nightModeFlags) {
                Configuration.UI_MODE_NIGHT_YES -> isDark = true
                Configuration.UI_MODE_NIGHT_NO -> isDark = false
                Configuration.UI_MODE_NIGHT_UNDEFINED -> isDark = false
            }
            hasStarted = true
        }

        val current = batteryLevel()
        if(!isDark) {
            if(current < 20) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        Log.i("BatteryLevel", current.toString())
        handler.postDelayed(this,TIME_BETWEEN_UPDATES)
    }
}

