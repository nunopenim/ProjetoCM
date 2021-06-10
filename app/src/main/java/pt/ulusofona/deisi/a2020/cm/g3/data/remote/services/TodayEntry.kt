package pt.ulusofona.deisi.a2020.cm.g3.data.remote.services

import com.google.gson.internal.LinkedTreeMap
import pt.ulusofona.deisi.a2020.cm.g3.data.remote.responses.EntryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodayEntry {

    @GET("/Requests/get_last_update")
    suspend fun fetchLatest(): Response<EntryResponse>

    @GET("Requests/get_entry/{date}")
    suspend fun fetchEntry(@Path(value="date") date: String): LinkedTreeMap<String, LinkedTreeMap<Any, Any>>

    @GET("/Requests/get_last_update_specific_county/{county}")
    suspend fun getCounty(@Path(value="county") county: String): LinkedTreeMap<String, LinkedTreeMap<Any, Any>>
}