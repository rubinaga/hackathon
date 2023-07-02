import { createSlice } from '@reduxjs/toolkit'
import { RootState } from '..'

const initialState = {
  quizIndex: 1,
  dietaryPreferences: [],
  allergies: [],
  goals: [],
  cuisine: [],
  carbon: 0,
}

const quizSlice = createSlice({
  name: 'quiz',
  initialState,
  reducers: {
    nextQuizIndex: (state, { payload }) => {
      state.quizIndex = state.quizIndex + payload.increment
      console.log(state.quizIndex)
    },
    prevQuizIndex: (state, { payload }) => {
      state.quizIndex = state.quizIndex - payload.decrement
    },
    resetQuizIndex: (state, { payload }) => {
      state.quizIndex = 1
    },
    setDietaryPreferences: (state, { payload }) => {
      state.dietaryPreferences = payload.dietaryPreferences
    },
    setAllergies: (state, { payload }) => {
      state.allergies = payload.allergies
    },
    setGoals: (state, { payload }) => {
      state.goals = payload.goals
    },
    setCuisine: (state, { payload }) => {
      state.cuisine = payload.cuisine
    },
    setCarbon: (state, { payload }) => {
      state.carbon = payload.carbon
    },
  },
})
export const {
  nextQuizIndex,
  prevQuizIndex,
  resetQuizIndex,
  setDietaryPreferences,
  setAllergies,
  setGoals,
  setCuisine,
  setCarbon,
} = quizSlice.actions

export const getQuizIndex = (state: RootState) => state.quiz.quizIndex

export default quizSlice.reducer
