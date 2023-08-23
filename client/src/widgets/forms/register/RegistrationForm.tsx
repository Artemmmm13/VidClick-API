import * as Yup from "yup"
import {IRegisterValues} from "../../../shared/models/IRegisterValues.ts";
import {Field, Form, Formik} from "formik";
import {VidclickUiTextField} from "../../../shared/ui/VidclickUiTextField";
import {VidclickUiButton} from "../../../shared/ui/VidclickUiButton";
import {Box, Container, CssBaseline, Grid, ThemeProvider, Typography} from "@mui/material";
import theme from "../../../app/providers/mui";
import {Link} from "react-router-dom";
import {VidclickUiCopyright} from "../../../shared/ui/VidclickUiCopyright";
import {VidclickUiIcon} from "../../../shared/ui/VidclickUiIcon";

export function RegistrationForm() {
    const validationSchema = Yup.object({
        name: Yup
            .string()
            .required('This field is required.'),
        email: Yup
            .string()
            .email('Incorrect email.')
            .required('This field is required.'),
        password: Yup.string()
            .min(8, 'Your password is too short. (min 8 symbols, at least 1 digit)')
            .matches(/[0-9]/, 'Your password lacks digit.')
            .required('This field is required.'),
        confirmPassword: Yup
            .string()
            .required('This field is required.')
            .oneOf([Yup.ref('password')], 'Your passwords do not match.')
    });

    const initialValues: IRegisterValues = {
        name: '',
        email: '',
        password: '',
        confirmPassword: ''
    }

    const onSubmit = (values: IRegisterValues) => {
        console.log(values)
    }

    return (
        <Formik initialValues={initialValues}
                onSubmit={onSubmit}
                validationSchema={validationSchema}>
            {(formikProps) => (
                <Form onSubmit={formikProps.handleSubmit}>
                    <ThemeProvider theme={theme}>
                        <Container component="main"
                                   maxWidth="xs">
                            <CssBaseline/>
                            <Box
                                sx={{
                                    marginTop: 8,
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'center',
                                }}
                            >
                                <VidclickUiIcon primary={false}/>
                                <Typography component="h1"
                                            variant="h5">
                                    Create your account
                                </Typography>
                                <Box sx={{mt: 3}}>
                                    <Grid container>
                                        <Grid item
                                              xs={12}>
                                            <Field as={VidclickUiTextField}
                                                   name="name"
                                                   required
                                                   fullWidth
                                                   id="name"
                                                   label="Name"
                                                   autoFocus
                                                   error={formikProps.touched.name && !!formikProps.errors.name}
                                                   helperText={formikProps.touched.name && formikProps.errors.name}
                                            />
                                        </Grid>
                                        <Grid item
                                              xs={12}>
                                            <Field as={VidclickUiTextField}
                                                   required
                                                   fullWidth
                                                   id="email"
                                                   label="Email"
                                                   name="email"
                                                   autoComplete="email"
                                                   error={formikProps.touched.email && !!formikProps.errors.email}
                                                   helperText={formikProps.touched.email && formikProps.errors.email}
                                            />
                                        </Grid>
                                        <Grid item
                                              xs={12}>
                                            <Field as={VidclickUiTextField}
                                                   required
                                                   fullWidth
                                                   name="password"
                                                   label="Password"
                                                   type="password"
                                                   id="password"
                                                   autoComplete="new-password"
                                                   error={formikProps.touched.password && !!formikProps.errors.password}
                                                   helperText={formikProps.touched.password && formikProps.errors.password}
                                            />
                                        </Grid>
                                        <Grid item
                                              xs={12}>
                                            <Field as={VidclickUiTextField}
                                                   required
                                                   fullWidth
                                                   name="confirmPassword"
                                                   label="Confirm your password"
                                                   type="password"
                                                   id="confirmPassword"
                                                   error={formikProps.touched.confirmPassword && !!formikProps.errors.confirmPassword}
                                                   helperText={formikProps.touched.confirmPassword && formikProps.errors.confirmPassword}
                                            />
                                        </Grid>
                                    </Grid>
                                    <VidclickUiButton type="submit"
                                                      fullWidth
                                                      variant="contained">Start a journey
                                    </VidclickUiButton>
                                    <Grid container
                                          justifyContent="flex-end"
                                          sx={{mt: 3}}>
                                        <Grid item>
                                            <Link to={'/login'}>
                                                <Typography
                                                    color="black"
                                                    sx={{
                                                        textDecoration: 'underline',
                                                        '&:hover': {
                                                            cursor: 'pointer'
                                                        }
                                                    }}
                                                >Already have account? Login</Typography>
                                            </Link>
                                        </Grid>
                                    </Grid>
                                </Box>
                            </Box>
                            <VidclickUiCopyright/>
                        </Container>
                    </ThemeProvider>
                </Form>
            )}
        </Formik>
    );
}