package pt.ulusofona.deisi.a2020.cm.g3

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI

class VacinacaoFragment : Fragment() {

    lateinit var chart: PieChart
    lateinit var card: TextView

    var vacinasData = FakeAPI.fakeVaccines()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vacinacao, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        card = view!!.findViewById(R.id.confirmados)
        val cardstr = getString(R.string.totalvacinas) + vacinasData.doses
        card.text = cardstr
        //chart
        chart = view!!.findViewById(R.id.pieChart)
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = "type"
        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap[getString(R.string.first_doses)] = vacinasData.doses1!!
        typeAmountMap[getString(R.string.second_doses)] = vacinasData.doses2!!
        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#66CDAA"))
        colors.add(Color.parseColor("#4682B4"))
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type]!!.toFloat(), type))
        }
        val pieDataSet = PieDataSet(pieEntries, label)
        pieDataSet.valueTextSize = 15f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)
        chart.setEntryLabelTextSize(15f)
        chart.legend.isEnabled = false
        chart.description.text = ""
        chart.data = pieData
    }

}