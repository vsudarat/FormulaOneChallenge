package com.agoda.formulaone.controller

import com.agoda.formulaone.model.{AgodaCarFactory, ICarFactory, RacingCar, RacingRank}
import org.scalatest.FunSuite
import org.scalamock.scalatest.MockFactory
/**
  * Created by sudar on 12/9/2016.
  */
class FOneRacingControllerSpec extends FunSuite with MockFactory{

  test("Racing result should adjust speed") {
    //Setup data
    val numberOfCar = 3
    val trackingLength = 180
    val assessmentSeq = List()
    val assessTimeFrame = 2

    val hf = 0.8f
    val maxSpeed = 100.00f
    val acceleration = 10.00f

    val mockCarFactory = stub[ICarFactory]
    (mockCarFactory.getRacingCar _ ) when(1,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))
    (mockCarFactory.getRacingCar _ ) when(2,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))
    (mockCarFactory.getRacingCar _ ) when(3,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))

    //Action
    var controller = new FOneRacingController(mockCarFactory, assessTimeFrame, assessmentSeq)
    var rankResult = controller.racing(numberOfCar, trackingLength)

    //Assert
    assert(rankResult.size == 3,"Racing result should equals to 3")
    rankResult.foreach(r => assert(r.finishTime == 6 ) )
    rankResult.foreach(r => assert(r.finalSpeed == 60 ) )
  }

  test("Final speed should not over max speed") {
    //Setup data
    val numberOfCar = 3
    val trackingLength = 180
    val assessmentSeq = List()
    val assessTimeFrame = 2

    val hf = 0.8f
    val maxSpeed = 50.00f
    val acceleration = 10.00f

    val mockCarFactory = stub[ICarFactory]
    (mockCarFactory.getRacingCar _ ) when(1,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))
    (mockCarFactory.getRacingCar _ ) when(2,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))
    (mockCarFactory.getRacingCar _ ) when(3,*) returns(RacingCar(1, maxSpeed, acceleration, 0, hf))

    //Action
    var controller = new FOneRacingController(mockCarFactory, assessTimeFrame, assessmentSeq)
    var rankResult = controller.racing(numberOfCar, trackingLength)

    //Assert
    rankResult.foreach(r => assert(r.finalSpeed == 50 ) )
  }

}
