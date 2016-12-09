package com.agoda.formulaone.rule

import com.agoda.formulaone.model.RacingCar
import org.scalatest.FunSuite

/**
  * Created by sudar on 12/9/2016.
  */
class LastRankRuleAssessmentSpec extends FunSuite{

  test("Last rank car speed up to double") {
    //Setup data
    val hf = 0.8f
    val maxSpeed = 100.00f
    val acceleration = 10.00f
    var car1 = RacingCar(1,maxSpeed,acceleration,0f,hf)
    var car2 = RacingCar(2,maxSpeed,acceleration,100f,hf)
    var car3 = RacingCar(3,maxSpeed,acceleration,200f,hf)
    var racingCars = car1 :: car2 :: car3::  Nil
    racingCars.foreach(c => c.currentSpeed = 10.00f)
    //racingCars.foreach(c => c.currentDistant = 10.00f * c.carNumber)

    //Action
    var assessment = new LastRankRuleAssessment()
    assessment.assess(racingCars)

    //Assert
    assert(racingCars.size == 3,"Assessment should not change list size")
    val lastRankCarsShouldBeSpeedUp = racingCars.filter(r => r.carNumber==1 )
    assert(lastRankCarsShouldBeSpeedUp.size == 1, "Expect 1 cars to be speed up")
    assert(lastRankCarsShouldBeSpeedUp.head.getCurrentSpeed.equals(20f), "Expect speed of car#1 should be up to double")

  }

  test("Last rank car speed up to max speed") {
    //Setup data
    val hf = 0.8f
    val maxSpeed = 100.00f
    val acceleration = 10.00f
    var car1 = RacingCar(1,maxSpeed,acceleration,0f,hf)
    var car2 = RacingCar(2,maxSpeed,acceleration,100f,hf)
    var car3 = RacingCar(3,maxSpeed,acceleration,200f,hf)
    var racingCars = car1 :: car2 :: car3::  Nil
    racingCars.foreach(c => c.currentSpeed = 80.00f)
    //racingCars.foreach(c => c.currentDistant = 10.00f * c.carNumber)

    //Action
    var assessment = new LastRankRuleAssessment()
    assessment.assess(racingCars)

    //Assert
    assert(racingCars.size == 3,"Assessment should not change list size")
    val lastRankCarsShouldBeSpeedUp = racingCars.filter(r => r.carNumber==1 )
    assert(lastRankCarsShouldBeSpeedUp.size == 1, "Expect 1 cars to be speed up")
    assert(lastRankCarsShouldBeSpeedUp.head.getCurrentSpeed.equals(maxSpeed), "Expect speed of car#1 should be up to max speed")

  }
}
