package pt.ulusofona.deisi.a2020.cm.g3.remote.services

import pt.ulusofona.deisi.a2020.cm.g3.remote.responses.EntryResponse
import retrofit2.Response
import retrofit2.http.GET

interface TodayEntry {

    @GET("/Requests/get_last_update")
    suspend fun fetchLatest(): Response<EntryResponse>
}