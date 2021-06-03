package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarData
import pt.ulusofona.deisi.a2020.cm.g3.logic.DashboardLogic

class DashboardViewModel : ViewModel() {
    private val logic: DashboardLogic = DashboardLogic()

    fun onLoadCardBuilder() : Array<String> {
        return arrayOf(logic.getConfirmedStr(), logic.getRecoveredStr(), logic.getInternatedStr(), logic.getDeathsStr())
    }

    fun onLoadGraphBuilder() : BarData {
        return logic.buildBarChart()
    }

}