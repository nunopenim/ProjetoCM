package pt.ulusofona.deisi.a2020.cm.g3.logic

import android.graphics.Color
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import pt.ulusofona.deisi.a2020.cm.g3.R
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas

class VacinacaoLogic {

    fun getTotalDosage(vacinasData: Vacinas) : String {
        return "" + vacinasData.doses
    }

    fun buildChart(fst: String, snd: String, vacinasData: Vacinas) : PieData{
        val pieEntries: ArrayList<PieEntry> = ArrayList()
        val label = "type"
        val typeAmountMap: MutableMap<String, Int> = HashMap()
        typeAmountMap[fst] = vacinasData.doses1!!
        typeAmountMap[snd] = vacinasData.doses2!!
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
        return pieData
    }
}