import styles from "@components/search/search.module.scss"
import { Paths } from "@utils/enums"
import { updateSearchQuery, updateShowLoader } from "@utils/store/reducers/data"
import { useRouter } from "next/router"
import { useState } from "react"
import { useDispatch } from "react-redux"

const Search = () => {
    const [searchQuery, setSearchQuery] = useState<string>("")
    const dispatch = useDispatch()
    const router = useRouter()

    const handleSearch = () => {
        if (searchQuery.length > 1) {
            dispatch(updateShowLoader(true))
            dispatch(updateSearchQuery(searchQuery))
            router.push(Paths.MenuPage)
            dispatch(updateShowLoader(false))
        }
    }
    return (
        <>
            <div className={styles.search}>
                <div onClick={handleSearch} className={styles.search_button}>
                    <div className={styles.search_button_text}>{"Search"}</div>
                </div>
                <input onChange={e => setSearchQuery(e.target.value)} placeholder="Search food you love" className={styles.search_text}>
                </input>
            </div>
        </>
    )
}
export default Search