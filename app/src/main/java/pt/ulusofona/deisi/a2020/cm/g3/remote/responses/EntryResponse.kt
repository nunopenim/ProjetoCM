package pt.ulusofona.deisi.a2020.cm.g3.remote.responses

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Data

class EntryResponse(var data: String, var data_dados: String, var confirmados: Int, var confirmados_arsnorte: Int,
                    var confirmados_arscentro: Int, var confirmados_arslvt: Int, var confirmados_arsalentejo: Int,
                    var confirmados_arsalgarve: Int, var confirmados_acores: Int, var confirmados_madeira: Int,
                    var confirmados_novos: Int, var recuperados: Int, var obitos: Int, var internados: Int?,
                    var internados_uci: Int?, var confirmados_0_9_f: Int?, var confirmados_0_9_m: Int?,
                    var confirmados_10_19_f: Int?, var confirmados_10_19_m: Int?, var confirmados_20_29_f: Int?,
                    var confirmados_20_29_m: Int?, var confirmados_30_39_f: Int?, var confirmados_30_39_m: Int?,
                    var confirmados_40_49_f: Int?, var confirmados_40_49_m: Int?, var confirmados_50_59_f: Int?,
                    var confirmados_50_59_m: Int?, var confirmados_60_69_f: Int?, var confirmados_60_69_m: Int?,
                    var confirmados_70_79_f: Int?, var confirmados_70_79_m: Int?, var confirmados_80_plus_f: Int?,
                    var confirmados_80_plus_m: Int?) {

    fun toDataObject(): Data {
        return Data(
            this.data,
            data_dados,
            confirmados,
            confirmados_arsnorte,
            confirmados_arscentro,
            confirmados_arslvt,
            confirmados_arsalentejo,
            confirmados_arsalgarve,
            confirmados_acores,
            confirmados_madeira,
            confirmados_novos,
            recuperados,
            obitos,
            internados,
            internados_uci,
            confirmados_0_9_f,
            confirmados_0_9_m,
            confirmados_10_19_f,
            confirmados_10_19_m,
            confirmados_20_29_f,
            confirmados_20_29_m,
            confirmados_30_39_f,
            confirmados_30_39_m,
            confirmados_40_49_f,
            confirmados_40_49_m,
            confirmados_50_59_f,
            confirmados_50_59_m,
            confirmados_60_69_f,
            confirmados_60_69_m,
            confirmados_70_79_f,
            confirmados_70_79_m,
            confirmados_80_plus_f,
            confirmados_80_plus_m
        )
    }

}