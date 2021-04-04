package pt.ulusofona.deisi.a2020.cm.g3.blocs

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.FakeAPI

object InfoSingleton {
    var testList = ArrayList<Teste>()

    fun addTestToList(teste: Teste) {
        testList.add(teste)
    }

    fun removeTestFromList(teste: Teste) {
        testList.remove(teste)
    }

    fun initTestListDebug() {
        testList = FakeAPI.fakeTests()
    }
}