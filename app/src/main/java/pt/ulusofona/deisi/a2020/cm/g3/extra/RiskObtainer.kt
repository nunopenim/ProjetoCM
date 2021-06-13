package pt.ulusofona.deisi.a2020.cm.g3.extra

import android.app.Activity
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.DataObtainer
import java.lang.Exception

class RiskObtainer {
    companion object {
        private var riskLevel = -1
        private var hasRisk = false

        var distriot: String = ""

        fun sortRiskStuff(tview : TextView, activity: Activity) {
            riskLevel = -1
            hasRisk = false
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val riskIO = DataObtainer.getDistritoRisk(distriot)
                    riskLevel = riskIO
                    hasRisk = true
                } catch (e: Exception) {
                    if (e.message!!.contains("500")) {
                        riskLevel = -1
                        hasRisk = true
                    }
                    else {
                        riskLevel = -2
                        hasRisk = false
                    }
                }
                GlobalRisk.risco = riskLevel
                CoroutineScope(Dispatchers.Main).launch {
                    if(!hasRisk || GlobalRisk.risco == -2) {
                        DangerChanger.setToNoConnection(tview, activity)
                    }
                    else if (GlobalRisk.risco == -1) {
                        DangerChanger.setToUnknown(tview, activity)
                    }
                    else if(GlobalRisk.risco == 0) {
                        DangerChanger.setToSafe(tview, activity)
                    }
                    else if(GlobalRisk.risco == 1) {
                        DangerChanger.setToModerate(tview, activity)
                    }
                    else if(GlobalRisk.risco == 2) {
                        DangerChanger.setToRisky(tview, activity)
                    }
                    else if(GlobalRisk.risco == 3) {
                        DangerChanger.setToDangerous(tview, activity)
                    }
                }
            }

        }
    }
}
