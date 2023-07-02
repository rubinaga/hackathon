import React, { useState } from 'react'
import styles from '@components/quizQuestion2/quizQuestion2.module.scss'
import FishIcon from '../../assets/svg/fish.svg'
import Eggs from '../../assets/svg/eggs.svg'
import BeefIcon from '../../assets/svg/beef.svg'
import Vegan from '../../assets/svg/vegan.svg'
import Vegetarian from '../../assets/svg/vegetarian.svg'
import Cheese from '../../assets/svg/cheese.svg'
import Image from 'next/image'
import NextButton from '@components/nextButton'
import BackButton from '@components/backButton'
import DietaryRestriction from '@components/restrictionSquare'

const QuizQuestion2 = () => {
  const [selectedDietaryRestrictions, setSelectedDietaryRestrictions] =
    useState<string[]>([])

  const handleSelection = (diet: string) => {
    if (selectedDietaryRestrictions.indexOf(diet) > -1) {
      setSelectedDietaryRestrictions(
        selectedDietaryRestrictions.filter(e => e !== diet),
      )
    } else {
      setSelectedDietaryRestrictions([...selectedDietaryRestrictions, diet])
    }
  }

  return (
    <div>
      {/* Paleo Fish Figure */}
      <div className={styles.wrapper}>
        <div className={styles.display_text}>
          Do you have any dietary restrictions or preferences?
        </div>
        <div className={styles.conatiner}>
          {/* Vegan */}

          <DietaryRestriction
            hasImage={true}
            text="Vegan"
            imageSource={Vegan}
            handleSelection={handleSelection}
          />

          {/* Paleo */}
          <DietaryRestriction
            hasImage={true}
            text="Paleo"
            imageSource={FishIcon}
            handleSelection={handleSelection}
          />

          {/* Foodmap */}

          <DietaryRestriction
            hasImage={true}
            text="Fodmap"
            imageSource={BeefIcon}
            handleSelection={handleSelection}
          />

          {/* Vegetarian */}
          <DietaryRestriction
            hasImage={true}
            text="Vegetarian"
            imageSource={Vegetarian}
            handleSelection={handleSelection}
          />

          {/* Gluten free */}

          <DietaryRestriction
            hasImage={true}
            text="Gluten Free"
            imageSource={Eggs}
            handleSelection={handleSelection}
          />

          {/* Dairy Free */}
          <DietaryRestriction
            hasImage={true}
            text="Dairy Free"
            imageSource={Vegetarian}
            handleSelection={handleSelection}
          />
        </div>
        <div className={styles.bothButtons}>
          <NextButton data={selectedDietaryRestrictions} />
          {/* <BackButton /> */}
        </div>
      </div>
      {/* Vegetarian NoBeefIcon */}
    </div>
  )
}

export default QuizQuestion2
