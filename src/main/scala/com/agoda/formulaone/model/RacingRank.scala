package com.agoda.formulaone.model

/**
  * Created by sudar on 12/8/2016.
  */
case class RacingRank (val carNumber :Int, val finalSpeed:Float, val finishTime:Float) {
  override def toString: String = { "Car Number: "+ carNumber + " ,Final Speed:" + finalSpeed +" ,Total time :"+ finishTime}
}
