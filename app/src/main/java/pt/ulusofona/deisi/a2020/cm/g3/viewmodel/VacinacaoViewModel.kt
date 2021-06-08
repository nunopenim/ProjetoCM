package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.PieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnVaccineRecieved
import pt.ulusofona.deisi.a2020.cm.g3.logic.VacinacaoLogic
import pt.ulusofona.deisi.a2020.cm.g3.remote.DataObtainer

class VacinacaoViewModel: ViewModel() {
    private val logic: VacinacaoLogic = VacinacaoLogic()
    var vacinas = Vacinas("", 0, 0, 0, 0, 0,0)
    var vacinas_IO = Vacinas("", 0, 0, 0, 0, 0,0)
    private var listener: OnVaccineRecieved? = null

    fun onLoadCardBuilder() : String {
        return logic.getTotalDosage(vacinas)
    }

    fun onLoadChartBuilder(fst: String, snd: String) : PieData {
        return logic.buildChart(fst, snd, vacinas)
    }

    private fun notifyOnDataRecieved() {
        listener?.onVaccineRecieved(vacinas)
    }

    fun registerListener(listener: OnVaccineRecieved) {
        this.listener = listener
        listener?.onVaccineRecieved(vacinas)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onStartVaccine() {
        CoroutineScope(Dispatchers.IO).launch {
            vacinas_IO = DataObtainer.getVaccines()
            CoroutineScope(Dispatchers.Main).launch {
                vacinas = vacinas_IO
                notifyOnDataRecieved()
            }
        }
    }

}