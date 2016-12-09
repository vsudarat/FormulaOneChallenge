package com.agoda.formulaone.rule

import com.agoda.formulaone.model.IRacingCar

/**
  * Created by sudar on 12/8/2016.
  */
trait IRacingRuleAssessment {
  def assess(racingCars: List[IRacingCar]): List[IRacingCar]
}
