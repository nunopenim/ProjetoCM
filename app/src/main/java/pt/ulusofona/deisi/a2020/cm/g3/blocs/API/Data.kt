package pt.ulusofona.deisi.a2020.cm.g3.blocs.API

import pt.ulusofona.deisi.a2020.cm.g3.data.entities.DataDb

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
            var confirmados_80m: Int?, var obitos_new: Int?, var recuperados_new: Int?, var internados_new: Int?){

    fun confirmados_female() : Array<Int?> {
        return arrayOf(confirmados_0_9f, confirmados_10_19f, confirmados_20_29f, confirmados_30_39f, confirmados_40_49f, confirmados_50_59f, confirmados_60_69f, confirmados_70_79f, confirmados_80f)
    }

    fun confirmados_male() : Array<Int?> {
        return arrayOf(confirmados_0_9m, confirmados_10_19m, confirmados_20_29m, confirmados_30_39m, confirmados_40_49m, confirmados_50_59m, confirmados_60_69m, confirmados_70_79m, confirmados_80m)
    }

    fun confirmados_general() : Array<Int> {
        return arrayOf(
            confirmados_female()[0]!! + confirmados_male()[0]!!,
            confirmados_female()[1]!! + confirmados_male()[1]!!,
            confirmados_female()[2]!! + confirmados_male()[2]!!,
            confirmados_female()[3]!! + confirmados_male()[3]!!,
            confirmados_female()[4]!! + confirmados_male()[4]!!,
            confirmados_female()[5]!! + confirmados_male()[5]!!,
            confirmados_female()[6]!! + confirmados_male()[6]!!,
            confirmados_female()[7]!! + confirmados_male()[7]!!,
            confirmados_female()[8]!! + confirmados_male()[8]!!
        )
    }

    fun getMaxConfirmadosGeneral() : Int {
        var max = 0
        for (i in confirmados_general()) {
            if (i > max) {
                max = i
            }
        }
        return max
    }

    fun getMaxConfirmados() : Int {
        var max = 0
        for (i in confirmados_female() + confirmados_male()) {
            if (i != null && i > max) {
                max = i
            }
        }
        return max
    }

    fun isEmpty() : Boolean{
        if(data == "" && data_dados == "") return true
        return false
    }

    fun convertToDataDb() : DataDb {
        return DataDb(data, data_dados, confirmados, confirmados_norte, confirmados_centro, confirmados_lvt, confirmados_alentejo, confirmados_algarve, confirmados_acores, confirmados_madeira, confirmados_novos, recuperados, obitos, internados, internados_UCI, confirmados_0_9f,  confirmados_0_9m, confirmados_10_19f, confirmados_10_19m, confirmados_20_29f, confirmados_20_29m, confirmados_30_39f, confirmados_30_39m,confirmados_40_49f,confirmados_40_49m,confirmados_50_59f,confirmados_50_59m,confirmados_60_69f,confirmados_60_69m,confirmados_70_79f,confirmados_70_79m,confirmados_80f,confirmados_80m,obitos_new, recuperados_new, internados_new)
    }

    //falta: taxa de sintomas, métodos
}