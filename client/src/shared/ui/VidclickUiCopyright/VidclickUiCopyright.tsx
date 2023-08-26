import {Typography, TypographyProps} from "@mui/material";
import {Link} from "react-router-dom";

export function VidclickUiCopyright(props: TypographyProps) {
    return (
        <Typography
            variant="body2"
            color="text.secondary"
            align="center"
            sx={{
                position: 'fixed',
                bottom: 0,
                width: '100%',
                height: 40,
            }} {...props}>
            {'Copyright © '}<Link to={'/'}>Vidclick</Link>
            {' '}{new Date().getFullYear()}{'.'}
        </Typography>
    )
}