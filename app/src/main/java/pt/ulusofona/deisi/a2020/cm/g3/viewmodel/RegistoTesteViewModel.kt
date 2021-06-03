package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import pt.ulusofona.deisi.a2020.cm.g3.logic.RegistoTesteLogic
import java.util.*

class RegistoTesteViewModel : ViewModel() {

    val logic: RegistoTesteLogic = RegistoTesteLogic()

    fun onClickSave(local: String, result: Boolean, date: Date, pic: Bitmap?) {
        logic.addTest(local, result, date, pic)
    }
}