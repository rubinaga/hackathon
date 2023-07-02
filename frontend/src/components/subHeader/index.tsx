import Search from "@components/search"
import styles from "@components/subHeader/subHeader.module.scss"
import Image from "next/image"
import Hero from "../../assets/svg/backgroundComplete.svg"
import Character from "../../assets/svg/character.svg"
import CookingPots from "../../assets/svg/cookingPots.svg"

const SubHeader = () => {

    return (
        <>
            <div className={styles.subHeader}>
                <div className={styles.hero_display}>
                    <div className={styles.hero_display_text_container}>
                        <div className={styles.hero_display_text_main}>
                            {"Keep yourself healthy with WHAT2EAT"}
                        </div>
                        <div className={styles.hero_display_text_subText}>
                            {"WHAT2EAT is a food application that lets you order your favourite dishes while you can also keep yourself healthy!"}
                        </div>
                    </div>
                    <Search />
                    <div className={styles.hero}>
                        <Image src={Hero} alt="hero" />
                        <div style={{ marginTop: "-400px", marginLeft: "100px" }}>
                            <Image src={Character} alt="character" />
                        </div>
                        <div style={{ marginTop: "-260px", marginLeft: "15px" }}>
                            <Image src={CookingPots} alt="cooking pots" />
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
export default SubHeader