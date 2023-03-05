package com.ays.ui.fragments.maps

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ays.R
import com.ays.databinding.FragmentMapsBinding
import com.ays.mapsview.MapsView
import com.ays.mapsview.interfaces.OnReceiveMapCallback
import com.ays.mapsview.model.Place
import com.ays.mapsview.platform.GooglePlatform
import com.ays.mapsview.platform.HuaweiPlatform
import com.ays.mapsview.util.PlatformType
import com.ays.mapsview.util.getPlatformType
import dagger.hilt.android.AndroidEntryPoint

private const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

@AndroidEntryPoint
class MapsFragment : Fragment(R.layout.fragment_maps), OnReceiveMapCallback<Any> {

    private val viewModel: MapsViewModel by viewModels()
    private lateinit var binding: FragmentMapsBinding
    private lateinit var platformType: PlatformType
    private lateinit var map: Any
    private var mapsView: MapsView? = null
    private var coordinates = mutableListOf<Place>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        val initPlatform = sharedPref?.getString("init_platform", null);

        if (initPlatform == null) {
            platformType = activity?.let { getPlatformType(it.application) }!!
        } else {
            when (initPlatform) {
                "HMS" -> {
                    platformType = PlatformType.HMS
                }
                "GMS" -> {
                    platformType = PlatformType.GMS
                }
            }
        }
        mapsView = when (platformType) {
            PlatformType.GMS -> {
                activity?.let {
                    MapsView(
                        GooglePlatform(
                            binding.mapsview
                        ), mapViewBundle, it, this
                    )
                }
            }
            PlatformType.HMS -> {
                activity?.let {
                    MapsView(
                        HuaweiPlatform(
                            binding.mapsview
                        ), mapViewBundle, it, this
                    )
                }
            }
            else -> null
        }

        setCoordinates()

        /*
         // Button Event
         binding.hmsChangeButton.setOnClickListener {
            if(isHmsAvailable(this)){
                sharedPref.edit().putString("init_platform", "HMS").apply()
                val i = Intent(this, MapsActivity::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(i)
            }else{
                Toast.makeText(applicationContext, "HMS Core Service Not Found", Toast.LENGTH_SHORT).show()
            }
        }
        binding.gmsChangeButton.setOnClickListener {
            sharedPref.edit().putString("init_platform", "GMS").apply()
            val i = Intent(this, MapsActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
         */

        return binding.root
    }


    private fun setCoordinates() {
        coordinates.add(
            Place(
                41.028985, 29.117591, "Huawei R&D"
            )
        )
    }

    override fun onMap(map: Any) {
        this@MapsFragment.map = map
        mapsView?.addMarkers(coordinates)
    }

}