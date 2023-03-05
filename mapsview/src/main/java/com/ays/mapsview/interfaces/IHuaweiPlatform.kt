package com.ays.mapsview.interfaces

import android.app.Activity
import android.os.Bundle
import com.ays.mapsview.model.Place

interface IHuaweiPlatform : IPlatform {
    override fun setMap(
        mapViewBundle: Bundle?,
        activity: Activity,
        callback: OnReceiveMapCallback<Any>
    )

    override fun addMarkers(coordinates: MutableList<Place>)
}