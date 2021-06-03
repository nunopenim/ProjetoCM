package pt.ulusofona.deisi.a2020.cm.g3.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.Test

@Dao
interface TestDao {
    @Insert
    suspend fun insert(test: Test)

    @Query("SELECT * FROM test")
    suspend fun getAll(): List<Test>

    @Query("SELECT * FROM test WHERE uuid = :uuid")
    suspend fun getSpecific(uuid: String): Test
}