package pt.ulusofona.deisi.a2020.cm.g3.blocs.API

import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.util.*
import kotlin.collections.ArrayList

class FakeAPI {
    companion object {

        fun fakeData() : Data {
            return Data("2021-03-31", "2021-03-30", 800000, 400000, 100000,100000, 200000, 200000, 100000, 200000, 230, 700000, 10000, 10, 10, 10, 10, 200, 200, 100, 100, 30, 20, 55, 100, 150, 165, 75, 55, 28, 30, 10, 10)
        }

        fun fakeVaccines() : Vacinas {
            return Vacinas("2021-03-31", 1000, 250, 800, 200, 200, 50)
        }
    }
}