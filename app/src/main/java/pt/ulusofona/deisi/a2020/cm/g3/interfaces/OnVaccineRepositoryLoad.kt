package pt.ulusofona.deisi.a2020.cm.g3.interfaces

import pt.ulusofona.deisi.a2020.cm.g3.blocs.API.Vacinas

interface OnVaccineRepositoryLoad {
    fun onVaccineRepositoryLoad(vaccine: Vacinas)
}