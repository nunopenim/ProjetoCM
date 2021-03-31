package pt.ulusofona.deisi.a2020.cm.g3.blocs.API

import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.util.*

class FakeAPI {

    fun fakeTests() : List<Teste> {
        val t1 = Teste("farmacia 1", true, Date(1617197288))
        val t2 = Teste("farmacia 2", false, Date(1617097288))
        val t3 = Teste("farmacia 3", false, Date(1617097288))
        val lt = ArrayList<Teste>()
        lt.add(t1)
        lt.add(t2)
        lt.add(t3)
        return lt
    }

    fun fakeData() : Data {
        return Data("2021-03-31", "2021-03-30", 800000, 400000, 100000,100000, 200000, 200000, 100000, 200000, 230, 700000, 10000, 10, 10)
    }

    fun fakeVaccines() : Vacinas {
        return Vacinas("2021-03-31", 1000, 250, 800, 200, 200, 50)
    }

}