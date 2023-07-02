import AllergyComponent from '@components/allergyComponent'
import BackButton from '@components/backButton'
import NextButton from '@components/nextButton'
import styles from '@components/quizQuestion3/quizQuestion3.module.scss'
import Search from '@components/search'
import { useEffect, useMemo, useState } from 'react'

const QuizQuestion3 = () => {
  const [ingredientList, setIngredientList] = useState<string[]>([
    'rice',
    'sugar',
    'butter',
    'eggs',
    'garlic',
    'pasta',
    'salt',
    'apple',
    'pineapple',
    'rice',
    'sugar',
    'butter',
    'eggs',
    'garlic',
    'pasta',
    'salt',
    'apple',
    'pineapple',
    'rice',
    'sugar',
    'butter',
    'eggs',
    'garlic',
    'pasta',
    'salt',
    'apple',
    'pineapple',
    'rice',
    'sugar',
    'butter',
    'eggs',
    'garlic',
    'pasta',
    'salt',
    'apple',
    'pineapple',
  ])

  const [search, setSearch] = useState<string>('')

  const [updatedList, setUpdatedList] = useState<string[]>(ingredientList)

  const [chosenList, setChosenList] = useState<string[]>([])

  const filterBySearch = event => {
    // Access input value
    const query = event.target.value
    // Create copy of item list
    // Include all elements which includes the search query
    const updatedList = ingredientList.filter(item => {
      return item.toLowerCase().indexOf(query.toLowerCase()) !== -1
    })
    setUpdatedList(updatedList)
    // Trigger render with updated values
  }

  const handleChosen = (allergy: string) => {
    if (chosenList.indexOf(allergy) > -1) {
      setChosenList(chosenList.filter(e => e !== allergy))
    } else {
      setChosenList([...chosenList, allergy])
    }
  }
  return (
    <div className={styles.pageWrapper}>
      <div className={styles.text}>Do you have any allergies?</div>
      <div className={styles.allergiesWrapper}>
        <input
          className={styles.searchInput}
          onChange={filterBySearch}
          placeholder={'SEARCH HERE'}
        />
        {updatedList.length > 0 ? (
          updatedList.map((ingredient, key) => {
            return (
              <AllergyComponent
                key={key}
                ingredientName={ingredient}
                handleChosen={handleChosen}
              />
            )
          })
        ) : (
          <p className={styles.text}>No ingredients found</p>
        )}
      </div>
      <NextButton data={chosenList} />
      <BackButton />
    </div>
  )
}

export default QuizQuestion3
