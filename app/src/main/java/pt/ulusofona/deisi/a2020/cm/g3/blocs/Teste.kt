package pt.ulusofona.deisi.a2020.cm.g3.blocs

import java.util.*

class Teste (var local: String, var positivo: Boolean, var data: Date) {

    fun isOlder(t2: Teste): Boolean {
        if(this.data < t2.data) {
            return true
        }
        return false
    }

}