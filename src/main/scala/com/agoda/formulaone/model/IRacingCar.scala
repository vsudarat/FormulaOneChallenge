package com.agoda.formulaone.model

/**
  * Created by sudar on 12/9/2016.
  */
trait IRacingCar {
  def move(timePeriod: Int)
  def slowDown()
  def speedUp()
  def getCurrentPosition: Float
  def getCurrentDistance: Float
  def getRacingTime: Float
  def getCarNumber: Int
  def getCurrentSpeed: Float
  def getFinishTime: Float
}
