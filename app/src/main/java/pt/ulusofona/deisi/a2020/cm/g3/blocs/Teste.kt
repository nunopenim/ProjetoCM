package pt.ulusofona.deisi.a2020.cm.g3.blocs

import java.text.SimpleDateFormat
import java.util.*

class Teste (var local: String, var positivo: Boolean, var data: Date, var photo: String?) {

    fun isOlder(t2: Teste): Boolean {
        if(this.data < t2.data) {
            return true
        }
        return false
    }

    fun stringMyDate() : String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val str_date = formatter.format(data)
        return str_date
    }

    fun hasPhoto() : Boolean {
        if (photo == null || photo == "") {
            return false
        }
        return true
    }

}