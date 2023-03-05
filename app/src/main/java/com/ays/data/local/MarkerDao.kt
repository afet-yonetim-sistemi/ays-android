package com.ays.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ays.data.entity.Marker

@Dao
interface MarkerDao {

    @Query("SELECT * FROM marker_table")
    fun getMarker(): LiveData<List<Marker>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: Marker)

    @Update
    suspend fun update(search: Marker)

    @Delete
    suspend fun delete(search: Marker)


}