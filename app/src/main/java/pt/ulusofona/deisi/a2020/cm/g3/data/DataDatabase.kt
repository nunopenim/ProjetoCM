package pt.ulusofona.deisi.a2020.cm.g3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.DataDao
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.DataDb

@Database(entities = arrayOf(DataDb::class), version = 1)
abstract class DataDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        private var instance: DataDatabase? = null

        fun getInstance(applicationContext: Context): DataDatabase {
            synchronized(this) {
                if (instance == null) {
                    DataDatabase.instance = Room.databaseBuilder(applicationContext, DataDatabase::class.java, "data_db").build()
                }
                return instance as DataDatabase
            }
        }
    }
}