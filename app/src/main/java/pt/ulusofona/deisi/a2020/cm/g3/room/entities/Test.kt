package pt.ulusofona.deisi.a2020.cm.g3.room.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Teste (var local: String, var positivo: Boolean, var data: Date, var photo: Bitmap?) {
    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString()
}