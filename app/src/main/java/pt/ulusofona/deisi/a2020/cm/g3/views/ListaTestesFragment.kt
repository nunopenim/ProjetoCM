package pt.ulusofona.deisi.a2020.cm.g3.views

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.extra.GlobalRisk
import pt.ulusofona.deisi.a2020.cm.g3.extra.RiskObtainer
import pt.ulusofona.deisi.a2020.cm.g3.extra.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnTestListToView
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.ListaTestesViewModel
import java.util.*

class ListaTestesFragment : PermissionsFragment(100), OnLocationChangedListener, OnTestListToView {

    private lateinit var viewModel: ListaTestesViewModel

    lateinit var crescente : Button
    lateinit var decrescente : Button

    lateinit var rv : RecyclerView
    lateinit var relative_rv : RelativeLayout
    lateinit var emptyList : TextView

    override fun onLocationChanged(locationResult: LocationResult) {
        val location = locationResult.lastLocation
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        val gcd = Geocoder(activity?.baseContext!!, Locale.getDefault())
        val addresses: List<Address> = gcd.getFromLocation(location.latitude, location.longitude, 1)
        val localizacao = addresses[0].adminArea
        RiskObtainer.distriot = localizacao
        RiskObtainer.sortRiskStuff(waarning!!, activity!!)
    }

    override fun onRequestPermissionsSuccess() {
        FusedLocation.registerListener(this)
    }

    override fun onRequestPermissionsFailrule() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_testes, container, false)
        viewModel = ViewModelProviders.of(this).get(ListaTestesViewModel::class.java)
        viewModel.onLoadAdapter()
        crescente = view?.findViewById(R.id.crescente)!!
        decrescente = view?.findViewById(R.id.decrescente)
        rv = view?.findViewById(R.id.recycler_testes)
        relative_rv = view?.findViewById(R.id.relative_recycler)
        emptyList = view?.findViewById(R.id.emptyList)
        return view
    }

    override fun onStart() {
        super.onStart()
        viewModel.registerListener(this)
        drawRisk()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterListener()
        viewModel.onUnload()
    }

    override fun onTestListToView(adapter: TesteAdapter) {
        if (adapter.testList.isNotEmpty()) {
            relative_rv.visibility = View.VISIBLE
            emptyList.visibility = View.GONE
        }
        rv.adapter = adapter
        adapter.onItemClick = { teste ->
            NavigationManager.testDetail(activity!!.supportFragmentManager, teste.uuid)
        }
        crescente.setOnClickListener {
            viewModel.orderCrescente()
            val toast = Toast.makeText(activity, getString(R.string.ordenado_crescente), Toast.LENGTH_SHORT)
            toast.show()
        }
        decrescente.setOnClickListener {
            viewModel.orderDecrescente()
            val toast = Toast.makeText(activity, getString(R.string.ordenado_decrescente), Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}