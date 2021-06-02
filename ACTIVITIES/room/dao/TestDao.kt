package pt.ulusofona.deisi.a2020.cm.g3.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pt.ulusofona.deisi.a2020.cm.g3.room.entities.Test

@Dao
interface TestDao {
    @Insert
    suspend fun insert(test: Test)

    @Query("SELECT * FROM test")
    suspend fun getAll(): List<Test>

    @Query("SELECT * FROM test WHERE uuid = :uuid")
    suspend fun getSpecific(uuid: String): Test
}