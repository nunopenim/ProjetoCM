package pt.ulusofona.deisi.a2020.cm.g3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.DataDb

@Dao
interface DataDao {
    @Insert
    suspend fun insert(data: DataDb)

    @Update
    suspend fun update(data: DataDb)

    @Query("SELECT * FROM datadb WHERE uuid = :uuid")
    suspend fun getLatest(uuid: String) : DataDb
}
