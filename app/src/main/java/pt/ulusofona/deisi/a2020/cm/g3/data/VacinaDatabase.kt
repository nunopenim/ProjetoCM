package pt.ulusofona.deisi.a2020.cm.g3.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2020.cm.g3.data.dao.VacinaDao
import pt.ulusofona.deisi.a2020.cm.g3.data.entities.VacinaDb

@Database(entities = arrayOf(VacinaDb::class), version = 1)
abstract class VacinaDatabase : RoomDatabase() {
    abstract fun vacinaDao() : VacinaDao

    companion object {
        private var instance: VacinaDatabase? = null

        fun getInstance(applicationContext: Context): VacinaDatabase {
            synchronized(this) {
                if (instance == null) {
                    VacinaDatabase.instance = Room.databaseBuilder(applicationContext, VacinaDatabase::class.java, "vacina_db").build()
                }
                return instance as VacinaDatabase
            }
        }
    }
}