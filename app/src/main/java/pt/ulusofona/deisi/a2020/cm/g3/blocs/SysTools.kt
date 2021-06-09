package pt.ulusofona.deisi.a2020.cm.g3.blocs

import java.text.SimpleDateFormat
import java.util.*

class SysTools {
    companion object {
        fun subtractDayFromCurrentTime(amount: Int, currentDateString: String): String {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val dateFromCurrent: Date = sdf.parse(currentDateString)
            val cal = GregorianCalendar()
            cal.time = dateFromCurrent
            cal.add(Calendar.DATE, -amount)
            return sdf.format(cal.time)
        }
        fun getCurrentDateNumber(currentDateString: String) : Long {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val dateFromCurrent: Date = sdf.parse(currentDateString)
            val cal = GregorianCalendar()
            cal.time = dateFromCurrent
            return cal.timeInMillis
        }
    }
}