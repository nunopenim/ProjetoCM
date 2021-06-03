package pt.ulusofona.deisi.a2020.cm.g3.logic

import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste

class ListaTestesLogic {

    val adapter = TesteAdapter(InfoSingleton.testList)

    fun onLoadGetAdapter() : TesteAdapter {
        return adapter
    }

    fun getTestList() : List<Teste>{
        return adapter.testList
    }

    fun sortCrescente() {
        adapter.testList = InfoSingleton.testList.sortedBy { it.data.time }
        adapter.notifyDataSetChanged()
    }

    fun sortDecrescente() {
        adapter.testList = InfoSingleton.testList.sortedByDescending { it.data.time }
        adapter.notifyDataSetChanged()
    }
}