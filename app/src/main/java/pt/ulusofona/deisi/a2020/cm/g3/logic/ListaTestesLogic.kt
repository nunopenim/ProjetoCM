package pt.ulusofona.deisi.a2020.cm.g3.logic

import kotlinx.coroutines.*
import pt.ulusofona.deisi.a2020.cm.g3.extra.TesteAdapter
import pt.ulusofona.deisi.a2020.cm.g3.extra.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.TestDao
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnLoadTestsList
import kotlin.collections.ArrayList

class ListaTestesLogic(private val storage: TestDao) {

    //var loaded = false //remover!! usar observable-observer

    private var listener: OnLoadTestsList? = null

    var testList = ArrayList<Teste>()
    lateinit var adapter : TesteAdapter

    private fun notifyOnListLoaded() {
        listener?.onLoadTestsList(adapter)
    }

    fun registerListener(listener: OnLoadTestsList) {
        this.listener = listener
    }

    fun unregisterListener() {
        this.listener = null
    }

    fun loadFromDB() {
        CoroutineScope(Dispatchers.IO).launch {
            val lista = ArrayList<Teste>()
            val lst = storage.getAll()
            for (i in lst) {
                lista.add(i.convertToTeste())
            }
            testList = lista
            CoroutineScope(Dispatchers.Main).launch {
                adapter = TesteAdapter(testList)
                notifyOnListLoaded()
            }
        }
        /*CoroutineScope(Dispatchers.IO).launch {
            testList = ArrayList()
            val lst = storage.getAll()
            for (i in lst) {
                testList.add(i.convertToTeste())
            }
            loaded = true //remover!! usar observable-observer
        }*/
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