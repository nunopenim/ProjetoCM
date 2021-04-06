package pt.ulusofona.deisi.a2020.cm.g3.blocs.API

class Data (var data: String, var data_dados: String, var confirmados: Int, var confirmados_norte: Int,
            var confirmados_centro: Int, var confirmados_lvt: Int, var confirmados_alentejo: Int,
            var confirmados_algarve: Int, var confirmados_acores: Int, var confirmados_madeira: Int,
            var confirmados_novos: Int, var recuperados: Int, var obitos: Int, var internados: Int?,
            var internados_UCI: Int?, var confirmados_0_9f: Int?, var confirmados_0_9m: Int?,
            var confirmados_10_19f: Int?, var confirmados_10_19m: Int?, var confirmados_20_29f: Int?,
            var confirmados_20_29m: Int?, var confirmados_30_39f: Int?, var confirmados_30_39m: Int?,
            var confirmados_40_49f: Int?, var confirmados_40_49m: Int?, var confirmados_50_59f: Int?,
            var confirmados_50_59m: Int?, var confirmados_60_69f: Int?, var confirmados_60_69m: Int?,
            var confirmados_70_79f: Int?, var confirmados_70_79m: Int?, var confirmados_80f: Int?,
            var confirmados_80m: Int?){

    fun confirmados_female() : Array<Int?> {
        return arrayOf(confirmados_0_9f, confirmados_10_19f, confirmados_20_29f, confirmados_30_39f, confirmados_40_49f, confirmados_50_59f, confirmados_60_69f, confirmados_70_79f, confirmados_80f)
    }

    fun confirmados_male() : Array<Int?> {
        return arrayOf(confirmados_0_9m, confirmados_10_19m, confirmados_20_29m, confirmados_30_39m, confirmados_40_49m, confirmados_50_59m, confirmados_60_69m, confirmados_70_79m, confirmados_80m)
    }

    //falta: taxa de sintomas, métodos
}