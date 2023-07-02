import React, { useState } from 'react'
import styles from '@components/allergyComponent/allergyComponent.module.scss'

const AllergyComponent = ({
  ingredientName,
  handleChosen,
}: {
  ingredientName: string
  handleChosen: any
}) => {
  //className={styles.text_position}
  const [chosen, setChosen] = useState<boolean>(false)

  return (
    <div>
      <div className={styles.completeWrapper}>
        <div
          onClick={() => {
            setChosen(e => !e)
            handleChosen(ingredientName)
          }}
          className={chosen ? styles.chosenWrapper : styles.wrapper}>
          <p className={styles.text}>{ingredientName.toUpperCase()}</p>
        </div>
      </div>
    </div>
  )
}

export default AllergyComponent
