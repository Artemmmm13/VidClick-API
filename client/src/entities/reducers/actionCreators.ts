import {createAsyncThunk} from "@reduxjs/toolkit";
import AuthService from "../services/AuthService.ts";
import {ILoginRequest} from "../../shared/models/ILoginRequest.ts";
import {ISignupRequest} from "../../shared/models/ISignupRequest.ts";
import axios from "axios";
import {IAuthResponse} from "../../shared/models/IAuthResponse.ts";
import {API_URL} from "../../shared/http";
export const loginUser = createAsyncThunk(
    'user/register',
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async (data: ILoginRequest, thunkAPI) => {
        try {
            const response = await AuthService.login(data);
            console.log(response);
            localStorage.setItem('token', response.data.accessToken);
            return response.data.new_creator
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
            return response.data.new_creator
        } catch (e: any) {
            console.log(e.response?.data?.message)
        }
    }
)

export const logoutUser = createAsyncThunk(
    'user/logout',
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

export const checkAuth = createAsyncThunk(
    'user/checkAuth',
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    async (_, thunkAPI) => {
        try {
            const response = await axios.get<IAuthResponse>(`${API_URL}/refresh`, {withCredentials: true});
            localStorage.setItem('token', response.data.accessToken);
            return response.data.new_creator
        } catch (e: any) {
            console.log(e.response?.data?.message)
        }
    }
)