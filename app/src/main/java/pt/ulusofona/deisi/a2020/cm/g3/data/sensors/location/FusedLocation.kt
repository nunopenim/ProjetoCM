package pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*

class FusedLocation private constructor(context: Context) : LocationCallback() {
    private val TAG = FusedLocation::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 60 * 1000L // 60 segundos acho
    private var locationRequest: LocationRequest? = null
    private var client = FusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        client.requestLocationUpdates(locationRequest, this, Looper.myLooper())
    }

    override fun onLocationResult(p0: LocationResult?) {
        p0?.let { notifyListeners(it) }
        super.onLocationResult(p0)
    }

    init {
        locationRequest = LocationRequest()
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest?.interval = TIME_BETWEEN_UPDATES
        val locationSettingsRequest = LocationSettingsRequest.Builder().addLocationRequest(locationRequest!!).build()
        LocationServices.getSettingsClient(context).checkLocationSettings(locationSettingsRequest)
    }

    companion object {
        private var listener: OnLocationChangedListener? = null
        private var instance: FusedLocation? = null

        fun registerListener(listener: OnLocationChangedListener) {
            this.listener = listener
        }

        fun unregisterListener() {
            listener = null
        }

        fun notifyListeners(locationResult: LocationResult) {
            listener?.onLocationChanged(locationResult)
        }

        fun start(context: Context) {
            instance = if(instance==null) FusedLocation(context) else instance
            instance?.startLocationUpdates()
        }
    }
}

