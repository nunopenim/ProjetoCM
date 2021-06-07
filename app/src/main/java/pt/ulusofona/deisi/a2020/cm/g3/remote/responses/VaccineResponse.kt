package pt.ulusofona.deisi.a2020.cm.g3.remote.responses

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas

class VaccineResponse(var Data: Long, var Inoculacao1: Long, var Inoculacao1_Ac: Long, var Inoculacao2: Long,
                        var Inoculacao2_Ac: Long, var Vacinados: Long, var Vacinados_Ac: Long) {

    fun toVacinasObject() : Vacinas {
        return Vacinas(this.Data.toString(), Vacinados_Ac.toInt(), Vacinados.toInt(), Inoculacao1_Ac.toInt(), Inoculacao1.toInt(), Inoculacao2_Ac.toInt(), Inoculacao2.toInt())
    }
}
