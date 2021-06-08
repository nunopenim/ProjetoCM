package pt.ulusofona.deisi.a2020.cm.g3.data.entities

import android.graphics.BitmapFactory
import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import java.util.*

@Entity
data class Test (@PrimaryKey var uuid: String, var local: String, var positivo: Boolean, var data: Long, val photo: ByteArray?) {
    fun convertToTeste() : Teste {
        return if (photo == null) {
            val returnMe = Teste(local, positivo, Date(data), null)
            returnMe.uuid = uuid
            returnMe
        } else {
            val returnMe = Teste(local, positivo, Date(data), BitmapFactory.decodeByteArray(photo, 0, photo.size))
            returnMe.uuid = uuid
            returnMe
        }
    }
}
