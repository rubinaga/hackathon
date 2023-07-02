import { createSlice } from '@reduxjs/toolkit'
import { PageResponseGetModerateMealDTO } from 'openapi'

const initialState = {
  meals: {} as PageResponseGetModerateMealDTO,
}

const mealsSlice = createSlice({
  name: 'meals',
  initialState,
  reducers: {
    mealsDispatch: (state, action) => {
      state.meals = action.payload
    },
  },
})
export const { mealsDispatch } = mealsSlice.actions
export default mealsSlice.reducer
