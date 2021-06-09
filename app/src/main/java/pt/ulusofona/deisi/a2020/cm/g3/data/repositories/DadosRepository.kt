package pt.ulusofona.deisi.a2020.cm.g3.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Data
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.DataDao
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRepositoryLoad
import pt.ulusofona.deisi.a2020.cm.g3.remote.DataObtainer
import java.lang.Exception

class DadosRepository(private val local: DataDao) {

    private var listener: OnDataRepositoryLoad? = null

    var data = Data("", "",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0, 0)
    var IO_data = Data("", "",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0, 0, 0, 0)

    private fun notifyOnDataLoaded() {
        listener?.onDataRepositoryLoad(data)
    }

    fun registerListener(listener: OnDataRepositoryLoad) {
        this.listener = listener
        getData()
        listener?.onDataRepositoryLoad(data)
    }

    fun unregisterListener() {
        listener = null
    }

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            var ableToLoad = false
            try {
                IO_data = DataObtainer.getData()
                ableToLoad = true
            } catch(e: Exception) {
                val obj = local.getLatest("1")
                if (obj != null) {
                    IO_data = obj.convertToDataObject()
                }
            }
            data = IO_data
            notifyOnDataLoaded()
            if(ableToLoad) {
                local.update(IO_data.convertToDataDb())
            }
        }
    }
}