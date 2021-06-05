package pt.ulusofona.deisi.a2020.cm.g3.blocs.GUI

import android.app.Activity
import android.graphics.Color
import android.widget.TextView
import pt.ulusofona.deisi.a2020.cm.g3.R

class DangerChanger {
    companion object {
        fun setToDangerous(tview : TextView, activity: Activity) {
            tview.text = activity.getString(R.string.danger)
            tview.setTextColor(Color.WHITE)
            tview.setBackgroundColor(Color.rgb(255, 0, 0))
        }
        fun setToRisky(tview : TextView, activity: Activity) {
            tview.text = activity.getString(R.string.risky)
            tview.setTextColor(Color.WHITE)
            tview.setBackgroundColor(Color.rgb(255, 165, 0))
        }
        fun setToModerate(tview : TextView, activity: Activity) {
            tview.text = activity.getString(R.string.moderate)
            tview.setTextColor(Color.BLACK)
            tview.setBackgroundColor(Color.rgb(255, 255, 0))
        }
        fun setToSafe(tview : TextView, activity: Activity) {
            tview.text = activity.getString(R.string.safe)
            tview.setTextColor(Color.BLACK)
            tview.setBackgroundColor(Color.rgb(0, 255, 0))
        }
    }
}