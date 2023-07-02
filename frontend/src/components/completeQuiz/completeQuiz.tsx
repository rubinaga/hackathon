import Header from '@components/header'
import QuizQuestion1 from '@components/quizQuestion1'
import QuizQuestion2 from '@components/quizQuestion2'
import QuizQuestion3 from '@components/quizQuestion3'
import styles from '@components/completeQuiz/completeQuiz.module.scss'

const CompleteQuiz = () => {
  return (
    <>
      <Header />
      <div className={styles.pageWrapper}>
        {/* <QuizQuestion1 /> */}
        {/* <QuizQuestion2 /> */}
        <QuizQuestion2 />
      </div>
    </>
  )
}

export default CompleteQuiz
