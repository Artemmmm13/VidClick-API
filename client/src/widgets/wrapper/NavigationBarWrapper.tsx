import {ThemeProvider} from "@mui/material";
import {theme} from "../../app/providers/mui";
import {NavigationBar} from "../../entities/NavigationBar/ui";
import {Outlet} from "react-router-dom";

export function NavigationBarWrapper () {
    return (
        <div>
            <ThemeProvider theme={theme}>
                <NavigationBar/>
                <Outlet/>
            </ThemeProvider>
        </div>
    );
}