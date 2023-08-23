import {ThemeProvider} from "@mui/material";
import {NavigationBar} from "../../entities/NavigationBar/ui";
import {Outlet} from "react-router-dom";
import theme from "../../app/providers/mui";

export function NavigationBarWrapper () {
    return (
        <div>
            <ThemeProvider theme={theme}>
                <NavigationBar/>
            </ThemeProvider>
            <Outlet/>
        </div>
    );
}