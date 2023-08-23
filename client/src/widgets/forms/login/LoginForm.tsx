import * as Yup from "yup"
import {ILoginValues} from "../../../shared/models/ILoginValues.ts";
import {Field, Form, Formik} from "formik";
import {Box, Container, CssBaseline, Grid, ThemeProvider, Typography} from "@mui/material";
import theme from "../../../app/providers/mui";
import {VidclickUiIcon} from "../../../shared/ui/VidclickUiIcon";
import {VidclickUiTextField} from "../../../shared/ui/VidclickUiTextField";
import {VidclickUiButton} from "../../../shared/ui/VidclickUiButton";
import {Link} from "react-router-dom";
import {VidclickUiCopyright} from "../../../shared/ui/VidclickUiCopyright";

export function LoginForm() {

    const validationSchema = Yup.object({
        email: Yup
            .string()
            .email('Incorrect email format.')
            .required('This field is required.'),
        password: Yup
            .string()
            .required('This field is required.'),
    });

    const initialValues: ILoginValues = {
        email: '',
        password: ''
    }

    const onSubmit = (values: ILoginValues) => {
        console.log(values)
    }

    return (
        <Formik initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={onSubmit}>
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
                                    Welcome back!
                                </Typography>
                                <Box sx={{mt: 3}}>
                                    <Grid container>
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
                                    </Grid>
                                    <VidclickUiButton type="submit"
                                                      fullWidth
                                                      variant="contained">Login
                                    </VidclickUiButton>
                                    <Grid container
                                          justifyContent="flex-end"
                                          sx={{mt: 3}}>
                                        <Grid item>
                                            <Link to={'/signup'}>
                                                <Typography
                                                    color="black"
                                                    sx={{
                                                        textDecoration: 'underline',
                                                        '&:hover': {
                                                            cursor: 'pointer'
                                                        }
                                                    }}
                                                >Do not have account? Sign Up</Typography>
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