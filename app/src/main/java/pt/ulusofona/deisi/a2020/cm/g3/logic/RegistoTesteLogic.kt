package pt.ulusofona.deisi.a2020.cm.g3.logic

import android.graphics.Bitmap
import pt.ulusofona.deisi.a2020.cm.g3.blocs.InfoSingleton
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.util.*

class RegistoTesteLogic {

    fun addTest(local: String, result: Boolean, date: Date, pic: Bitmap?) {
        InfoSingleton.addTestToList(Teste(local, result, date, pic))
    }

}