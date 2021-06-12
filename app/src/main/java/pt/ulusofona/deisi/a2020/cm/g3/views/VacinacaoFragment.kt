package pt.ulusofona.deisi.a2020.cm.g3.views

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.PieChart
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.extra.GlobalRisk
import pt.ulusofona.deisi.a2020.cm.g3.extra.RiskObtainer
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnVaccineRecieved
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.VacinacaoViewModel
import java.io.IOException
import java.util.*

class VacinacaoFragment : PermissionsFragment(100), OnLocationChangedListener, OnVaccineRecieved {

    private lateinit var viewModel: VacinacaoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vacinacao, container, false)
        viewModel = ViewModelProviders.of(this).get(VacinacaoViewModel::class.java)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(VacinacaoViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        viewModel.registerListener(this)
        viewModel.onLoad()
        viewModel.onStartVaccine()
        drawRisk()
        super.onStart()
    }

    override fun onDestroy() {
        viewModel.onUnload()
        viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onVaccineRecieved(value: Vacinas?) {
        val card: TextView = view!!.findViewById(R.id.confirmados)
        val cardstr = getString(R.string.totalvacinas) + viewModel.onLoadCardBuilder()
        card.text = cardstr

        //chart
        val chart: PieChart = view!!.findViewById(R.id.pieChart)
        chart.setEntryLabelTextSize(15f)
        chart.legend.isEnabled = false
        chart.description.text = ""
        chart.data = viewModel.onLoadChartBuilder(getString(R.string.first_doses), getString(R.string.second_doses))
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

}
