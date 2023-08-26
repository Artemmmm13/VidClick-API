import {IUser, UserRole} from "../../shared/models/IUser.ts";
import {createSlice} from "@reduxjs/toolkit";

interface UserState {
    user: IUser;
    isLoading: boolean;
    error: string;
}

const initialUser: IUser = {
    id: '',
    name: '',
    email: '',
    password: '',
    creatorProfileImage: '',
    role: UserRole.USER
}

const initialState: UserState = {
    user: initialUser,
    isLoading: false,
    error: ''
}

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {},
    extraReducers: {

    }
})

export default userSlice.reducer;