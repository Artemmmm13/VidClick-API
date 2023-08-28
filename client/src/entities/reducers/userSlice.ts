import {IUser, UserRole} from "../../shared/models/IUser.ts";
import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {checkAuth, loginUser, logoutUser, signupUser} from "./actionCreators.ts";


interface UserState {
    user: IUser;
    isLoading: boolean;
    isAuth: boolean;
    error: string;
}

const initialUser: IUser = {
    id: '',
    name: '',
    email: '',
    password: '',
    creatorProfileImage: '',
    role: UserRole.USER,
    accountCreatedOn: 0
}

const initialState: UserState = {
    user: initialUser,
    isLoading: false,
    isAuth: false,
    error: ''
}

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {},
    extraReducers: {
        [signupUser.pending.type]: (state) => {
            state.isLoading = true;
        },
        [signupUser.fulfilled.type]: (state, action: PayloadAction<IUser>) => {
            state.isLoading = false;
            state.error = '';
            state.user = action.payload;
            state.isAuth = true;
        },
        [signupUser.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload;
        },
        [loginUser.pending.type]: (state) => {
            state.isLoading = true;
        },
        [loginUser.fulfilled.type]: (state, action: PayloadAction<IUser>) => {
            state.isLoading = false;
            state.error = '';
            state.user = action.payload;
            state.isAuth = true;
        },
        [loginUser.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload
        },
        [logoutUser.pending.type]: (state) => {
            state.isLoading = true;
        },
        [logoutUser.fulfilled.type]: (state) => {
            state.isLoading = false;
            state.error = '';
            state.user = {} as IUser;
            state.isAuth = false;
        },
        [logoutUser.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload
        },
        [checkAuth.pending.type]: (state) => {
            state.isLoading = true;
        },
        [checkAuth.fulfilled.type]: (state, action: PayloadAction<IUser>) => {
            state.isLoading = false;
            state.error = '';
            state.user = action.payload;
            state.isAuth = true;
        },
        [checkAuth.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload;
        }
    }
})

export default userSlice.reducer;