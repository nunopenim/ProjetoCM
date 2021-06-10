package pt.ulusofona.deisi.a2020.cm.g3.data.remote.services

import pt.ulusofona.deisi.a2020.cm.g3.data.remote.responses.VaccineResponse
import retrofit2.Response
import retrofit2.http.GET

interface VaccineEntry {
    @GET("/api/vaccines")
    suspend fun fetchLatest(): Response<Array<VaccineResponse>>
}