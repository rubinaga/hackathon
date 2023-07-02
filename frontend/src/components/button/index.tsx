import styles from '@components/button/button.module.scss'
import { nextQuizIndex } from '@utils/store/reducers/quiz'
import { useDispatch } from 'react-redux'

const Button = ({ gender }) => {
  const dispatch = useDispatch()
  return (
    <button
      onClick={() => {
        dispatch(nextQuizIndex({ increment: 1 }))
      }}
      className={
        gender === 'women' ? styles.button_orange : styles.button_green
      }>
      <div
        className={
          gender === 'women' ? styles.button_text_women : styles.button_text_men
        }>
        {gender === 'women' ? 'Women' : 'Men'}
      </div>
    </button>
  )
}

export default Button
