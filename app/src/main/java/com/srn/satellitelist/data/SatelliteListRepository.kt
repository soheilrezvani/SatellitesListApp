package com.srn.satellitelist.data

import javax.inject.Inject

/**
 * Created by SoheilR .
 */
class SatelliteListRepository @Inject constructor(private val satelliteDao: SatelliteDao) {

    fun getSatelliteList() = satelliteDao.getSatellite()

    fun getSatelliteList(satId: Int) = satelliteDao.getSatelliteById(satId)

    fun getSatelliteListByName(satName: String) = satelliteDao.getSatelliteByName(satName)

    fun getSatelliteStatus(status: String) =
        satelliteDao.getSatelliteStatus(status)

}
