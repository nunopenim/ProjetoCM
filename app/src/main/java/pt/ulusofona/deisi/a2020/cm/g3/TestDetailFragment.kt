package pt.ulusofona.deisi.a2020.cm.g3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import pt.ulusofona.deisi.a2020.cm.g3.extra.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.TestDetailViewModel

class TestDetailFragment(uuid: String) : Fragment() {

    val uud = uuid
    private lateinit var viewModel: TestDetailViewModel
    var hasPhoto = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test_detail, container, false)
        viewModel = ViewModelProviders.of(this).get(TestDetailViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        DangerChanger.setToUnknown(waarning!!, activity!!)
        super.onActivityCreated(savedInstanceState)
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
}