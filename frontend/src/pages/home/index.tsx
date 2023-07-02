import Header from '@components/header';
import SubHeader from '@components/subHeader';
import styles from "@styles/home.module.scss"
import Footer from '@components/footer';

const Home = () => {
    return (<>
        <div className={styles.home}>
            <Header />
            <SubHeader />
            <Footer />
        </div>
    </>)
}
export default Home