import styles from "@components/cartItem/cartItem.module.scss"
import Image from "next/image"
import TestProductImage from "../../assets/svg/testProductImage.svg"
import TrashCan from "../../assets/svg/trashCan.svg"
import { ProductCartProps } from "@utils/enums"
import { useDispatch } from "react-redux"
import { emptyCard } from "@utils/store/reducers/cart"
import { toast } from "react-toastify"

const CartItem = (props: ProductCartProps) => {
    const dispatch = useDispatch()

    const handleTrashCan = () => {
        dispatch(emptyCard([]))
        toast.success("This product has been removed from cart!")
    }
    return (
        <div className={styles.cart_item}>
            <div style={{ padding: "13px" }}>
                <div className={styles.cart_item_image}>
                    <Image className={styles.cart_item_svg} src={TestProductImage} alt="test image" />
                </div>
            </div>
            <div className={styles.product_labels}>
                <div>
                    {props.name}
                </div>
            </div>
            <div className={styles.cart_item_price}>
                {props.price}$
            </div>
            <div onClick={handleTrashCan} className={styles.cart_item_delete_trash}>
                <Image src={TrashCan} alt="trash can" />
            </div>
        </div>
    )
}
export default CartItem