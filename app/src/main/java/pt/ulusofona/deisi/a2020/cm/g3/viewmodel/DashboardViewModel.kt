package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.BarData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Data
import pt.ulusofona.deisi.a2020.cm.g3.data.DataDatabase
import pt.ulusofona.deisi.a2020.cm.g3.data.repositories.DadosRepository
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRecieved
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRepositoryLoad
import pt.ulusofona.deisi.a2020.cm.g3.logic.DashboardLogic
import pt.ulusofona.deisi.a2020.cm.g3.remote.DataObtainer

class DashboardViewModel(application: Application) : AndroidViewModel(application), OnDataRepositoryLoad  {
    private val storage = DataDatabase.getInstance(application).dataDao()
    private val repo = DadosRepository(storage)

    private val logic: DashboardLogic = DashboardLogic()
    private var listener: OnDataRecieved? = null
    var data = Data("", "",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0, 0)
    private var data_IO = Data("", "",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0,0)

    fun onLoad() {
        repo.registerListener(this)
    }

    fun onUnload() {
        repo.unregisterListener()
    }

    fun onLoadCardBuilder() : Array<String> {
        return arrayOf(logic.getConfirmedStr(data), logic.getRecoveredStr(data), logic.getInternatedStr(data), logic.getDeathsStr(data))
    }

    fun onLoadGraphBuilder() : BarData {
        return logic.buildBarChart(data)
    }

    private fun notifyOnDataRecieved() {
        listener?.onDataRecieved(data)
    }

    fun registerListener(listener: OnDataRecieved) {
        this.listener = listener
        listener?.onDataRecieved(data)
    }

    fun unregisterListener() {
        listener = null
    }

    fun onStartDashboard() {
        CoroutineScope(Dispatchers.IO).launch {
            //data_IO = DataObtainer.getData()
            CoroutineScope(Dispatchers.Main).launch {
                data = data_IO
                notifyOnDataRecieved()
            }
        }
    }

    override fun onDataRepositoryLoad(datab: Data) {
        data_IO = datab
        onStartDashboard()
    }

}