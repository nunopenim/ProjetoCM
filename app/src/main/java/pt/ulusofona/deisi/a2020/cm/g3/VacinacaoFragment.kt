package pt.ulusofona.deisi.a2020.cm.g3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.PieChart
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.DangerChanger
import pt.ulusofona.deisi.a2020.cm.g3.viewmodel.VacinacaoViewModel

class VacinacaoFragment : Fragment() {

    private lateinit var viewModel: VacinacaoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_vacinacao, container, false)
        viewModel = ViewModelProviders.of(this).get(VacinacaoViewModel::class.java)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val waarning = view?.findViewById<TextView>(R.id.TextView01)
        DangerChanger.setToModerate(waarning!!, activity!!)
        super.onActivityCreated(savedInstanceState)

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

}