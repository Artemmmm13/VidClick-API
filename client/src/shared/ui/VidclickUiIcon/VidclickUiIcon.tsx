import {Box, SxProps} from "@mui/material";
import {Link} from "react-router-dom"
import logo from "./assets/logo.svg"

interface IVidclickUiIconProps{
    sx?: SxProps
}

export function VidclickUiIcon({sx}: IVidclickUiIconProps) {
    return (
        <Link to="/">
            <Box
                component="img"
                sx={{height: 40, ...sx}}
                alt="vidclick-ui-logo-svg"
                src={logo}
            />
        </Link>
    );
}