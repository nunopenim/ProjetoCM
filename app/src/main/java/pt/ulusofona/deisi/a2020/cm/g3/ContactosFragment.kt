package pt.ulusofona.deisi.a2020.cm.g3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.DangerChanger

class ContactosFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contactos, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        DangerChanger.setToModerate(waarning!!, activity!!)
        super.onActivityCreated(savedInstanceState)
    }
}