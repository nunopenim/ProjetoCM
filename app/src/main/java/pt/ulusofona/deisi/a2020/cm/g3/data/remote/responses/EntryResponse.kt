package pt.ulusofona.deisi.a2020.cm.g3.data.remote.responses

import pt.ulusofona.deisi.a2020.cm.g3.extra.Data

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
        val data_ret = Data(data, data_dados, confirmados,confirmados_arsnorte,confirmados_arscentro,confirmados_arslvt,confirmados_arsalentejo,confirmados_arsalgarve,confirmados_acores,confirmados_madeira,confirmados_novos,recuperados,obitos,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0, 0)
        if(internados != null) {
            data_ret.internados = internados
        }
        if(internados_uci != null) {
            data_ret.internados_UCI = internados_uci
        }
        if(confirmados_0_9_f != null) {
            data_ret.confirmados_0_9f = confirmados_0_9_f
        }
        if(confirmados_0_9_m != null) {
            data_ret.confirmados_0_9m = confirmados_0_9_m
        }
        if(confirmados_10_19_f != null) {
            data_ret.confirmados_10_19f = confirmados_10_19_f
        }
        if(confirmados_10_19_m != null) {
            data_ret.confirmados_10_19m = confirmados_10_19_m
        }
        if(confirmados_20_29_f != null) {
            data_ret.confirmados_20_29f = confirmados_20_29_f
        }
        if(confirmados_20_29_m != null) {
            data_ret.confirmados_20_29m = confirmados_20_29_m
        }
        if(confirmados_30_39_f != null) {
            data_ret.confirmados_30_39f = confirmados_30_39_f
        }
        if(confirmados_30_39_m != null) {
            data_ret.confirmados_30_39m = confirmados_30_39_m
        }
        if(confirmados_40_49_f != null) {
            data_ret.confirmados_40_49f = confirmados_40_49_f
        }
        if(confirmados_40_49_m != null) {
            data_ret.confirmados_40_49m = confirmados_40_49_m
        }
        if(confirmados_50_59_f != null) {
            data_ret.confirmados_50_59f = confirmados_50_59_f
        }
        if(confirmados_50_59_m != null) {
            data_ret.confirmados_50_59m = confirmados_50_59_m
        }
        if(confirmados_60_69_f != null) {
            data_ret.confirmados_60_69f = confirmados_60_69_f
        }
        if(confirmados_60_69_m != null) {
            data_ret.confirmados_60_69m = confirmados_60_69_m
        }
        if(confirmados_70_79_f != null) {
            data_ret.confirmados_70_79f = confirmados_70_79_f
        }
        if(confirmados_70_79_m != null) {
            data_ret.confirmados_70_79m = confirmados_70_79_m
        }
        if(confirmados_80_plus_f!= null) {
            data_ret.confirmados_80f = confirmados_80_plus_f
        }
        if(confirmados_80_plus_m != null) {
            data_ret.confirmados_80m = confirmados_80_plus_m
        }
        return data_ret
    }

}
