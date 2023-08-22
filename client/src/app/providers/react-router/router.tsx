import {createBrowserRouter} from "react-router-dom";
import {NavigationBarWrapper} from "../../../widgets/wrapper";
import {ErrorPage} from "../../../pages/ErrorPage";
import {Main} from "../../../pages/Main";
import {FundraisingOffers} from "../../../pages/FundraisingOffers/FundraisingOffers.tsx";
import {About} from "../../../pages/About";
import {CreatorProfile} from "../../../pages/CreatorProfile";
import {RegistrationPage} from "../../../pages/RegistrationPage/RegistrationPage.tsx";
import {LoginPage} from "../../../pages/LoginPage";

export const router = createBrowserRouter([
        {
            path: "/",
            element: <NavigationBarWrapper/>,
            errorElement: <ErrorPage/>,
            children: [
                {
                    path: "/",
                    element: <Main/>
                },
                {
                    path: "/fundraising",
                    element: <FundraisingOffers/>
                },
                {
                    path: "/about",
                    element: <About/>
                },
                {
                    path: "/profile:id",
                    element: <CreatorProfile/>
                }
            ]
        },
        {
            path: "/signup",
            element: <RegistrationPage/>
        },
        {
            path: "/login",
            element: <LoginPage/>
        }
    ]
)