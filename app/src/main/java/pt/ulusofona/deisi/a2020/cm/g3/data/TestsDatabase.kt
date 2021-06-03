package pt.ulusofona.deisi.a2020.cm.g3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.TestDao
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.Test

@Database(entities = arrayOf(Test::class), version = 1)
abstract class TestsDatabase : RoomDatabase() {
    abstract fun testDao(): TestDao

    companion object {
        private var instance: TestsDatabase? = null

        fun getInstace(applicationContext: Context): TestsDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(applicationContext, TestsDatabase::class.java, "tests_db").build()
                }
                return instance as TestsDatabase
            }
        }
    }

}