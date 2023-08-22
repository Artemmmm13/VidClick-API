import {AppBar, Container, Toolbar, Typography} from "@mui/material";
import {VidclickUiIcon} from "../../../shared/ui/VidclickUiIcon";

export function NavigationBar() {
    // const menuItems = [
    //     {key: 'fundraisingOffers', label: "Fundraising Offers", link: "/offers"},
    //     {key: 'about', label: "About Us", link: "/about"},
    //     {key: 'signup', label: "Sign Up", link: "/signup"},
    //     {key: 'login', label: "Log In", link: "/login"}
    // ]
    return (
        <AppBar position="static"
                color="inherit"
                style={{fontFamily: 'Bricolage Regular'}}>
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <VidclickUiIcon sx={{display: {xs: 'none', md: 'flex'}, mr: 10}}/>
                    <Typography sx={{display: {xs: 'none', md: 'flex'}}}/>
                </Toolbar>
            </Container>
        </AppBar>
    );
}