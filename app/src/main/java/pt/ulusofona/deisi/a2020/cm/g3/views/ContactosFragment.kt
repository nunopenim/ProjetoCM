package pt.ulusofona.deisi.a2020.cm.g3.views

import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.extra.GlobalRisk
import pt.ulusofona.deisi.a2020.cm.g3.extra.RiskObtainer
import java.io.IOException
import java.util.*

class ContactosFragment : PermissionsFragment(100), OnLocationChangedListener {

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        val gcd = Geocoder(activity?.baseContext!!, Locale.getDefault())
        try {
            val addresses: List<Address> = gcd.getFromLocation(location.latitude, location.longitude, 1)
            val localizacao = addresses[0].adminArea
            RiskObtainer.distriot = localizacao
            RiskObtainer.sortRiskStuff(waarning!!, activity!!)
        }
        catch (e: IOException) {
            GlobalRisk.risco = -1
            try {
                DangerChanger.setToUnknown(waarning!!, activity!!)
            }
            catch (e: NullPointerException) {
                null
            }
        }
        catch (e: NullPointerException) {
            null
        }
    }

    override fun onRequestPermissionsSuccess() {
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailrule() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contactos, container, false)
    }

    override fun onStart() {
        super.onRequestPermissions(activity?.baseContext!!, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION))
        super.onStart()
        drawRisk()
    }

    fun drawRisk() {
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        if (GlobalRisk.risco == -1) {
            DangerChanger.setToUnknown(waarning!!, activity!!)
        }
        else if(GlobalRisk.risco == 0) {
            DangerChanger.setToSafe(waarning!!, activity!!)
        }
        else if(GlobalRisk.risco == 1) {
            DangerChanger.setToModerate(waarning!!, activity!!)
        }
        else if(GlobalRisk.risco == 2) {
            DangerChanger.setToRisky(waarning!!, activity!!)
        }
        else if(GlobalRisk.risco == 3) {
            DangerChanger.setToDangerous(waarning!!, activity!!)
        }
        else if(GlobalRisk.risco == -2) {
            DangerChanger.setToNoConnection(waarning!!, activity!!)
        }
    }

}