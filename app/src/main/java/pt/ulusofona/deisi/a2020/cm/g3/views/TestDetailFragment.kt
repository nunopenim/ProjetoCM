package pt.ulusofona.deisi.a2020.cm.g3.views

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.location.LocationResult
import pt.ulusofona.deisi.a2020.cm.g3.NavigationManager
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.FusedLocation
import pt.ulusofona.deisi.a2020.cm.g3.data.sensors.location.OnLocationChangedListener
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.extra.GlobalRisk
import pt.ulusofona.deisi.a2020.cm.g3.extra.RiskObtainer
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.TestDetailViewModel
import java.io.IOException
import java.util.*

class TestDetailFragment(uuid: String) : PermissionsFragment(100), OnLocationChangedListener {

    val uud = uuid
    private lateinit var viewModel: TestDetailViewModel
    var hasPhoto = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(TestDetailViewModel::class.java)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(TestDetailViewModel::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        drawRisk()
        val teste = viewModel.loadTeste(uud)
        val local_intent = teste.local
        val data_intent = teste.data
        val resultado_intent = teste.positivo
        val foto_id = teste.photo
        val foto: ImageView = activity!!.findViewById(R.id.teste_foto)
        if (foto_id != null) {
            foto.setImageBitmap(foto_id)
            hasPhoto = true
        }
        val local = activity!!.findViewById<TextView>(R.id.teste_local)
        val data = activity!!.findViewById<TextView>(R.id.teste_data)
        val resultado = activity!!.findViewById<TextView>(R.id.teste_resultado)
        val localstr = "${getString(R.string.local)} ${local_intent}"
        val datastr = "${getString(R.string.data)} ${data_intent}"
        var resultadostr = ""
        if (resultado_intent) {
            resultadostr = getString(R.string.result_pos)
        }
        else {
            resultadostr = getString(R.string.result_neg)
        }
        local.text = localstr
        data.text = datastr
        resultado.text = resultadostr
        if (hasPhoto) {
            foto.setOnClickListener {
                NavigationManager.photoViewer(activity!!.supportFragmentManager, viewModel)
            }
        }
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