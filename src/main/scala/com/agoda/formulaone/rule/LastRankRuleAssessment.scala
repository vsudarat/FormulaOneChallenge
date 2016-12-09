package com.agoda.formulaone.rule

import com.agoda.formulaone.model.IRacingCar


/**
  * Created by sudar on 12/8/2016.
  */
class LastRankRuleAssessment extends IRacingRuleAssessment{
  def assess(racingCars: List[IRacingCar]): List[IRacingCar] = {
     if(racingCars.size > 1) {
       //Find last one on the race (last position)
       var lastRank = racingCars.minBy(p => p.getCurrentPosition)
       var lastRankCars = racingCars.filter(c => c.getCurrentPosition == lastRank.getCurrentPosition)
       lastRankCars.foreach(c => c.speedUp())
     }
     racingCars
   }

}
