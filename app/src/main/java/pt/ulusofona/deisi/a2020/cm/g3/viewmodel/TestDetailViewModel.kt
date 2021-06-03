package pt.ulusofona.deisi.a2020.cm.g3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pt.ulusofona.deisi.a2020.cm.g3.blocs.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.TestsDatabase
import pt.ulusofona.deisi.a2020.cm.g3.logic.TestDetailLogic

class TestDetailViewModel(application: Application) : AndroidViewModel(application)    {
    private val storage = TestsDatabase.getInstace(application).testDao()
    private val logic: TestDetailLogic = TestDetailLogic(storage)

    fun loadTeste(uuid: String) : Teste {
        return logic.loadTest(uuid)
    }

}