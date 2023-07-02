import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import { ProductStoreType } from '@utils/enums'

interface CartTypes {
  cartItems: ProductStoreType[]
}

const initialState = {
  cartItems: [],
} as CartTypes

type AddProductType = {
  product: ProductStoreType
  count: number
}

const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addProduct: (state, action) => {
      return {
        ...state,
        cartItems: [...state.cartItems, action.payload],
      }
    },
    emptyCard(state, action) {
      state.cartItems = action.payload
    },
    setCount(state, action: PayloadAction<AddProductType>) {
      // find index and add new count on product count
    },
  },
})

export const { addProduct, setCount, emptyCard } = cartSlice.actions
export default cartSlice.reducer
