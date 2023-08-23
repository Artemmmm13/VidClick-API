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
import {VidclickUiTextField} from "../../../shared/ui/VidclickUiTextField";
import * as Yup from "yup";
import {Field, Form, Formik} from "formik";

export function NavigationBar() {
    const menuPages = [
        {link: '/fundraising', label: "Fundraising Offers", key: "offers"},
        {link: '/about', label: "About Us", key: "about"}
    ]

    const profileSettings = [
        {label: "Profile", key: "profile"},
        {label: "Settings", key: "settings"},
        {label: "Statistics", key: "statistics"},
        {
            label: "Sign Out", key: "sign-out", action: () => {
                handleLogin();
                handleCloseUserMenu();
            }
        }
    ]

    const [login, setLogin] = useState(false);
    const [loginFormOpen, setLoginFormOpen] = useState(false);

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

    const handleLoginClick = () => {
        setLoginFormOpen(true);
        handleCloseNavMenu();
    };

    const handleLoginFormClose = () => {
        setLoginFormOpen(false);
    };

    interface IValuesLogin {
        email: string;
        password: string;
    }

    const validationSchema = Yup.object({
        email: Yup.string().email('Incorrect email.').required('This field is required.'),
        password: Yup.string().required('This field is required.'),
    });

    const initialValues: IValuesLogin = {
        email: '',
        password: ''
    }

    const onSubmit = (values: IValuesLogin) => {
        console.log(values)
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
                                        <Link key={menuPage.key}
                                              to={menuPage.link}
                                              style={{textDecoration: 'none', color: 'inherit'}}
                                        >
                                            <MenuItem onClick={handleCloseNavMenu}>
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
                                    <Link key={menuPage.key}
                                          to={menuPage.link}
                                          style={{textDecoration: 'none', color: 'inherit'}}
                                    >
                                        <VidclickUiButton
                                            onClick={handleCloseNavMenu}
                                            $vdBackgroundColor="#000000"
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
                                                  onClick={handleLoginClick}>
                                    Sign Up
                                </VidclickUiButton>
                            </Box>
                        }
                    </Toolbar>
                </Container>
                <Menu
                    anchorEl={anchorElNav}
                    open={loginFormOpen}
                    onClose={handleLoginFormClose}
                    anchorOrigin={{
                        vertical: 'top',
                        horizontal: 'right',
                    }}
                    transformOrigin={{
                        vertical: 'top',
                        horizontal: 'right',
                    }}
                >
                    <Formik initialValues={initialValues}
                            onSubmit={onSubmit}
                            validationSchema={validationSchema}>
                        {(formikProps) => (
                            <Form onSubmit={formikProps.handleSubmit}>
                                <Box sx={{p: 2, mx: 2, maxWidth: '300px'}}>
                                    <Typography variant="h6"
                                                gutterBottom
                                    >
                                        Welcome!
                                    </Typography>
                                    <Field as={VidclickUiTextField}
                                           label="Email"
                                           variant="outlined"
                                           id="email"
                                           name="email"
                                           required
                                           fullWidth
                                           error={formikProps.touched.email && !!formikProps.errors.email}
                                           helperText={formikProps.touched.email && formikProps.errors.email}
                                    />
                                    <Field as={VidclickUiTextField}
                                           label="Password"
                                           variant="outlined"
                                           type="password"
                                           id="password"
                                           name="password"
                                           required
                                           fullWidth
                                           error={formikProps.touched.email && !!formikProps.errors.password}
                                           helperText={formikProps.touched.email && formikProps.errors.password}
                                    />
                                    <Link to={'/signup'}>
                                        <Typography fontSize={12}
                                                    mb={1}
                                                    color={'#1B1464'}
                                                    sx={{
                                                        textDecoration: 'underline',
                                                        '&:hover': {
                                                            cursor: 'pointer'
                                                        }
                                                    }}
                                        >
                                            Don't have an account yet?
                                        </Typography>
                                    </Link>
                                    <VidclickUiButton
                                        variant="contained"
                                        type="submit"
                                        disabled={!formikProps.touched.email || !!formikProps.errors.email || !!formikProps.errors.password}
                                        onClick={() => {
                                            handleLoginFormClose();
                                            handleLogin()
                                        }}
                                        fullWidth
                                    >
                                        Login
                                    </VidclickUiButton>
                                </Box>
                            </Form>
                        )}
                    </Formik>
                </Menu>
            </AppBar>
        </div>
    );
}