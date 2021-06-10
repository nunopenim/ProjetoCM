package pt.ulusofona.deisi.a2020.cm.g3.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.extra.Teste
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.TestDao

class TestDetailLogic(private val storage: TestDao) {
    lateinit var testToAnalyse : Teste

    var improvisedSem = false

    private fun getTest(uuid: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val tst = storage.getSpecific(uuid)
            testToAnalyse = tst.convertToTeste()
            improvisedSem = true
        }
    }

    fun loadTest(uuid: String) : Teste{
        getTest(uuid)
        while(!improvisedSem){
            Thread.sleep(1)
        }
        improvisedSem = false
        return testToAnalyse
    }
}