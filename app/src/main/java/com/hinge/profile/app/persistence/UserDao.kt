package com.hinge.profile.app.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hinge.profile.app.persistence.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(posters: List<UserEntity>)

    @Query("SELECT * FROM USERS WHERE id = :id_")
    suspend fun getUser(id_: Long): List<UserEntity>?

    @Query("SELECT * FROM USERS")
    suspend fun getUsers(): List<UserEntity>
}