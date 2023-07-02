import React from 'react'
import Header from '@components/header'
import RankingUsers from '@components/rankingUsers'
import styles from './leaderboard.module.scss'
import LeaderboardRight from '@components/leaderboardRight'
import LeftLeaderboard from '@components/leftLeaderboard'

const Leaderboard = () => {
  return (
    <>
      <Header />
      <div className={styles.fullPage}>
        <LeftLeaderboard />
        <LeaderboardRight />
      </div>
    </>
  )
}

export default Leaderboard
