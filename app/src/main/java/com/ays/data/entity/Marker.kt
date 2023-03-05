package com.ays.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ays.data.local.Converters
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "marker_table")
data class Marker(
    @PrimaryKey(autoGenerate = true)
    var markerId: Int? = null,
    val markerName: String?,
    val markerLongitude: Double?,
    val markerLatitude: Double?,
    @TypeConverters(Converters::class)
    val markerCreateAt: Long) : Parcelable