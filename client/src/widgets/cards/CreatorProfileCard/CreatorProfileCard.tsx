import {Avatar, Card, CardContent, Container, ThemeProvider, Typography} from "@mui/material";
import theme from "../../../app/providers/mui";


export function CreatorProfileCard() {
    return (
        <ThemeProvider theme={theme}>
            <Container maxWidth={"xl"}
                       sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', my: 3}}
                       disableGutters>
                <Card sx={{maxWidth: 500}}>
                    <CardContent sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        flexDirection: 'column'
                    }}>
                        <Typography textAlign="center" variant="h5">
                            Your personal information
                        </Typography>
                        <Avatar sx={{width: 72, height: 72}} src="/broken-image.jpg" />
                    </CardContent>
                </Card>
            </Container>
        </ThemeProvider>
    );
}