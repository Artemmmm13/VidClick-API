import {Box, SxProps} from "@mui/material";
import {Link} from "react-router-dom"
import logo from "./assets/vidclick-logo-2.png"
import logoSecondary from "./assets/logoc.png"

interface IVidclickUiIconProps{
    sx?: SxProps
    primary: boolean
}

export function VidclickUiIcon({sx, primary}: IVidclickUiIconProps) {
    return primary ? (
        <Link to="/">
            <Box
                component="img"
                sx={{height: 30, ...sx}}
                alt="vidclick-ui-logo-svg"
                src={logo}
            />
        </Link>
    ) : (
        <Link to="/">
            <Box
                component="img"
                sx={{height: 60, ...sx}}
                alt="vidclick-ui-logo-svg"
                src={logoSecondary}
            />
        </Link>
    );
}