import React from 'react'
import styles from './rankingUseers.module.scss'
import Image from 'next/image'

const RankingUsers = ({ username, points, imgsrc, index }) => {
  return (
    <div className={styles.rankingUsers}>
      <div className={styles.image_style}>
        <Image src={imgsrc} alt="img" />
      </div>
      <div className={styles.users_info}>
        <p className={styles.para_info}>{username}</p>
        <p className={styles.para_info}>{points}</p>
      </div>
      <p className={styles.index_info}>{index}</p>
    </div>
  )
}

export default RankingUsers
