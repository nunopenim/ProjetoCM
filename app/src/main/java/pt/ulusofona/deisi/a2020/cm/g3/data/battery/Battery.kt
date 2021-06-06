package pt.ulusofona.deisi.a2020.cm.g3.data.battery

import android.content.Context
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
class Battery private constructor(private val context: Context) : Runnable{

    private val TAG = Battery::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 20 * 1000L

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

    override fun run() {
        val current = getBaterryCurrentNow()
        Log.i(TAG, current.toString())
        handler.postDelayed(this,TIME_BETWEEN_UPDATES)
    }
}

