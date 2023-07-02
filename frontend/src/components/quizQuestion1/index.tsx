import React from 'react'
import styles from '@components/quizQuestion1/quizQuestion1.module.scss'
import Button from '@components/button'

const QuizQuestion1 = () => {
  return (
    <div>
      <div className={styles.text_position}>
        <div className={styles.display_text}>Select your gender</div>
      </div>
      <div className={styles.bothButtons}>
        <Button gender="women" />
        <Button gender="men" />
      </div>
    </div>
  )
}

export default QuizQuestion1
