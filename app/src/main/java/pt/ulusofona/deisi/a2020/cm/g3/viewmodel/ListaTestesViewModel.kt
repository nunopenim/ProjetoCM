package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.util.Log
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.extra.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.extra.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.TestsDatabase
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnLoadTestsList
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnTestListToView
import pt.ulusofona.deisi.a2020.cm.g3.logic.ListaTestesLogic

class ListaTestesViewModel(application: Application) : AndroidViewModel(application), OnLoadTestsList  {

    private val storage = TestsDatabase.getInstace(application).testDao()
    private val logic: ListaTestesLogic = ListaTestesLogic(storage)
    var adapter: TesteAdapter = TesteAdapter(ArrayList<Teste>())

    private var listener: OnTestListToView? = null

    private fun notifyOnTestListUpdated() {
        listener?.onTestListToView(adapter)
    }

    fun registerListener(listener: OnTestListToView) {
        this.listener = listener
        listener?.onTestListToView(adapter)
    }

    fun unregisterListener() {
        this.listener = null
    }

    fun onLoadAdapter() {
        logic.registerListener(this)
        logic.loadFromDB()
    }

    fun onUnload() {
        logic.unregisterListener()
    }

    fun getList() : List<Teste> {
        return adapter.testList
    }

    fun orderCrescente() {
        logic.sortCrescente()
    }

    fun orderDecrescente() {
        logic.sortDecrescente()
    }

    override fun onLoadTestsList(list: TesteAdapter) {
        adapter = list
        notifyOnTestListUpdated()
    }
}