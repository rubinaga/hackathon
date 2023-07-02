import styles from '@components/enterForm/enterForm.module.scss'
import { useState } from 'react'
import loginLine from '../../assets/svg/loginLine.svg'
import googleSvg from '../../assets/svg/google.svg'
import facebookSvg from '../../assets/svg/facebook.svg'

import Image from 'next/image'
import FormButton from '@components/formButton'
import { useRouter } from 'next/router'
import { CookieNames, Paths } from '@utils/enums'
import { useDispatch, useSelector } from 'react-redux'
import { updateShowLoader } from '@utils/store/reducers/data'
import { useCookies } from 'react-cookie'
import { RootState } from '@utils/store'
import { toast } from 'react-toastify'
import {
  AppUserControllerService,
  AuthTokenControllerService,
  DetailedTokenDetailsDTO,
} from 'openapi'
import { loginDispatch } from '@utils/store/reducers/user'
import { setUserCookie } from '@utils/authorization'
import Quiz from 'src/pages/quiz'

const EnterForm = ({ type }: { type: string }) => {
  const [email, setEmail] = useState<string>('')
  const [password, setPassword] = useState<string>('')
  const [confirmPassword, setConfirmPassword] = useState<string>('')
  const [phoneNumber, setPhoneNumber] = useState<string>('')
  const [remember, setRemember] = useState<boolean>(false)
  const [, setCookie] = useCookies([CookieNames.USER])
  const [name, setName] = useState<string>('')
  const [surname, setSurname] = useState<string>('')

  const router = useRouter()
  const dispatch = useDispatch()
  const handleLogin = () => {
    dispatch(updateShowLoader(true))
    AuthTokenControllerService.authenticate({
      username: email,
      password: password,
    })
      .then((res: DetailedTokenDetailsDTO) => {
        if (res.access_token) {
          dispatch(loginDispatch(res))
          setUserCookie(setCookie, res)
          router.push(Paths.HomePage)
        } else {
          toast.error('Your credentials are incorrect!')
        }
      })
      .catch(err => {
        console.log(err)
      })
      .finally(() => {
        dispatch(updateShowLoader(false))
      })
  }

  const handleRegister = () => {
    dispatch(updateShowLoader(true))
    AppUserControllerService.registerUser({
      email: email,
      name: name,
      password: password,
      surname: surname,
      phoneNumber: phoneNumber,
    })
      .then(res => {
        console.log(res)
        if (res.access_token) {
          dispatch(loginDispatch(res))
          setUserCookie(setCookie, res)
          router.push(Paths.QuizPage)
        } else {
          toast.error('Please fill all the fields')
        }
      })
      .catch(err => {
        console.log(err)
      })
      .finally(() => {
        dispatch(updateShowLoader(false))
      })
  }

  let displayedString = 'Log In'

  type?.toUpperCase() === 'REGISTER'
    ? (displayedString = 'Register Now')
    : 'Log In'

  return (
    <div className={styles.outsideForm}>
      <div className={styles.brand_name}>{'WHAT2EAT'}</div>

      <div className={styles.loginForm}>
        <p className={styles.loginTextSize}>{displayedString}</p>

        <input
          type="email"
          value={email}
          onChange={e => {
            setEmail(e.target.value)
          }}
          className={styles.inputStyles}
          placeholder="email"
        />
        <input
          type="password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          placeholder="password"
          className={styles.inputStyles}
        />
        {type.toUpperCase() === 'REGISTER' && (
          <>
            <input
              type="text"
              value={name}
              onChange={e => setName(e.target.value)}
              placeholder="Name"
              className={styles.inputStyles}
            />
            <input
              type="text"
              value={surname}
              onChange={e => setSurname(e.target.value)}
              placeholder="Surname"
              className={styles.inputStyles}
            />
            <input
              type="tel"
              value={phoneNumber}
              onChange={e => setPhoneNumber(e.target.value)}
              placeholder="phone number (optional)"
              className={styles.inputStyles}
            />
          </>
        )}
        {type.toUpperCase() !== 'REGISTER' && (
          <div className={styles.rememberAndForgot}>
            <div className={styles.rememberMe}>
              <input
                type="checkbox"
                checked={remember}
                onChange={e => setRemember(e.target.checked)}
                placeholder="type here"
                className={styles.rememberCheckbox}
              />
              <p className={styles.rememberText}>Remember me</p>
            </div>
            <p className={styles.forgotPassword}>Forgot password</p>
          </div>
        )}
        {type.toUpperCase() !== 'REGISTER' && (
          <FormButton
            handler={handleLogin}
            label={displayedString}
            key={type}
          />
        )}
        <br />
        {type.toUpperCase() === 'REGISTER' && (
          <FormButton handler={handleRegister} label={'Register'} key={type} />
        )}
        {type.toUpperCase() != 'REGISTER' && (
          <div
            onClick={() => router.push(Paths.RegisterPage)}
            className={styles.text_labels_for_account}>
            {'Create new account!'}
          </div>
        )}
        {type.toUpperCase() === 'REGISTER' && (
          <div style={{ marginTop: '10px' }}>
            <div
              className={styles.text_labels_for_account}
              onClick={() => router.push(Paths.LoginPage)}>
              {'Back to login!'}
            </div>
          </div>
        )}
        <div className={styles.lines}>
          <Image
            src={loginLine}
            alt={'line'}
            width={350}
            className={styles.loginLine}
          />
          <p className={styles.lineText}> OR </p>
          <Image
            src={loginLine}
            alt={'line'}
            width={350}
            className={styles.loginLine}
          />
        </div>
        <div className={styles.allLoginForms}>
          <div className={styles.individualLoginForm1}>
            <Image src={googleSvg} alt={'google'} />
          </div>
          <div className={styles.individualLoginForm2}>
            <Image src={facebookSvg} alt={'facebook'} />
          </div>
        </div>
      </div>
    </div>
  )
}

export default EnterForm
