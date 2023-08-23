import {Box, SxProps} from "@mui/material";
import {Link} from "react-router-dom"
import logo from "./assets/vidclick-logo-2.png"

interface IVidclickUiIconProps{
    sx?: SxProps
}

export function VidclickUiIcon({sx}: IVidclickUiIconProps) {
    return (
        <Link to="/">
            <Box
                component="img"
                sx={{height: 30, ...sx}}
                alt="vidclick-ui-logo-svg"
                src={logo}
            />
        </Link>
    );
}