import React from 'react'
import styles from './nextButton.module.scss'
import { useDispatch, useSelector } from 'react-redux'
import {
  getQuizIndex,
  nextQuizIndex,
  setAllergies,
  setCarbon,
  setCuisine,
  setDietaryPreferences,
  setGoals,
} from '@utils/store/reducers/quiz'
import { useRouter } from 'next/router'
import { Paths } from '@utils/enums'
import next from 'next/types'

const NextButton = ({ type = 'Next', data }: { type?: string; data: any }) => {
  const dispatch = useDispatch()

  const quizIndex = useSelector(getQuizIndex)
  const router = useRouter()

  const finishHandler = () => {
    router.push(Paths.HomePage)
  }

  const nextHandler = () => {
    switch (quizIndex) {
      case 0:
        dispatch(setDietaryPreferences({ dietaryPreferences: data }))
        break
      case 1:
        dispatch(setAllergies({ allergies: data }))
        break
      case 2:
        dispatch(setGoals({ goals: data }))
        break
      case 3:
        dispatch(setCuisine({ cuisine: data }))
        break
      case 4:
        dispatch(setCarbon({ carbon: data }))
        break
    }

    dispatch(nextQuizIndex({ increment: 1 }))
  }

  let func: any

  if (type.toUpperCase() === 'NEXT') {
    func = nextHandler
  } else {
    func = finishHandler
  }

  return (
    <button
      onClick={func}
      className={
        type.toUpperCase() === 'NEXT'
          ? styles.button_green
          : styles.button_orange
      }>
      <div className={styles.button_text_position}>{type}</div>
    </button>
  )
}

export default NextButton
