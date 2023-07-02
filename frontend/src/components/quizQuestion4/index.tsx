import React, { useState } from 'react'
import styles from './quizQuestion4.module.scss'
import NextButton from '@components/nextButton'
import BackButton from '@components/backButton'
import DietaryRestriction from '@components/restrictionSquare'

const QuizQuestion4 = () => {
  const [selectedGoals, setSelectedGoals] = useState<string[]>([])

  const handleSelection = (goal: string) => {
    if (selectedGoals.indexOf(goal) > -1) {
      setSelectedGoals(selectedGoals.filter(e => e !== goal))
    } else {
      setSelectedGoals([...selectedGoals, goal])
    }
  }

  return (
    <div>
      <div className={styles.wrapper}>
        <div className={styles.display_text}>
          Select your Health Goals, <br />
          what do you want to improve?
        </div>
        <div className={styles.conatiner}>
          <DietaryRestriction
            hasImage={false}
            text="Weight Loss"
            handleSelection={handleSelection}
          />
          {/* Weight Loss */}

          {/* Muscle Gain */}
          <DietaryRestriction
            hasImage={false}
            text="Muscle Gain"
            handleSelection={handleSelection}
          />

          <DietaryRestriction
            hasImage={false}
            text="Digestion"
            handleSelection={handleSelection}
          />

          {/* Digestion */}

          {/* Heart Health */}
          <DietaryRestriction
            hasImage={false}
            text="Heart Health"
            handleSelection={handleSelection}
          />

          {/* Mental Health*/}
          <DietaryRestriction
            hasImage={false}
            text="Mental Health"
            handleSelection={handleSelection}
          />

          {/* Sleep */}
          <DietaryRestriction
            hasImage={false}
            text="Sleep"
            handleSelection={handleSelection}
          />
        </div>
        <NextButton type="NEXT" data={selectedGoals} />
        <BackButton />
      </div>
    </div>
  )
}

export default QuizQuestion4
