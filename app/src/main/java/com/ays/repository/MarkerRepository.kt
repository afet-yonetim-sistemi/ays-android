package com.ays.repository

import com.ays.data.local.MarkerDao
import com.ays.data.remote.AppApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarkerRepository @Inject constructor(
    private val appApi: AppApi,
    private val searchDao: MarkerDao
) {

    fun getMarker() = searchDao.getMarker()

}