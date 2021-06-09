package pt.ulusofona.deisi.a2020.cm.g3.interfaces

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Data

interface OnDataRepositoryLoad {
    fun onDataRepositoryLoad(datab: Data)
}