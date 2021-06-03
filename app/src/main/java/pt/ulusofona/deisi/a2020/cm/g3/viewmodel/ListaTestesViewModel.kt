package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import androidx.lifecycle.ViewModel
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import pt.ulusofona.deisi.a2020.cm.g3.logic.ListaTestesLogic

class ListaTestesViewModel : ViewModel()  {
    private val logic: ListaTestesLogic = ListaTestesLogic()

    fun onLoadAdapter() : TesteAdapter {
        return logic.onLoadGetAdapter()
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