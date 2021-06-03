package pt.ulusofona.deisi.a2020.cm.g3

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
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.ListaTestesViewModel

class ListaTestesFragment : Fragment() {

    private lateinit var viewModel: ListaTestesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_testes, container, false)
        viewModel = ViewModelProviders.of(this).get(ListaTestesViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val crescente : Button? = view?.findViewById(R.id.crescente)
        val decrescente : Button? = view?.findViewById(R.id.decrescente)

        val rv : RecyclerView? = view?.findViewById(R.id.recycler_testes)
        val relative_rv : RelativeLayout? = view?.findViewById(R.id.relative_recycler)
        val emptyList : TextView? = view?.findViewById(R.id.emptyList)

        val testeAdapter  = viewModel.onLoadAdapter()
        if (viewModel.getList().isNotEmpty()) {
            relative_rv?.visibility = View.VISIBLE
            emptyList?.visibility = View.GONE
        }
        rv?.adapter = testeAdapter

        // https://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter :)
        testeAdapter.onItemClick = {teste ->
            NavigationManager.testDetail(activity!!.supportFragmentManager, teste.uuid)

            /*val intent = Intent(this, TesteDetailActivity::class.java)
            val b = Bundle()
            var id: ByteArray = byteArrayOf()
            if (teste.photo != null) {
                //val byteBuffer: ByteBuffer = ByteBuffer.allocate(teste.photo!!.getByteCount())
                //teste.photo!!.copyPixelsToBuffer(byteBuffer)
                //byteBuffer.rewind()
                //id = byteBuffer.array() //para entrar aqui será doferente de null
                val baos = ByteArrayOutputStream()
                teste.photo!!.compress(Bitmap.CompressFormat.PNG, 100, baos)
                id = baos.toByteArray()
            }
            b.putString("local", teste.local)
            b.putString("data", teste.stringMyDate())
            b.putBoolean("resultado", teste.positivo)
            b.putByteArray("photo", id)
            intent.putExtras(b)
            startActivity(intent)*/
        }
        crescente!!.setOnClickListener {
            viewModel.orderCrescente()
            val toast = Toast.makeText(activity, getString(R.string.ordenado_crescente), Toast.LENGTH_SHORT)
            toast.show()
        }
        decrescente!!.setOnClickListener {
            viewModel.orderDecrescente()
            val toast = Toast.makeText(activity, getString(R.string.ordenado_decrescente), Toast.LENGTH_SHORT)
            toast.show()
        }
    }

}