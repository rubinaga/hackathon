import CartBody from "@components/cartBody"
import Footer from "@components/footer"
import Header from "@components/header"
import styles from "@styles/cart.module.scss"

const Cart = () => {

    return (
        <>
            <Header />
            <div className={styles.cart}>
                <CartBody />
            </div>
            <Footer />
        </>
    )
}
export default Cart