package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieData
import pt.ulusofona.deisi.a2020.cm.g3.logic.VacinacaoLogic

class VacinacaoViewModel: ViewModel() {
    private val logic: VacinacaoLogic = VacinacaoLogic()

    fun onLoadCardBuilder() : String {
        return logic.getTotalDosage()
    }

    fun onLoadChartBuilder(fst: String, snd: String) : PieData {
        return logic.buildChart(fst, snd)
    }
}