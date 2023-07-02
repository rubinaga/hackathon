import Header from '@components/header'
import QuizQuestion1 from '@components/quizQuestion1'
import QuizQuestion2 from '@components/quizQuestion2'
import QuizQuestion3 from '@components/quizQuestion3'
import styles from '@components/completeQuiz/completeQuiz.module.scss'
import { useDispatch, useSelector } from 'react-redux'
import {
  getQuizIndex,
  prevQuizIndex,
  resetQuizIndex,
} from '@utils/store/reducers/quiz'
import { useEffect } from 'react'
import QuizQuestion4 from '@components/quizQuestion4'
import QuizQuestion5 from '@components/quizQuestion5'
import QuizQuestion6 from '@components/quizQuestion6'

const CompleteQuiz = () => {
  const quizIndex = useSelector(getQuizIndex)
  const dispatch = useDispatch()

  useEffect(() => {
    console.log(quizIndex)
    dispatch(resetQuizIndex({ decrement: 1 }))
  }, [])

  return (
    <>
      <Header />
      <div className={styles.pageWrapper}>
        {/* {quizIndex == 0 && <QuizQuestion1 />} */}
        {quizIndex == 0 && <QuizQuestion2 />}
        {quizIndex == 1 && <QuizQuestion3 />}
        {quizIndex == 2 && <QuizQuestion4 />}
        {quizIndex == 3 && <QuizQuestion5 />}
        {quizIndex == 4 && <QuizQuestion6 />}
        {/* <QuizQuestion2 /> */}
      </div>
    </>
  )
}

export default CompleteQuiz
