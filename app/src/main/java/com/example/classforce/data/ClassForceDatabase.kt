package com.example.classforce.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.classforce.data.dao.CursoDao
import com.example.classforce.data.dao.UserDao
import com.example.classforce.data.entities.CursoEntity
import com.example.classforce.data.entities.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserEntity::class, CursoEntity::class], version = 2, exportSchema = true)
abstract class ClassForceDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun cursoDao(): CursoDao

    companion object {
        private const val DATABASE_NAME = "ClassForceDatabase"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE cursos_temp AS SELECT courseid, nome, local, dataInicio, dataFim, preco, duracao, edicao, descricao FROM cursos")
                database.execSQL("DROP TABLE cursos")
                database.execSQL("ALTER TABLE cursos_temp RENAME TO cursos")
            }
        }

        fun getInstance(context: Context): ClassForceDatabase {
            return Room.databaseBuilder(context, ClassForceDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }

    private class RoomDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val database = ClassForceDatabase.getInstance(context)
            val initializer = DatabaseInitializer(database, context)
            CoroutineScope(Dispatchers.IO).launch {
                initializer.initializeCursosData()
                initializer.initializeUsersData()
            }
        }
    }
}

