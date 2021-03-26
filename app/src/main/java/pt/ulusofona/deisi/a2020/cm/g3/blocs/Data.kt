package pt.ulusofona.deisi.a2020.cm.g3.blocs

class Data (var data: String, var data_dados: String, var confirmados: Int, var confirmados_norte: Int,
            var confirmados_centro: Int, var confirmados_lvt: Int, var confirmados_alentejo: Int,
            var confirmados_algarve: Int, var confirmados_acores: Int, var confirmados_madeira: Int,
            var confirmados_novos: Int, var recuperados: Int, var obitos: Int, var internados: Int?,
            var internados_UCI: Int?){
    //falta: taxa de sintomas, faixas etárias
}