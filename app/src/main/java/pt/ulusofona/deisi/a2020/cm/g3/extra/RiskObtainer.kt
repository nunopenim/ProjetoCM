package pt.ulusofona.deisi.a2020.cm.g3.extra

import android.app.Activity
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.DataObtainer
import java.lang.Exception

class RiskObtainer(val distriot: String) {
    private var riskLevel = -1
    private var hasRisk = false

    fun sortRiskStuff(tview : TextView, activity: Activity) {
        riskLevel = -1
        hasRisk = false
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val riskIO = DataObtainer.getDistritoRisk(distriot)
                riskLevel = riskIO
                hasRisk = true
            } catch (e: Exception) {
                Log.i("RiskObtainer", "Father, I have failed you")
                hasRisk = true
            }
            GlobalRisk.risco = riskLevel
            CoroutineScope(Dispatchers.Main).launch {
                if (GlobalRisk.risco == -1 || !hasRisk) {
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
