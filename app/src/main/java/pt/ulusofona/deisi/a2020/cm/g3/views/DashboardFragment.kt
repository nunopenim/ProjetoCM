package pt.ulusofona.deisi.a2020.cm.g3.views

import android.Manifest.permission
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.BarChart
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.extra.Data
import pt.ulusofona.deisi.a2020.cm.g3.extra.GlobalRisk
import pt.ulusofona.deisi.a2020.cm.g3.extra.RiskObtainer
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRecieved
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.DashboardViewModel
import java.util.*


class DashboardFragment : PermissionsFragment(100), OnDataRecieved, OnLocationChangedListener {

    private lateinit var viewModel: DashboardViewModel

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

    override fun onRequestPermissionsSuccess() {
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailrule() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        viewModel = ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        return view
    }

    override fun onStart() {
        viewModel.registerListener(this)
        viewModel.onLoad()
        viewModel.onStartDashboard()
        super.onRequestPermissions(activity?.baseContext!!, arrayOf(permission.ACCESS_COARSE_LOCATION, permission.ACCESS_FINE_LOCATION))
        super.onStart()
        drawRisk()
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        viewModel.onUnload()
        FusedLocation.unregisterListener()
        super.onDestroy()
    }

    override fun onDataRecieved(value: Data?) {
        val cardData = viewModel.onLoadCardBuilder()

        //cards
        val confirmados = view?.findViewById<TextView>(R.id.confirmados)
        val recuperados = view?.findViewById<TextView>(R.id.recuperados)
        val internados = view?.findViewById<TextView>(R.id.internados)
        val obitos = view?.findViewById<TextView>(R.id.obitos)
        val confirmadosstr = getString(R.string.confirmados) + cardData[0]
        val recuperadosstr = getString(R.string.recuperados) + cardData[1]
        val internadosstr = getString(R.string.internados) + cardData[2]
        val obitosstr = getString(R.string.obitos) + cardData[3]
        confirmados?.text = confirmadosstr
        recuperados?.text = recuperadosstr
        internados?.text = internadosstr
        obitos?.text = obitosstr

        //chart stuff
        val chart: BarChart = view?.findViewById<BarChart>(R.id.chart1)!!
        chart.data = viewModel.onLoadGraphBuilder()
        chart.xAxis.setDrawLabels(false)
        chart.description.text = ""
    }

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        val gcd = Geocoder(activity?.baseContext!!, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(location.latitude, location.longitude, 1)
        val localizacao = addresses[0].adminArea
        RiskObtainer.distriot = localizacao
        RiskObtainer.sortRiskStuff(waarning!!, activity!!)
    }
}
