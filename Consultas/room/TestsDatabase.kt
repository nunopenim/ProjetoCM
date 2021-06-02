package pt.ulusofona.deisi.a2020.cm.g3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g3.room.dao.TestDao
import pt.ulusofona.deisi.a2020.cm.g3.room.entities.Test

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