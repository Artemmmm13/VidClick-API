import {createTheme} from "@mui/material";

declare module "@mui/material/styles" {
    interface Palette {

    }
    interface PaletteOptions {

    }
}

export const theme = createTheme({
    typography: {
        fontFamily: 'Bricolage Regular',
        fontSize: 18
    },
    palette: {

    }
})