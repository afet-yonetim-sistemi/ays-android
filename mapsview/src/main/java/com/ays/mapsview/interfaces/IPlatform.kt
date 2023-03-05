package com.ays.mapsview.interfaces

import android.app.Activity
import android.os.Bundle
import com.ays.mapsview.model.Place

interface IPlatform {
    fun setMap(mapViewBundle: Bundle?, activity: Activity, callback: OnReceiveMapCallback<Any>)
    fun addMarkers(coordinates: MutableList<Place>)
}