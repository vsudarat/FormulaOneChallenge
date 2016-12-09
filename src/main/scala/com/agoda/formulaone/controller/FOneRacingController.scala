package com.agoda.formulaone.controller
import com.agoda.formulaone.model.{AgodaCarFactory, ICarFactory, IRacingCar, RacingRank}
import com.agoda.formulaone.rule.IRacingRuleAssessment

/**
  * Created by sudar on 12/8/2016.
  */
class FOneRacingController(val racingCarFactory: ICarFactory, val assessTimeFrame: Int, val assessmentSeq: List[IRacingRuleAssessment]) {
  require(assessTimeFrame > 0, "The assessTimeFrame must be greater than 0")

  def racing(numberOfCar:Int,trackLength: Float) : List[RacingRank] = {
    require(numberOfCar > 0, "The numberOfCar must be greater than 0")
    require(trackLength > 0, "The trackLength must be Non-negative")

    var racingResult: List[RacingRank] = List()
    var currentRacingCars = InitRacingCar(numberOfCar)

    var racingDuration = 0
    while(currentRacingCars.nonEmpty){

      racingDuration += assessTimeFrame

      //calculate next position
      var nextPositionCars = {
        //TODO refactor to parallel processing
        currentRacingCars.foreach(car => {
          car.move(assessTimeFrame)
        })
        currentRacingCars
      }

      //check for finished car
      currentRacingCars = nextPositionCars
      currentRacingCars.filter(car => car.getCurrentDistance >= trackLength)
        .foreach(f => {
          val rank = RacingRank(f.getCarNumber, f.getCurrentSpeed, f.getFinishTime)
          racingResult = rank :: racingResult
        })
      currentRacingCars = currentRacingCars.filter(car => car.getCurrentDistance < trackLength)

      //apply racing rule
      assessmentSeq.foreach(a => {
        a.assess(currentRacingCars)
      })

      printRacingState(currentRacingCars, racingDuration)
    }

    racingResult
  }

  private def InitRacingCar(numberOfCar: Int): List[IRacingCar] = {
    var racingCars: List[IRacingCar] = List()
    var dependPosition = 0f
    for (i <- 1 to numberOfCar) {
      var racingCar = racingCarFactory.getRacingCar(i, dependPosition)
      racingCars =  racingCar:: racingCars
      dependPosition = racingCar.getCurrentPosition
    }
    printRacingState(racingCars, 0)
    racingCars
  }

  private def printRacingState(racingCars: List[IRacingCar], currentTime : Int){
    println("=== Racing result at " +currentTime+ " second." )
    var racingCarsRank = racingCars.sortBy{car => (-car.getCurrentDistance, car.getRacingTime)}
    racingCarsRank.foreach( c => println(c.toString))
  }

}
