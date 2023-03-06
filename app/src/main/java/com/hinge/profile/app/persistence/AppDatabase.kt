package com.hinge.profile.app.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hinge.profile.app.persistence.model.UserEntity
import com.hinge.profile.app.persistence.util.HobbiesConverter
import com.hinge.profile.app.persistence.util.UserConverter

@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
@TypeConverters(HobbiesConverter::class, UserConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}