package com.agoda.formulaone

import com.agoda.formulaone.controller.FOneRacingController
import com.agoda.formulaone.model.AgodaCarFactory
import com.agoda.formulaone.rule.{LastRankRuleAssessment, NearByRuleAssessment}

/**
  * Created by sudar on 12/8/2016.
  */
object RacingMain extends App{
  val usage = """
    Usage: sbt "run-main com.agoda.formulaone.RacingMain [numberOfCar] [trackingLength]"
          numberOfCar is number of racing car (default to 5 cars)
          trackingLength is total racing distance (default to 200 meters)
    """

  if(args.isEmpty) {
    println(usage)
  }

  val defaultCarNumber = 5
  val defaultTrackLength = 200
  val numberOfCar:Int = {if(args.isEmpty) defaultCarNumber else args(0).toInt }
  val trackingLength = {if(args.size == 2) args(1).toFloat else defaultTrackLength }

  val assessments = List(new NearByRuleAssessment(10), new LastRankRuleAssessment())

  def run(): Unit = {

    println("========================== Start Racing =============================")
    println("[param] Number of cars: "+ numberOfCar)
    println("[param] Track length(meter): "+ trackingLength + "\n")

    var racing = new FOneRacingController(AgodaCarFactory,2, assessments)
    var racingRannk = racing.racing(numberOfCar, trackingLength)
    println("========================== Racing Result ============================")
    racingRannk.sortBy{car => (car.finishTime, -car.finalSpeed)}.foreach(r => println(r.toString))
  }

  run()
}
