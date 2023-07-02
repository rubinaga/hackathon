import styles from "@components/header/header.module.scss"
import { faSearch, faShoppingCart } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { Paths } from "@utils/enums"
import { RootState } from "@utils/store"
import { useRouter } from "next/router"
import { useSelector } from "react-redux"

const Header = () => {
    const router = useRouter()
    const user = useSelector((state: RootState) => state.user.user)

    const handleProfileClick = () => {
        if (Object.keys(user).length > 0) {
            router.push(Paths.ProfilePage)
        } else {
            router.push(Paths.LoginPage)
        }
    }

    return (
        <>
            <div className={styles.header}>
                <div className={styles.subHeader}>
                    <div onClick={() => router.push(Paths.HomePage)} className={styles.header_brand_name}>{"What2Eat"}</div>
                    <div className={styles.header_menu}>
                        <div className={styles.menu_texts}>
                            <div onClick={() => router.push(Paths.HomePage)} className={styles.menu_text}>{"Home"}</div>
                            <div onClick={() => router.push(Paths.MenuPage)} className={styles.menu_text}>{"Menu"}</div>
                            <div onClick={() => router.push(Paths.SellerPage)} className={styles.menu_text}>{"Seller"}</div>
                            <div onClick={handleProfileClick} className={styles.menu_text}>{"Profile"}</div>
                        </div>
                        <div className={styles.menu_icons}>
                            <div className={styles.menu_icon} onClick={() => router.push(Paths.CartPage)}>
                                <FontAwesomeIcon icon={faShoppingCart} color="white" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Header