import styles from '@components/formButton/formButton.module.scss'
import { useEffect, useState } from 'react'

const FormButton = ({ label, handler }: { label: string; handler: any }) => {
  return (
    <button className={styles.button} onClick={handler}>
      <p className={styles.buttonText}>{label}</p>
    </button>
  )
}

export default FormButton
