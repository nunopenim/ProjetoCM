package pt.ulusofona.deisi.a2020.cm.g3.views

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment

abstract class PermissionsFragment (private val requestCode: Int): Fragment() {
    fun onRequestPermissions(context: Context, permissions: Array<String>) {
        var permsGived = 0
        permissions.forEach {
            if (checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, requestCode)
            }
            else {
                permsGived ++
            }
            if (permsGived == permissions.size) onRequestPermissionsSuccess()
        }
    }
    abstract fun onRequestPermissionsSuccess()
    abstract fun onRequestPermissionsFailrule()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if(this.requestCode == requestCode) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                onRequestPermissionsSuccess()
            }
            else {
                onRequestPermissionsFailrule()
            }
        }
    }
}