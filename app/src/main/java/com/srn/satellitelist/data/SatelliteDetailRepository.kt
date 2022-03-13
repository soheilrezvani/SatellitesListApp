package com.srn.satellitelist.data

import javax.inject.Inject

/**
 * Created by SoheilR .
 */
class SatelliteDetailRepository @Inject constructor(private val satelliteDetailDao: SatelliteDetailDao) {

    fun getSatelliteDetail() = satelliteDetailDao.getSatelliteDetail()

    fun getSatelliteDetail(satId: Int) = satelliteDetailDao.getSatelliteDetailById(satId)

}