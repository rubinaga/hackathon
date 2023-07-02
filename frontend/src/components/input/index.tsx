import { InputProps } from "@utils/enums"
import styles from "./input.module.scss"

const Input = (props: InputProps) => {
    return (
        <>
            <input onChange={e => props?.onChange(e.target.value)} defaultValue={props.value} placeholder={props.label} className={styles.input}>
            </input>
        </>
    )
}
export default Input