import React, { useState } from 'react'
import styles from './quizQuestion6.module.scss'
import NextButton from '@components/nextButton'
import BackButton from '@components/backButton'
import DietaryRestriction from '@components/restrictionSquare'

const quizQuestion6 = () => {
  const [selectedEmission, setSelectedEmission] = useState<number>()

  const handleSelection = (emission: number) => {
    setSelectedEmission(emission)
  }

  return (
    <div>
      <div className={styles.wrapper}>
        <div className={styles.display_text}>
          How much you are willing to reduce carbon footprint and to support
          sustainable food sources?
        </div>
        <div className={styles.conatiner}>
          {/* 1 */}
          <DietaryRestriction
            hasImage={false}
            text="I don't care"
            handleSelection={handleSelection}
            extraString={1}
          />

          <DietaryRestriction
            hasImage={false}
            text="I slightly care"
            handleSelection={handleSelection}
            extraString={2}
          />
          {/* 3 */}
          <DietaryRestriction
            hasImage={false}
            text="Neutral"
            handleSelection={handleSelection}
            extraString={3}
          />

          {/* 4 */}
          <DietaryRestriction
            hasImage={false}
            text="I care"
            handleSelection={handleSelection}
            extraString={4}
          />
          {/* 5 */}
          <DietaryRestriction
            hasImage={false}
            text="I care a lot"
            handleSelection={handleSelection}
            extraString={5}
          />
        </div>
        <NextButton type="Finish" data={selectedEmission} />
        <BackButton />
      </div>
      {/* Vegetarian NoBeefIcon */}
    </div>
  )
}

export default quizQuestion6
