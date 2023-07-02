import Input from "@components/input"
import styles from "@components/sellerBody/sellerBody.module.scss"
import Image from "next/image"
import Seller from "../../assets/svg/seller.svg"

const SellerBody = () => {
    return (
        <>
            <div className={styles.seller_body}>
                <div className={styles.seller_main}>
                    <div className={styles.seller_form}>
                        <div className={styles.seller_main_text}>
                            <div className={styles.seller_text1}>
                                {"Do you want to become a seller?"}
                            </div>
                            <div className={styles.seller_text2}>{"Fill this form with your details and your bussines details to register your bussines!"}</div>
                        </div>
                        <div className={styles.seller_form_inputs}>
                            <div className={styles.form_input_row}>
                                <Input label="Name" />
                                <Input label="Surname" />
                                <Input label="Email" />
                            </div>
                            <div className={styles.form_input_row}>
                                <Input label="Phone Number" />
                            </div>
                            <div className={styles.form_input_row}>
                                <Input label="Bussines Name" />
                                <Input label="Bussines Adress" />
                                <Input label="NIPT" />
                            </div>
                        </div>
                    </div>
                    <div className={styles.seller_image}>
                        <Image src={Seller} alt="seller" />
                    </div>
                </div>
            </div>
        </>
    )
}
export default SellerBody