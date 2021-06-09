package pt.ulusofona.deisi.a2020.cm.g3.data.repositories

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.VacinaDao
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnVaccineRepositoryLoad
import pt.ulusofona.deisi.a2020.cm.g3.remote.DataObtainer
import java.lang.Exception

class VacinasRepository(private val local: VacinaDao) {

    private var listener: OnVaccineRepositoryLoad? = null

    var vacinas = Vacinas("", 0, 0, 0, 0, 0,0)
    var vacinas_IO = Vacinas("", 0, 0, 0, 0, 0,0)

    private fun notifyOnDataLoaded() {
        listener?.onVaccineRepositoryLoad(vacinas)
    }

    fun registerListener(listener: OnVaccineRepositoryLoad) {
        this.listener = listener
        getData()
        listener?.onVaccineRepositoryLoad(vacinas)
    }

    fun unregisterListener() {
        this.listener = null
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            var ableToLoad = false
            try {
                vacinas_IO = DataObtainer.getVaccines()
                ableToLoad = true
            } catch(e: Exception) {
                val obj = local.getLatest("1")
                if (obj != null) {
                    vacinas_IO = obj.convertToVacinasObject()
                }
            }
            vacinas = vacinas_IO
            notifyOnDataLoaded()
            if(ableToLoad) {
                val obj = local.getLatest("1")
                if (obj != null) {
                    local.update(vacinas.convertToVacinaDb())
                }
                else {
                    local.insert(vacinas.convertToVacinaDb())
                }
            }
        }
    }
}