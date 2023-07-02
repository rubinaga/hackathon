import CategoryButton from "@components/categoryButton"
import Filter from "@components/filter"
import Input from "@components/input"
import styles from "@components/menuBody/menuBody.module.scss"
import ProductCard from "@components/productCard"
import { RootState } from "@utils/store"
import { updateSearchQuery, updateShowLoader } from "@utils/store/reducers/data"
import { mealsDispatch } from "@utils/store/reducers/meals"
import { MealControllerService, PageResponseGetModerateMealDTO } from "openapi"
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"

const MenuBody = () => {
    const dispatch = useDispatch()
    const meals = useSelector((state: RootState) => state.meal?.meals)
    const searchQuery = useSelector((state: RootState) => state.data.searchQuery)
    const [menuSearch, setMenuSearch] = useState<string>(searchQuery)
    const [mealList, setMealList] = useState<any>()

    const handleSearchClick = () => {
        if (menuSearch.length > 0) {
            dispatch(updateSearchQuery(menuSearch))
        }
    }

    useEffect(() => {
        dispatch(updateShowLoader(true))
        MealControllerService.getAllMeals().then(res => {
            dispatch(mealsDispatch(res))
        }).catch((err) => {
            console.log(err);
        }).finally(() => {
            dispatch(updateShowLoader(false))
        })
    }, [])
    const allergies = [
        'rice',
        'sugar',
        'butter',
        'eggs',
        'garlic',
        'pasta',
        'salt',
        'apple',
        'pineapple',
    ]
    const dietary = [
        'vegan',
        'paleo',
        'fodmap',
        'vegetarian',
        'gluten free',
        'dairy free'
    ]
    const goals = [
        'digestion',
        'weight loss',
        'muscle gain',
        'heart health',
        'mental health',
        'sleep'
    ]
    useEffect(() => {
        if (menuSearch?.length > 0) {
            const searchQueryFilter = meals?.items?.filter((meal) => meal?.name?.includes(menuSearch))
            setMealList(searchQueryFilter)
        } else {
            setMealList(meals?.items)
        }
    }, [meals, searchQuery, menuSearch])

    return (
        <>
            <div className={styles.menuBody}>
                <div className={styles.menu_body_filters}>
                    <div className={styles.menu_body_selected_category}>{"All Cuisines"}</div>
                    <div className={styles.menu_body_categories}>
                        <CategoryButton isFirstButton={true} label="Italian" />
                        <CategoryButton isMiddleButton={true} label="Asian" />
                        <CategoryButton isMiddleButton={true} label="Mexican" />
                        <CategoryButton isMiddleButton={true} label="French" />
                        <CategoryButton isMiddleButton={true} label="American" />
                        <CategoryButton isLastButton={true} label="Mediterrean" />
                    </div>
                    <div className={styles.filters}>
                        <div className={styles.menu_body_filter}>
                            <Filter filters={allergies} />
                        </div>
                        <div className={styles.menu_body_filter}>
                            <Filter filters={dietary} />
                        </div>
                        <div className={styles.menu_body_filter}>
                            <Filter filters={goals} />
                        </div>
                    </div>
                </div>
                <div className={styles.menuBody_search}>
                    <Input onChange={setMenuSearch} value={searchQuery} label="Search" />
                    <div className={styles.search_button}>
                        <div onClick={handleSearchClick} className={styles.search_button_text}>{"Search"}</div>
                    </div>
                </div>
                <div className={styles.products}>
                    {mealList?.map((meal, index) =>
                        <ProductCard image={meal.image} name={meal.name} price={meal.price} key={index} />
                    )}
                </div>
            </div>
        </>
    )
}
export default MenuBody