import styles from "@components/cartBody/cartBody.module.scss"
import CartBackArrow from "../../assets/svg/cartBackArrow.svg"
import Image from "next/image"
import CartItem from "@components/cartItem"
import MasterCard from "../../assets/svg/mastercard.svg"
import Visa from "../../assets/svg/visa.svg"
import RuPay from "../../assets/svg/rupay.svg"
import Input from "@components/input"
import { useSelector } from "react-redux"
import { RootState } from "@utils/store"
import { useRouter } from "next/router"
import { Paths } from "@utils/enums"

const CartBody = () => {

    const router = useRouter()
    const cart = useSelector((state: RootState) => state.cart?.cartItems)
    const handleGoBack = () => {
        router.push(Paths.MenuPage)
    }

    return (
        <div className={styles.cartBody}>
            <div className={styles.main_cart_body}>
                <div style={{ display: "flex", flexDirection: "column" }}>
                    <div className={styles.cart_body_header}>
                        <Image style={{ paddingBottom: "20px" }} src={CartBackArrow} alt="cart back arrow" />
                        <div onClick={handleGoBack} className={styles.cart_body_text}>
                            {"Continue to select dishes"}
                        </div>
                    </div>
                    <div className={styles.shopping_cart_number_of_items}>
                        {"Shopping Cart"}
                    </div>
                    <div className={styles.number_of_items}>
                        {"You have"} {cart?.length} {"items on your cart!"}
                    </div>
                    <div className={styles.cart_items}>
                        {cart?.map((cart, indx) =>
                            <CartItem name={cart.name} price={cart.price} image={cart.thumbnail} rate={cart.rate} key={indx} />
                        )}
                    </div>
                </div>
                <div className={styles.cartBody_payment}>
                    <div className={styles.cartBody_payment_title}>{"Card Details"}</div>
                    <div className={styles.cartBody_payment_methods}>
                        <div><Image src={MasterCard} alt="mastercard" /></div>
                        <div><Image src={Visa} alt="visa" /></div>
                        <div><Image src={RuPay} alt="rupay" /></div>
                    </div>
                    <div className={styles.card_details_inputs}>
                        <Input label="Name on Card" />
                    </div>
                    <div className={styles.card_details_inputs}>
                        <Input label="Card Number" />
                    </div>
                    <div className={styles.card_details_inputs}>
                        <div className={styles.card_details_expiry}>
                            <Input label="Expiration date" />
                            <Input label="CVV" />
                        </div>
                    </div>
                    <div className={styles.total_pay}>
                        <div className={styles.total_pay_texts}>

                            <div>{"Subtotal"}</div>
                            <div>{"Shipping"}</div>
                            <div>{"Total (Incl. Tax)"}</div>
                        </div>
                        <div className={styles.total_pay_texts}>
                            <div>{"1600$"}</div>
                            <div>{"10$"}</div>
                            <div>{"1610$"}</div>

                        </div>
                    </div>
                </div>

            </div>
        </div>
    )
}
export default CartBody