package pt.ulusofona.deisi.a2020.cm.g3.extra

import pt.ulusofona.deisi.a2020.cm.g3.data.entities.VacinaDb

class Vacinas (var data: String, var doses: Int?, var doses_novas: Int?,
               var doses1: Int?, var doses1_novas: Int?, var doses2: Int?, var doses2_novas: Int?) {

    fun convertToVacinaDb() : VacinaDb {
        return VacinaDb(data, doses, doses_novas,doses1, doses1_novas, doses2, doses2_novas)
    }
}