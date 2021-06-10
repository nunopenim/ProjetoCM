package pt.ulusofona.deisi.a2020.cm.g3.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.BarChart
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.Data
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRecieved
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.DashboardViewModel
import java.util.jar.Manifest
import android.Manifest.permission
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import java.util.*


class DashboardFragment : PermissionsFragment(100), OnDataRecieved, OnLocationChangedListener {

    private lateinit var viewModel: DashboardViewModel

    override fun onRequestPermissionsSuccess() {
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        DangerChanger.setToUnknown(waarning!!, activity!!)
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailrule() {
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        DangerChanger.setToUnknown(waarning!!, activity!!)
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
        val gcd = Geocoder(activity?.baseContext!!, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(location.latitude, location.longitude, 1)
        val localizacao = addresses[0].locality

    }

}