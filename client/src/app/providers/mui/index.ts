import { createTheme } from '@mui/material/styles';

declare module "@mui/material/styles" {
    interface Palette {
        vidclickUiPrimary?: string;
    }
    interface PaletteOptions {
        vidclickUiPrimary?: string;
    }
}

const theme = createTheme({
    typography: {
        fontFamily: 'Bricolage, Arial',
        fontSize: 18
    },
    palette: {
        vidclickUiPrimary: '#1B1464',
    }
});

export default theme;
