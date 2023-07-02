import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  showLoader: false,
  searchQuery: undefined,
}

const dataSlice = createSlice({
  name: 'data',
  initialState,
  reducers: {
    updateShowLoader: (state, action) => {
      state.showLoader = action.payload
    },
    updateSearchQuery: (state, action) => {
      state.searchQuery = action.payload
    },
  },
})
export const { updateShowLoader, updateSearchQuery } = dataSlice.actions
export default dataSlice.reducer
