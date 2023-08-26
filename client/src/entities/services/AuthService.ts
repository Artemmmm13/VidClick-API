import {IAuthResponse} from "../../shared/models/IAuthResponse.ts";
import {AxiosResponse} from 'axios';
import $api from "../../shared/http";
import {ILoginRequest} from "../../shared/models/ILoginRequest.ts";
import {ISignupRequest} from "../../shared/models/ISignupRequest.ts";

export default class AuthService {
    static async login(data: ILoginRequest): Promise<AxiosResponse<IAuthResponse>> {
        return $api.post<IAuthResponse>('/creator/login', {...data})
    }

    static async signup(data: ISignupRequest){
        return $api.post<IAuthResponse>('/creator/signup', {...data})
    }

    static async logout(): Promise<void> {
        return $api.post('/creator/logout')
    }
}