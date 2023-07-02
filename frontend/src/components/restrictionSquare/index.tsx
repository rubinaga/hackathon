import Image from 'next/image'
import styles from './restrictionSquare.module.scss'
import { useState } from 'react'
import { useSelector } from 'react-redux'

interface IDietaryRestriction {
  imageSource?: any
  handleSelection: any
  text: string
  hasImage: boolean
  extraString?: number
}

const DietaryRestriction = ({
  imageSource,
  handleSelection,
  text,
  hasImage,
  extraString,
}: IDietaryRestriction) => {
  const [chosen, setChosen] = useState<boolean>(false)

  return (
    <div
      className={chosen ? styles.chosen_food_box : styles.food_box}
      onClick={() => {
        handleSelection(text)
        setChosen(e => !e)
      }}>
      {extraString && <p>{'' + extraString}</p>}
      {hasImage && (
        <Image src={imageSource} alt="icon" style={{ zIndex: '1' }} />
      )}
      <p className={styles.text}>{text}</p>
    </div>
  )
}

export default DietaryRestriction
