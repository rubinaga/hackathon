import EnterForm from '@components/enterForm'
import EnterFormRight from '@components/enterFormRight'
import styles from '@components/completeForm/completeForm.module.scss'

const CompleteForm = ({ type }: { type: string }) => {
  return (
    <div className={styles.fullPage}>
      <EnterForm type={type} />
      <EnterFormRight />
    </div>
  )
}
export default CompleteForm
