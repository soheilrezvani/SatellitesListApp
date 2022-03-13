package com.srn.satellitelist.data

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by SoheilR .
 */
@Singleton
class SatellitePositionRepository @Inject constructor(private val satellitePositionDao: SatellitePositionDao) {

    fun getSatellitePosition() = satellitePositionDao.getSatellitePosition()

    fun getSatellitePosition(satId: String) = satellitePositionDao.getSatellitePositionById()

}