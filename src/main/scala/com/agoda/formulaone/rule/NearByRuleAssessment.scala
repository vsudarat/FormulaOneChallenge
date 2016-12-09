package com.agoda.formulaone.rule

import com.agoda.formulaone.model.IRacingCar


/**
  * Created by sudar on 12/8/2016.
  */
class NearByRuleAssessment(val handlingDistance: Int) extends IRacingRuleAssessment{

  def assess(curentRacingCars: List[IRacingCar]): List[IRacingCar] = {

    if(curentRacingCars.size > 2) {

        var sortedRacingCars = curentRacingCars.sortBy{car => car.getCurrentPosition}
        //check fist car
        if (distanceLessThanTenMeters(sortedRacingCars.head, sortedRacingCars(1))) {
          sortedRacingCars.head.slowDown()
          if(curentRacingCars.size == 2){
            curentRacingCars(1).slowDown()
            return curentRacingCars
          }
        }
        for (i <- 1 to sortedRacingCars.size - 2) {
          if (distanceLessThanTenMeters(sortedRacingCars(i), sortedRacingCars(i - 1)) || distanceLessThanTenMeters(sortedRacingCars(i), sortedRacingCars(i + 1))) {
            sortedRacingCars(i).slowDown()
          }
        }
        //check last car
        if (distanceLessThanTenMeters(sortedRacingCars.last, sortedRacingCars(sortedRacingCars.size - 2))) {
          sortedRacingCars.last.slowDown()
        }
      return sortedRacingCars
    }
    curentRacingCars
  }

  private def distanceLessThanTenMeters(firstCar: IRacingCar, secondCar: IRacingCar): Boolean = {
     Math.abs(firstCar.getCurrentPosition - secondCar.getCurrentPosition) <= handlingDistance
  }
}
