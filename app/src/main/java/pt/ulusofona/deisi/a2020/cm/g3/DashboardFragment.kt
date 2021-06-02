package pt.ulusofona.deisi.a2020.cm.g3

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI


class DashboardFragment : Fragment() {

    lateinit var chart: BarChart
    var data = FakeAPI.fakeData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //cards
        val confirmados = view?.findViewById<TextView>(R.id.confirmados)
        val recuperados = view?.findViewById<TextView>(R.id.recuperados)
        val internados = view?.findViewById<TextView>(R.id.internados)
        val obitos = view?.findViewById<TextView>(R.id.obitos)
        val confirmadosstr = getString(R.string.confirmados) + data.confirmados
        val recuperadosstr = getString(R.string.recuperados) + data.recuperados
        val internadosstr = getString(R.string.internados) + data.internados
        val obitosstr = getString(R.string.obitos) + data.obitos
        confirmados?.text = confirmadosstr
        recuperados?.text = recuperadosstr
        internados?.text = internadosstr
        obitos?.text = obitosstr

        //chart stuff
        chart = view?.findViewById<BarChart>(R.id.chart1)!!
        val barData0to9 = BarDataSet(arrayListOf(BarEntry(0f, data.confirmados_general()[0].toFloat())), "0-9")
        val barData10to19 = BarDataSet(arrayListOf(BarEntry(1.25f, data.confirmados_general()[1].toFloat())), "10-19")
        val barData20to29 = BarDataSet(arrayListOf(BarEntry(2.5f, data.confirmados_general()[2].toFloat())), "20-29")
        val barData30to39 = BarDataSet(arrayListOf(BarEntry(3.75f, data.confirmados_general()[3].toFloat())), "30-39")
        val barData40to49 = BarDataSet(arrayListOf(BarEntry(5f, data.confirmados_general()[4].toFloat())), "40-49")
        val barData50to59 = BarDataSet(arrayListOf(BarEntry(6.25f, data.confirmados_general()[5].toFloat())), "50-59")
        val barData60to69 = BarDataSet(arrayListOf(BarEntry(7.5f, data.confirmados_general()[6].toFloat())), "60-69")
        val barData70to79 = BarDataSet(arrayListOf(BarEntry(8.75f, data.confirmados_general()[7].toFloat())), "70-79")
        val barData80 = BarDataSet(arrayListOf(BarEntry(10f, data.confirmados_general()[8].toFloat())), "80+")
        val barData = BarData(barData0to9, barData10to19, barData20to29, barData30to39, barData40to49, barData50to59, barData60to69, barData70to79, barData80)
        barData0to9.valueTextSize=12f
        barData0to9.color= Color.parseColor("#66CDAA")
        barData10to19.valueTextSize=12f
        barData10to19.color= Color.parseColor("#66CDAA")
        barData20to29.valueTextSize=12f
        barData20to29.color= Color.parseColor("#66CDAA")
        barData30to39.valueTextSize=12f
        barData30to39.color= Color.parseColor("#66CDAA")
        barData40to49.valueTextSize=12f
        barData40to49.color= Color.parseColor("#66CDAA")
        barData50to59.valueTextSize=12f
        barData50to59.color= Color.parseColor("#66CDAA")
        barData60to69.valueTextSize=12f
        barData60to69.color= Color.parseColor("#66CDAA")
        barData70to79.valueTextSize=12f
        barData70to79.color= Color.parseColor("#66CDAA")
        barData80.valueTextSize=12f
        barData80.color= Color.parseColor("#66CDAA")
        chart.data = barData
        chart.xAxis.setDrawLabels(false)
        chart.description.text = ""
    }

}