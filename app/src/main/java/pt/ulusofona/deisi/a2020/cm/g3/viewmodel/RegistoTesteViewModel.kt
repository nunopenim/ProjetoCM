package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2020.cm.g3.data.TestsDatabase
import pt.ulusofona.deisi.a2020.cm.g3.logic.RegistoTesteLogic
import java.util.*

class RegistoTesteViewModel(application: Application) : AndroidViewModel(application) {

    private val storage = TestsDatabase.getInstace(application).testDao()
    private val logic: RegistoTesteLogic = RegistoTesteLogic(storage)

    fun onClickSave(local: String, result: Boolean, date: Date, pic: Bitmap?) {
        logic.addTest(local, result, date, pic)
    }
}