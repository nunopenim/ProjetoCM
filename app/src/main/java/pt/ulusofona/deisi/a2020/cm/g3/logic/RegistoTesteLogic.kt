package pt.ulusofona.deisi.a2020.cm.g3.logic

import android.graphics.Bitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.extra.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.TestDao
import java.util.*

class RegistoTesteLogic (private val storage: TestDao){

    fun addTest(local: String, result: Boolean, date: Date, pic: Bitmap?) {
        CoroutineScope(Dispatchers.IO).launch {
            storage.insert(Teste(local, result, date, pic).convertToTest())
        }
    }

}