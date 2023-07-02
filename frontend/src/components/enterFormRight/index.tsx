import styles from '@components/enterFormRight/enterFormRight.module.scss'
import Salad from '../../assets/svg/Salad.svg'
import Image from 'next/image'

const LoginRight = () => {
  return (
    <div className={styles.fullPage}>
      <div className={styles.elementInside}>
        <Image src={Salad} alt="salad" height={500} width={500} />
      </div>
    </div>
  )
}

export default LoginRight
