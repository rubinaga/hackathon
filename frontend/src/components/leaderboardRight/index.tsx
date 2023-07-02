import styles from '@components/leaderboardRight/leaderboardRight.module.scss'
import Crown from '../../assets/svg/crown.svg'
import Image from 'next/image'

const LeaderboardRight = () => {
  return (
    <div className={styles.fullPage}>
      <div className={styles.elementInside}>
        <Image src={Crown} alt="crown" height={500} width={500} />
      </div>
    </div>
  )
}

export default LeaderboardRight
