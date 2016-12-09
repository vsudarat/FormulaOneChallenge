package com.agoda.formulaone.model

/**
  * Created by sudar on 12/9/2016.
  */
object AgodaCarFactory extends ICarFactory{

  def getRacingCar(carNumber:Int, dependPosition: Float) : IRacingCar = {
    RacingCar(carNumber, kmPerHourToMeterPerSec(150f + 10 * carNumber), 2 * carNumber, getStartPosition(carNumber, dependPosition),0.8f)
  }
  private def kmPerHourToMeterPerSec(kmPerHour: Float): Float = kmPerHour * 1000f / 3600f

  //The start line for (i + 1)th car is 200 * i meters behind the ith car
  private def getStartPosition(carNumber :Int, previousPosition: Float): Float = (-200 * (carNumber - 1)) + previousPosition

}
