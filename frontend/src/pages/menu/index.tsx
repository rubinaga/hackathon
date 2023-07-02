import Footer from "@components/footer"
import Header from "@components/header"
import MenuBody from "@components/menuBody"
import styles from "@styles/menu.module.scss"

const Menu = () => {
    return (
        <>
            <Header />
            <div className={styles.menu}>
                <MenuBody />
                {/* <Footer /> */}
            </div>
        </>
    )
}
export default Menu