import {
    AppBar,
    Avatar,
    Box,
    Container,
    IconButton,
    Menu,
    MenuItem,
    Toolbar,
    Tooltip,
    Typography
} from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import {VidclickUiIcon} from "../../../shared/ui/VidclickUiIcon";
import {Link} from "react-router-dom";
import React, {useState} from "react";
import {VidclickUiButton} from "../../../shared/ui/VidclickUiButton";

export function NavigationBar() {
    const menuPages = [
        {link: '/fundraising', label: "Fundraising Offers", key: "offers"},
        {link: '/about', label: "About Us", key: "about"}
    ]

    const profileSettings = [
        {label: "Profile", key: "offers"},
        {label: "Settings", key: "about"},
        {label: "Statistics", key: "statistics"},
        {
            label: "Sign Out", key: "sign-out", action: () => {
                handleLogin();
                handleCloseUserMenu();
            }
        }
    ]

    const [login, setLogin] = useState(false);

    const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    const handleLogin = () => {
        setLogin(!login);
    }

    return (
        <div>
            <AppBar sx={{fontFamily: 'Bricolage', bgcolor: 'black'}}
                    position="static">
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <VidclickUiIcon sx={{display: {xs: 'none', md: 'flex'}, mr: 10}}/>
                        <Typography sx={{display: {xs: 'none', md: 'flex'}}}/>
                        <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                            <IconButton size="large"
                                        onClick={handleOpenNavMenu}
                                        color="inherit">
                                <MenuIcon/>
                            </IconButton>
                            <Menu anchorEl={anchorElNav}
                                  anchorOrigin={{
                                      vertical: "bottom",
                                      horizontal: "left"
                                  }}
                                  keepMounted
                                  transformOrigin={{vertical: "top", horizontal: "left"}}
                                  open={Boolean(anchorElNav)}
                                  onClose={handleCloseNavMenu}
                                  sx={{display: {xs: 'block', md: 'none'}}}
                            >
                                {
                                    menuPages.map((menuPage) => (
                                        <Link to={menuPage.link}
                                              style={{textDecoration: 'none', color: 'inherit'}}
                                        >
                                            <MenuItem key={menuPage.key}
                                                      onClick={handleCloseNavMenu}>
                                                <Typography textAlign="center">{menuPage.label}</Typography>
                                            </MenuItem>
                                        </Link>
                                    ))
                                }
                            </Menu>
                        </Box>
                        <VidclickUiIcon sx={{display: {xs: 'flex', md: 'none'}, mr: 1}}/>
                        <Typography sx={{display: {xs: 'flex', md: 'none'}, flexGrow: 1}}/>
                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                            {
                                menuPages.map((menuPage) => (
                                    <Link to={menuPage.link}
                                          style={{textDecoration: 'none', color: 'inherit'}}
                                    >
                                        <VidclickUiButton key={menuPage.key}
                                                onClick={handleCloseNavMenu}
                                                vdBackgroundColor="#000000"
                                                sx={{my: 2, mx: 1}}
                                        >
                                            {menuPage.label}
                                        </VidclickUiButton>
                                    </Link>
                                ))
                            }
                        </Box>
                        {login ? <Box sx={{flexGrow: 0}}>
                                <Tooltip title="Open Settings">
                                    <IconButton onClick={handleOpenUserMenu}
                                                sx={{p: 0}}>
                                        <Avatar alt="Remy Sharp"
                                                src="/static/images/avatar/2.jpg"/>
                                    </IconButton>
                                </Tooltip>
                                <Menu sx={{mt: '45px'}}
                                      anchorEl={anchorElUser}
                                      anchorOrigin={{
                                          vertical: 'top',
                                          horizontal: 'right',
                                      }}
                                      keepMounted
                                      transformOrigin={{
                                          vertical: 'top',
                                          horizontal: 'right',
                                      }}
                                      open={Boolean(anchorElUser)}
                                      onClose={handleCloseUserMenu}
                                >
                                    {
                                        profileSettings.map((profileSetting) => (
                                            profileSetting.action ?
                                                <MenuItem key={profileSetting.key}
                                                                              onClick={profileSetting.action}>
                                                    <Typography textAlign="center">{profileSetting.label}</Typography>
                                                </MenuItem> :
                                            <MenuItem key={profileSetting.key}
                                                      onClick={handleCloseUserMenu}>
                                                <Typography textAlign="center">{profileSetting.label}</Typography>
                                            </MenuItem>
                                        ))
                                    }
                                </Menu>
                            </Box>
                            :
                            <Box sx={{flexGrow: 0}}>
                                <VidclickUiButton sx={{my: 2, display: 'block'}}
                                        onClick={() => setLogin(!login)}>
                                    Sign Up
                                </VidclickUiButton>
                            </Box>
                        }
                    </Toolbar>
                </Container>
            </AppBar>
        </div>
    );
}