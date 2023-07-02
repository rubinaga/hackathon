import styles from "@components/filter/filter.module.scss"
import { FilterProps } from "@utils/enums"

const Filter = (props: FilterProps) => {
    return (
        <>
            <div>
                <select className={styles.filter}>
                    {props?.filters?.map((filter, index) =>
                        <option key={index} className={styles.filter_option}>{filter}</option>
                    )}
                </select>
            </div>
        </>
    )
}
export default Filter