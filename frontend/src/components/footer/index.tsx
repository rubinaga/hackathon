import styles from "@components/footer/footer.module.scss"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { faFacebook, faInstagram } from "@fortawesome/free-brands-svg-icons"

const Footer = () => {
    return (
        <>
            <div className={styles.footer}>
                <div className={styles.subFooter}>
                    <div className={styles.footer_menu}>
                        <div className={styles.footer_brand_name}>
                            {"WHAT2EAT"}
                        </div>
                        <div className={styles.footer_main_menu}>
                            <div className={styles.menu_text}>{"Home"}</div>
                            <div className={styles.menu_text}>{"About"}</div>
                            <div className={styles.menu_text}>{"Menu"}</div>
                            <div className={styles.menu_text}>{"Blog"}</div>
                            <div className={styles.menu_text}>{"Contact"}</div>
                        </div>
                        <div className={styles.footer_main_menu_icons}>
                            <div>
                                <FontAwesomeIcon icon={faInstagram} color="white" />
                            </div>
                            <div>
                                <FontAwesomeIcon icon={faFacebook} color="white" />
                            </div></div>
                    </div>
                    <div className={styles.copyright_frame}>
                        <div className={styles.copyright_text}>
                            {"Copyright @2023 WHAT2EAT"}
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
export default Footer