import styles from "@components/categoryButton/categoryButton.module.scss"
import { CategoryButtonProps } from "@utils/enums"

const CategoryButton = (props: CategoryButtonProps) => {

    return (
        <>
            {props.isFirstButton &&
                <div className={styles.FirstCategoryButton}>
                    {props.label}
                </div>
            }
            {props.isMiddleButton &&
                <div className={styles.MiddleCategoryButton}>
                    {props.label}
                </div>
            }
            {props.isLastButton &&
                <div className={styles.LastCategoryButton}>
                    {props.label}
                </div>
            }
        </>
    )
}
export default CategoryButton