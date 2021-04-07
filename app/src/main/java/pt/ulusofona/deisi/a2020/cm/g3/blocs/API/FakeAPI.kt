package pt.ulusofona.deisi.a2020.cm.g3.blocs.API

import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.util.*
import kotlin.collections.ArrayList

class FakeAPI {
    companion object {

        fun fakeData() : Data {
            return Data("2021-03-31", "2021-03-30", 824368, 400000, 100000,100000, 200000, 200000, 100000, 200000, 230, 781537, 16887, 537, 10, 10, 10, 200, 200, 100, 100, 30, 20, 55, 100, 150, 165, 75, 55, 28, 30, 10, 10)
        }

        fun fakeVaccines() : Vacinas {
            return Vacinas("2021-03-31", 1867470, 250, 1309681, 200, 557789, 50)
        }
    }
}