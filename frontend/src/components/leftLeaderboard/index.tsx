import RankingUsers from '@components/rankingUsers'
import styles from './leftLeaderboard.module.scss'
import profileicon from '../../assets/svg/profile-icon.svg'
import RankLogo from '../../assets/svg/rankLogo.svg'
import Image from 'next/image'

const LeftLeaderboard = () => {
  return (
    <div className={styles.page_style}>
      <div className={styles.logo_style}>
        <Image src={RankLogo} alt="rank-logo" />
      </div>
      <div className={styles.leaderboard_style}>
        <RankingUsers
          imgsrc={profileicon}
          index={1}
          username={'user1'}
          points={'1200'}
        />
        <RankingUsers
          imgsrc={profileicon}
          index={2}
          username={'user2'}
          points={'1400'}
        />{' '}
        <RankingUsers
          imgsrc={profileicon}
          index={3}
          username={'user3'}
          points={'1700'}
        />{' '}
        <RankingUsers
          imgsrc={profileicon}
          index={4}
          username={'user4'}
          points={'2200'}
        />{' '}
        <RankingUsers
          imgsrc={profileicon}
          index={5}
          username={'user5'}
          points={'2300'}
        />{' '}
        <RankingUsers
          imgsrc={profileicon}
          index={6}
          username={'user6'}
          points={'5000'}
        />{' '}
      </div>
    </div>
  )
}

export default LeftLeaderboard
