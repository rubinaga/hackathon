import { createSlice } from "@reduxjs/toolkit";
import { DetailedTokenDetailsDTO } from "openapi";

const initialState = { user: {} as DetailedTokenDetailsDTO };

const userSlice = createSlice({
	name: "user",
	initialState,
	reducers: {
		loginDispatch: (state, action) => {
			state.user = action.payload;
		},
	},
});
export const { loginDispatch } = userSlice.actions;
export default userSlice.reducer;
