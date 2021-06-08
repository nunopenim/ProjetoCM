package pt.ulusofona.deisi.a2020.cm.g3.logic

import kotlinx.coroutines.*
import pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.TestDao
import kotlin.collections.ArrayList

class ListaTestesLogic(private val storage: TestDao) {

    var loaded = false //remover!! usar observable-observer

    var testList = ArrayList<Teste>()
    lateinit var adapter : TesteAdapter

    fun loadFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            testList = ArrayList()
            val lst = storage.getAll()
            for (i in lst) {
                testList.add(i.convertToTeste())
            }
            loaded = true //remover!! usar observable-observer
        }
    }

    fun onLoadGetAdapter() : TesteAdapter {

        while(!loaded) {
            Thread.sleep(1)
        }
        loaded = false //remover!! usar observable-observer

        adapter = TesteAdapter(testList)
        return adapter
    }

    fun getTestList() : List<Teste>{
        return adapter.testList
    }

    fun sortCrescente() {
        adapter.testList = testList.sortedBy { it.data.time }
        adapter.notifyDataSetChanged()
    }

    fun sortDecrescente() {
        adapter.testList = testList.sortedByDescending { it.data.time }
        adapter.notifyDataSetChanged()
    }
}