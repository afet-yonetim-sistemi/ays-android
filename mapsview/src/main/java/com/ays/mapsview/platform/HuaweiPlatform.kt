package com.ays.mapsview.platform

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.ays.mapsview.interfaces.IHuaweiPlatform
import com.ays.mapsview.interfaces.OnReceiveMapCallback
import com.ays.mapsview.model.Place
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.MarkerOptions

class HuaweiPlatform(private val mapsView: com.ays.mapsview.MapsView) : IHuaweiPlatform,
    OnMapReadyCallback {

    private lateinit var onReceiveMapCallback: OnReceiveMapCallback<Any>
    private lateinit var huaweiMap: HuaweiMap

    override fun setMap(
        mapViewBundle: Bundle?,
        activity: Activity,
        callback: OnReceiveMapCallback<Any>
    ) {
        onReceiveMapCallback = callback
        val mapView = MapView(activity.baseContext)
        mapView.apply {
            onCreate(mapViewBundle)
            getMapAsync(this@HuaweiPlatform)
        }
        mapsView.addView(mapView as View?)
        mapView.onResume()
    }

    override fun addMarkers(coordinates: MutableList<Place>) {
        for (place in coordinates) {
            val coordinate = LatLng(place.latitude, place.longitude)
            huaweiMap.addMarker(MarkerOptions().position(coordinate).title(place.title))
        }
        if (coordinates.isNotEmpty()) {
            val firstCoordinate = LatLng(coordinates[0].latitude, coordinates[0].longitude)
            huaweiMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstCoordinate, 13f))
        }

    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        this.huaweiMap = huaweiMap
        onReceiveMapCallback.onMap(huaweiMap)
    }

}