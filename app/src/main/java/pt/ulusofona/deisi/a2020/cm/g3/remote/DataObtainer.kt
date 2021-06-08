package pt.ulusofona.deisi.a2020.cm.g3.remote

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Data
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.remote.services.TodayEntry
import pt.ulusofona.deisi.a2020.cm.g3.remote.services.VaccineEntry

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

        suspend fun getData() : Data{
            val service = RetrofitBuilder.getInstace(API_URL).create(TodayEntry::class.java)
            val response = service.fetchLatest()
            if(response.isSuccessful) {
                return response.body()!!.toDataObject()
            }
            return Data("", "", 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0)
        }
    }
}
