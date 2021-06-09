package pt.ulusofona.deisi.a2020.cm.g3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.VacinaDb

@Dao
interface VacinaDao {
    @Insert
    suspend fun insert(vacina: VacinaDb)

    @Update
    suspend fun update(vacina: VacinaDb)

    @Query("SELECT * FROM vacinadb WHERE uuid = :uuid")
    suspend fun getLatest(uuid: String) : VacinaDb
}