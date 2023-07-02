import React from 'react'
import styles from './backButton.module.scss'
import { useDispatch } from 'react-redux'
import { prevQuizIndex } from '@utils/store/reducers/quiz'

const BackButton = () => {
  const dispatch = useDispatch()

  return (
    <button
      onClick={() => {
        dispatch(prevQuizIndex({ decrement: 1 }))
      }}
      className={styles.backButton}>
      {' '}
      {'< '}Back
    </button>
  )
}

export default BackButton
