package pt.ulusofona.deisi.a2020.cm.g3.data.entities

import androidx.room.PrimaryKey
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas

data class VacinaDb(var data: String, var doses: Int?, var doses_novas: Int?,
                    var doses1: Int?, var doses1_novas: Int?, var doses2: Int?, var doses2_novas: Int?) {

    @PrimaryKey
    var uuid = "1" // It's a surprise too that will help us with SQL :)

    fun convertToVacinasObject() : Vacinas{
        return Vacinas(data, doses, doses_novas, doses1, doses1_novas, doses2, doses2_novas)
    }
}