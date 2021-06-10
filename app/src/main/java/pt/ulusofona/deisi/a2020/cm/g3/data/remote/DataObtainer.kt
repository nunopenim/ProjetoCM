package pt.ulusofona.deisi.a2020.cm.g3.data.remote

import android.util.Log
import pt.ulusofona.deisi.a2020.cm.g3.extra.Data
import pt.ulusofona.deisi.a2020.cm.g3.extra.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.extra.SysTools
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.services.TodayEntry
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.services.VaccineEntry

class DataObtainer {
    companion object {
        const val API_URL = "https://covid19-api.vost.pt/"
        const val VacinasURL = "https://www.vacinacaocovid19.pt/"

        suspend fun getVaccines() : Vacinas {
            val service = RetrofitBuilder.getInstace(VacinasURL).create(VaccineEntry::class.java)
            val response = service.fetchLatest()
            if (response.isSuccessful) {
                return response.body()!![response.body()!!.size - 1].toVacinasObject()
            }
            return Vacinas("", 0, 0,0,0,0,0)
        }

        suspend fun getData() : Data {
            val service = RetrofitBuilder.getInstace(API_URL).create(TodayEntry::class.java)
            val data = fetchData()
            val response = service.fetchEntry(SysTools.subtractDayFromCurrentTime(1, data.data_dados))
            val obitos_new = data.obitos - (response["obitos"]!!.values.elementAt(0).toString().split(".")[0].toInt())
            val recuperados_new = data.recuperados - (response["recuperados"]!!.values.elementAt(0).toString().split(".")[0].toInt())
            val internados_new = data.internados!! - (response["internados"]!!.values.elementAt(0).toString().split(".")[0].toInt())
            data.obitos_new = obitos_new
            data.recuperados_new = recuperados_new
            data.internados_new = internados_new
            return data
        }

        private suspend fun fetchData() : Data {
            val service = RetrofitBuilder.getInstace(API_URL).create(TodayEntry::class.java)
            val response = service.fetchLatest()
            if(response.isSuccessful) {
                return response.body()!!.toDataObject()
            }
            return Data("", "", 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0, 0)
        }

        suspend fun getDistritoRisk(distrito: String) : Int{
            val service = RetrofitBuilder.getInstace(API_URL).create(TodayEntry::class.java)
            val response = service.getCounty(distrito)
            val risco = response[0].incidencia_risco
            if (risco == "Baixo a Moderado") {
                return 0
            }
            if (risco == "Moderado") {
                return 1
            }
            if (risco == "Elevado") {
                return 2
            }
            if (risco == "Extremamente Elevado") {
                return 3
            }
            return -1
        }
    }
}
