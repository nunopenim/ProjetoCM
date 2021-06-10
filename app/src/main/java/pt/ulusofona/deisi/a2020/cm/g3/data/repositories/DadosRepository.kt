package pt.ulusofona.deisi.a2020.cm.g3.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.extra.Data
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.DataDao
import pt.ulusofona.deisi.a2020.cm.g3.interfaces.OnDataRepositoryLoad
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.DataObtainer
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
                if(IO_data.confirmados_0_9f == 0) {
                    val obj = local.getLatest("1").convertToDataObject()
                    if (obj != null) {
                        IO_data.confirmados_0_9f = obj.confirmados_0_9f
                        IO_data.confirmados_0_9m = obj.confirmados_0_9m
                        IO_data.confirmados_10_19f = obj.confirmados_10_19f
                        IO_data.confirmados_10_19m = obj.confirmados_10_19m
                        IO_data.confirmados_20_29f = obj.confirmados_20_29f
                        IO_data.confirmados_20_29m = obj.confirmados_20_29m
                        IO_data.confirmados_30_39f = obj.confirmados_30_39f
                        IO_data.confirmados_30_39m = obj.confirmados_30_39m
                        IO_data.confirmados_40_49f = obj.confirmados_40_49f
                        IO_data.confirmados_40_49m = obj.confirmados_40_49m
                        IO_data.confirmados_50_59f = obj.confirmados_50_59f
                        IO_data.confirmados_50_59m = obj.confirmados_50_59m
                        IO_data.confirmados_60_69f = obj.confirmados_60_69f
                        IO_data.confirmados_60_69m = obj.confirmados_60_69m
                        IO_data.confirmados_70_79f = obj.confirmados_70_79f
                        IO_data.confirmados_70_79m = obj.confirmados_70_79m
                        IO_data.confirmados_80f = obj.confirmados_80f
                        IO_data.confirmados_80m = obj.confirmados_80m
                    }
                }
            } catch(e: Exception) {
                val obj = local.getLatest("1")
                if (obj != null) {
                    IO_data = obj.convertToDataObject()
                }
            }
            data = IO_data
            notifyOnDataLoaded()
            if(ableToLoad) {
                val obj = local.getLatest("1")
                if (obj != null) {
                    local.update(IO_data.convertToDataDb())
                }
                else {
                    local.insert(IO_data.convertToDataDb())
                }
            }
        }
    }
}