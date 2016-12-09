package com.agoda.formulaone.model

/**
  * Created by sudar on 12/9/2016.
  */
trait ICarFactory {
  def getRacingCar(carNumber:Int, dependPosition: Float) : IRacingCar
}
