package pt.ulusofona.deisi.a2020.cm.g3.blocs

import java.util.*
import kotlin.collections.ArrayList

object InfoSingleton {
    var testList = ArrayList<Teste>()

    fun addTestToList(teste: Teste) {
        testList.add(teste)
    }

    fun removeTestFromList(teste: Teste) {
        testList.remove(teste)
    }

    fun initTestListDebug() {
        val lt = ArrayList<Teste>()
        for (i in 0..20) {
            val t1 = Teste("Farmácia $i", i%2==0, Date())
            lt.add(t1)
        }
        testList = lt
    }

}