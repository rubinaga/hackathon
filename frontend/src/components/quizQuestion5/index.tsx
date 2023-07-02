import React, { useState } from 'react'
import styles from './quizQuestion5.module.scss'
import NextButton from '@components/nextButton'
import BackButton from '@components/backButton'
import DietaryRestriction from '@components/restrictionSquare'

const quizQuestion5 = () => {
  const [selectedCuisine, setSelectedCuisine] = useState<string[]>([])

  const handleSelection = (cuisine: string) => {
    if (selectedCuisine.indexOf(cuisine) > -1) {
      setSelectedCuisine(selectedCuisine.filter(e => e !== cuisine))
    } else {
      setSelectedCuisine([...selectedCuisine, cuisine])
    }
  }

  return (
    <div>
      <div className={styles.wrapper}>
        <div className={styles.display_text}>
          Preferred cooking styles or cuisines
        </div>
        <div className={styles.conatiner}>
          {/* Italian */}
          <DietaryRestriction
            hasImage={false}
            text="Italian"
            handleSelection={handleSelection}
          />

          {/* Asian */}

          <DietaryRestriction
            hasImage={false}
            text="Asian"
            handleSelection={handleSelection}
          />
          {/* Mexican */}

          <DietaryRestriction
            hasImage={false}
            text="Mexican"
            handleSelection={handleSelection}
          />

          {/* Mediterranean */}
          <DietaryRestriction
            hasImage={false}
            text="Mediterranean"
            handleSelection={handleSelection}
          />
          {/* French */}
          <DietaryRestriction
            hasImage={false}
            text="French"
            handleSelection={handleSelection}
          />
          {/* American */}
          <DietaryRestriction
            hasImage={false}
            text="American"
            handleSelection={handleSelection}
          />
        </div>
        <NextButton type="NEXT" data={selectedCuisine} />
        <BackButton />
      </div>
      {/* Vegetarian NoBeefIcon */}
    </div>
  )
}

export default quizQuestion5
