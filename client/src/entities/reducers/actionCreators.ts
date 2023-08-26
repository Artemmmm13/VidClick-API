import {createAsyncThunk} from "@reduxjs/toolkit";
import AuthService from "../services/AuthService.ts";
import {ILoginRequest} from "../../shared/models/ILoginRequest.ts";
import {ISignupRequest} from "../../shared/models/ISignupRequest.ts";

// TODO: think about isAuth field in the state

export const loginUser = createAsyncThunk(
    'user/register',
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async (data: ILoginRequest, thunkAPI) => {
        try {
            const response = await AuthService.login(data);
            console.log(response);
            localStorage.setItem('token', response.data.accessToken);
        } catch (e: any) {
            console.log(e.response?.data?.message)
        }
    }
)

export const signupUser = createAsyncThunk(
    'user/register',
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async (data: ISignupRequest, thunkAPI) => {
        try {
            const response = await AuthService.signup(data);
            console.log(response);
            localStorage.setItem('token', response.data.accessToken);
        } catch (e: any) {
            console.log(e.response?.data?.message)
        }
    }
)

export const logoutUser = createAsyncThunk(
    'user/register',
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async (_, thunkAPI) => {
        try {
            // eslint-disable-next-line @typescript-eslint/no-unused-vars
            const response = await AuthService.logout();
            localStorage.removeItem('token');
        } catch (e: any) {
            console.log(e.response?.data?.message)
        }
    }
)