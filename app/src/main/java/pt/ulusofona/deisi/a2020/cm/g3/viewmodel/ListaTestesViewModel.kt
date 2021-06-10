package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g3.extra.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.extra.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.TestsDatabase
import pt.ulusofona.deisi.a2020.cm.g3.logic.ListaTestesLogic

class ListaTestesViewModel(application: Application) : AndroidViewModel(application)  {

    private val storage = TestsDatabase.getInstace(application).testDao()
    private val logic: ListaTestesLogic = ListaTestesLogic(storage)

    fun onLoadAdapter() : TesteAdapter {
        logic.loadFromDB()
        val adapter = logic.onLoadGetAdapter()
        return adapter
    }

    fun getList() : List<Teste> {
        return logic.getTestList()
    }

    fun orderCrescente() {
        logic.sortCrescente()
    }

    fun orderDecrescente() {
        logic.sortDecrescente()
    }

}