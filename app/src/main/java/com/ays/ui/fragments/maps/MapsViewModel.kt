package com.ays.ui.fragments.maps

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ays.repository.MarkerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val searchRepository: MarkerRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun getMarker() = searchRepository.getMarker()

}