package pt.ulusofona.deisi.a2020.cm.g3.blocs

import android.graphics.Bitmap
import android.util.Log
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.Test
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class Teste (var local: String, var positivo: Boolean, var data: Date, var photo: Bitmap?) {

    var uuid = UUID.randomUUID().toString()

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
        if (photo == null) {
            return false
        }
        return true
    }

    fun convertToTest() : Test {
        if (photo == null) {
            return Test(uuid, local, positivo, data.time, null)
        }
        else {
            val baos = ByteArrayOutputStream()
            photo!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val id = baos.toByteArray()
            return Test(uuid, local, positivo, data.time, id)

        }
    }

}