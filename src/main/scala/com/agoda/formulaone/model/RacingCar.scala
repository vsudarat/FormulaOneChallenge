package com.agoda.formulaone.model

/**
  * Created by sudar on 12/8/2016.
  */
case class RacingCar(val p_carNumber: Int, val p_maxSpeed: Float, val p_acceleration: Float, p_startPosition:Float ,p_handlingFactor : Float)
  extends IRacingCar
{
  val carNumber: Int = p_carNumber
  val maxSpeed: Float = p_maxSpeed
  val acceleration: Float = p_acceleration
  val hf :Float = p_handlingFactor
  var currentPosition: Float = p_startPosition
  var timeConsumeInSec = 0
  var currentSpeed = 0f
  var currentDistant = 0f
  private var nitroAvailable = true


  def move(timePeriod: Int) {
    //distance = v1*t + (a*t*t)/2
    var timeToMaxSpeed = (maxSpeed - currentSpeed) / acceleration
    var movingDistance = 0f
    if (timeToMaxSpeed > timePeriod) {
      movingDistance = currentSpeed * timePeriod + acceleration * timePeriod * timePeriod / 2f
      currentSpeed = currentSpeed + acceleration * timePeriod
    }else{
      movingDistance = currentSpeed * timeToMaxSpeed + acceleration * timeToMaxSpeed * timeToMaxSpeed / 2f
      movingDistance = movingDistance + maxSpeed * (timePeriod - timeToMaxSpeed)
      currentSpeed = maxSpeed
    }
    currentPosition = currentPosition + movingDistance
    currentDistant = currentDistant + movingDistance
    timeConsumeInSec +=timePeriod
  }

  def slowDown() = {
    println("** Car#"+ carNumber + " is slow down with Handling factor")
    currentSpeed = currentSpeed * hf
  }


  def speedUp() = {
    if (nitroAvailable) {
      nitroAvailable = false
      println("** Car#"+ carNumber + " is using Nitro")
      currentSpeed = Math.min(maxSpeed, currentSpeed * 2)
    }
  }

  override def getCurrentDistance = { currentDistant }

  override def getCurrentPosition = { currentPosition}

  override def getRacingTime = { timeConsumeInSec }

  override def getCarNumber = {carNumber}

  override def getCurrentSpeed = {currentSpeed}

  override def getFinishTime = {timeConsumeInSec}

  override def toString: String = {
    "= Car Number: "+ carNumber + " ,Speed:" + currentSpeed +" ,CurrentPosition :"+ currentPosition+" ,CurrentDistance :"+ currentDistant+" ,timeSpend :"+ timeConsumeInSec
  }
}
