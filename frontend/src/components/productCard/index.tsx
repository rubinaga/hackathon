import styles from '@components/productCard/productCard.module.scss'
import { ProductCardProps } from '@utils/enums'
import Image from 'next/image'
import TestProductImage from '../../assets/svg/testProductImage.svg'
import AddToCart from '../../assets/svg/addToCart.svg'
import Star from '../../assets/svg/star.svg'
import { useDispatch } from 'react-redux'
import { addProduct } from '@utils/store/reducers/cart'
import { toast } from 'react-toastify'

const ProductCard = (props: ProductCardProps) => {
  const dispatch = useDispatch()

  const handleAddToCartButton = () => {
    dispatch(
      addProduct({
        name: props?.name,
        price: props?.price,
        rate: props?.rate,
        image: props?.image,
      }),
    )
    toast.success('Meal added to cart!')
  }
  return (
    <>
      <div className={styles.productCard}>
        <div className={styles.productCard_image}>
          <Image src={TestProductImage} alt="product image" />
        </div>
        <div className={styles.productCard_info}>
          <div className={styles.productCard_info_frame}>
            <div className={styles.productCard_info_name}>{props.name}</div>
          </div>
          <div className={styles.productCard_info_details}>
            <div className={styles.productCard_info_ranking}>
              <div>{'4.7'}</div>
              <div className={styles.productCard_info_price}>
                {props.price}$
              </div>
              <div className={styles.star}>
                <Image src={Star} alt="star" />
              </div>
            </div>
            <div
              onClick={handleAddToCartButton}
              className={styles.productCard_info_button}>
              <Image src={AddToCart} alt="add to cart" />
            </div>
          </div>
        </div>
      </div>
    </>
  )
}
export default ProductCard
