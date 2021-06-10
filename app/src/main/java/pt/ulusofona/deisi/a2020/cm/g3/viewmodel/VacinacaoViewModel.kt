package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.github.mikephil.charting.data.PieData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.extra.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.data.VacinaDatabase
import pt.ulusofona.deisi.a2020.cm.g3.data.repositories.VacinasRepository
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnVaccineRecieved
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnVaccineRepositoryLoad
import pt.ulusofona.deisi.a2020.cm.g3.logic.VacinacaoLogic

class VacinacaoViewModel(application: Application) : AndroidViewModel(application), OnVaccineRepositoryLoad {
    private val storage = VacinaDatabase.getInstance(application).vacinaDao()
    private val repo = VacinasRepository(storage)

    private val logic: VacinacaoLogic = VacinacaoLogic()
    var vacinas = Vacinas("", 0, 0, 0, 0, 0,0)
    var vacinas_IO = Vacinas("", 0, 0, 0, 0, 0,0)
    private var listener: OnVaccineRecieved? = null

    fun onLoad() {
        repo.registerListener(this)
    }

    fun onUnload() {
        repo.unregisterListener()
    }

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
            //vacinas_IO = DataObtainer.getVaccines()
            CoroutineScope(Dispatchers.Main).launch {
                vacinas = vacinas_IO
                notifyOnDataRecieved()
            }
        }
    }

    override fun onVaccineRepositoryLoad(vaccine: Vacinas) {
        vacinas_IO = vaccine
        onStartVaccine()
    }

}