import Footer from "@components/footer"
import Header from "@components/header"
import Input from "@components/input"
import styles from "@styles/profile.module.scss"
import { RootState } from "@utils/store"
import { updateShowLoader } from "@utils/store/reducers/data"
import { AppUserControllerService, GetAppUserDTO, OpenAPI } from "openapi"
import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import Image from "next/image"
import { toast } from "react-toastify"

const Profile = () => {
    const user = useSelector((state: RootState) => state.user.user)
    const dispatch = useDispatch();
    const [userProfile, setUserProfile] = useState<GetAppUserDTO>()

    useEffect(() => {
        if (Object.keys(user).length > 0) {
            OpenAPI.TOKEN = user.access_token
            dispatch(updateShowLoader(true))
            AppUserControllerService.getUserByUsername(user.username).then((res: GetAppUserDTO) => {
                setUserProfile(res)
            }).catch((err) => {
                console.log(err);
            }).finally(() => {
                dispatch(updateShowLoader(false))
            })
        }
    }, [user])

    const [name, setName] = useState<string>(userProfile?.name)
    const [surname, setSurname] = useState<string>(userProfile?.surname)
    const [email, setEmail] = useState<string>(userProfile?.username)
    const [phoneNumber, setPhoneNumber] = useState(userProfile?.phoneNumber)

    const handleUpdateUser = () => {
        OpenAPI.TOKEN = user?.access_token
        dispatch(updateShowLoader(true))
        AppUserControllerService.updateUserByUsername(user?.username, { name: name, phoneNumber: phoneNumber, surname: surname }).then((res) => {
            if (res) {
                toast.success("User profile has been updated!")
            }
        }).catch((err) => {
            console.log(err);
        }).finally(() => {
            dispatch(updateShowLoader(false))
        })
    }
    return (
        <>
            <Header />
            <div className={styles.profile}>
                <div className={styles.main_div}>
                    <div className={styles.profile_container}>
                        <div className={styles.main_body}>
                            <div className={styles.profile_body_container}>
                                <div className={styles.profile_badge}>
                                    <div className={styles.avatar_div}>
                                        {/* <Image className={styles.profile_avatar} src={userProfile.profileImagePath} width={150} height={150} /> */}
                                    </div>
                                    <div className={styles.settings_page}>
                                        <button onClick={() => { }} className={styles.upload_pic}>Upload Profile Picture</button>

                                    </div>
                                </div>
                                <div className={styles.profile_settings}>
                                    <div className={styles.inputs_div}>
                                        <div className={styles.first_input_div}>

                                            <div>
                                                <div className={styles.firstName_label}>
                                                    First Name
                                                </div>
                                                <div>
                                                    <Input onChange={setName} value={userProfile?.name} label="First Name" />

                                                </div>
                                            </div>

                                            <div className={styles.surname_label}>
                                                <div className={styles.firstName_label}>
                                                    Surname
                                                </div>
                                                <div>
                                                    <Input onChange={setSurname} value={userProfile?.surname} label="Surname" />

                                                </div>
                                            </div>

                                        </div>

                                        <div className={styles.second_input_div}>
                                            <div>
                                                <div className={styles.firstName_label}>
                                                    Email
                                                </div>
                                                <div>
                                                    <Input onChange={setEmail} value={userProfile?.email} label="Email" />

                                                </div>
                                            </div>

                                            <div className={styles.surname_label}>
                                                <div className={styles.firstName_label}>
                                                    Phone Number
                                                </div>
                                                <div>
                                                    <Input onChange={setPhoneNumber} value={userProfile?.phoneNumber} label="Phone Number" />

                                                </div>
                                            </div>
                                        </div>

                                        <div className={styles.buttons_div}>
                                            <div>
                                                <button onClick={handleUpdateUser} className={styles.button}>Update</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    )
}
export default Profile