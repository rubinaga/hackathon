import { Dispatch } from 'react'

export enum CookieNames {
  USER = 'user',
}
export enum Paths {
  LoginPage = '/login',
  HomePage = '/home',
  RegisterPage = '/register',
  SellerPage = '/seller',
  QuizPage = '/quiz',
  ProfilePage = '/profile',
  MenuPage = '/menu',
  CartPage = '/cart',
}

export interface InputProps {
  label: string
  value?: string
  onChange?: Dispatch<any>
}

export interface CategoryButtonProps {
  label: string
  isFirstButton?: boolean
  isLastButton?: boolean
  isMiddleButton?: boolean
}

export interface ProductCardProps {
  name?: string
  price?: number
  rate?: string
  image?: any
}

export type ProductStoreType = {
  rate: string
  name: string
  thumbnail: string
  price: string
  count: number
}

export interface ProductCartProps {
  name: string
  price: string
  rate: string
  image: string
}

export interface FilterProps {
  filters: Array<string>
}
