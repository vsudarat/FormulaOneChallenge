package com.agoda.formulaone.rule

import com.agoda.formulaone.model.RacingCar
import org.scalatest.FunSuite

/**
  * Created by sudar on 12/9/2016.
  */
class NearByRuleAssessmentSpec extends FunSuite{

  test("Near by car should be slow down") {
    //Setup data
    val hf = 0.8f
    val maxSpeed = 100.00f
    val acceleration = 10.00f
    var car1_near_car2 = RacingCar(1,maxSpeed,acceleration,0f,hf)
    var car2_near_car1 = RacingCar(2,maxSpeed,acceleration,-10f,hf)
    var car3_notnear_any = RacingCar(3,maxSpeed,acceleration,40f,hf)
    var car4_near_car5 = RacingCar(4,maxSpeed,acceleration,100.00f,hf)
    var car5_near_car4 = RacingCar(5,maxSpeed,acceleration,108.00f,hf)
    var racingCars = car1_near_car2 :: car2_near_car1 :: car3_notnear_any:: car4_near_car5:: car5_near_car4:: Nil
    racingCars.foreach(c => c.currentSpeed = 100.00f)

    //Action
    var assessment = new NearByRuleAssessment(10)
    assessment.assess(racingCars)

    //Assert
    assert(racingCars.size == 5,"Assessment should not change list size")
    val carsThatShouldBeSlowDown = racingCars.filter(r => r.carNumber==1 || r.carNumber==2||r.carNumber==4||r.carNumber==5 )
    assert(carsThatShouldBeSlowDown.size == 4, "Expect 4 cars to be slow down")
    carsThatShouldBeSlowDown.foreach( s=> assert(s.getCurrentSpeed.equals(80f), "Expect Car#"+s.carNumber+" should be slow down"))

  }

}
