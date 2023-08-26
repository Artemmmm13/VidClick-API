import {Avatar, Box, Card, CardContent, Container, Divider, Rating, ThemeProvider, Typography} from "@mui/material";
import theme from "../../../../app/providers/mui";
import {VidclickUiEditButton} from "./VidlickUiEditButton";


export function CreatorProfileCard() {
    return (
        <ThemeProvider theme={theme}>
            <Container maxWidth={"xl"}
                       sx={{display: 'flex', alignItems: 'center', justifyContent: 'center', my: 3}}
                       disableGutters>
                <Card sx={{minWidth: 500}}>
                    <CardContent sx={{
                        display: 'flex',
                        gap: 4
                    }}>
                        <Box sx={{
                            display: 'flex',
                            flexDirection: 'column',
                            alignItems: 'center',
                            gap: 1
                        }}>
                            <Avatar sx={{width: 96, height: 96}}
                                    src="/broken-image.jpg"/>
                            <Rating name="half-rating"
                                    defaultValue={2.5}
                                    precision={0.5}
                                    readOnly/>
                            <Box sx={{
                                display: 'flex',
                                flexDirection: 'column',
                                alignItems: 'center'
                            }}>
                                <Box sx={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'center'
                                }}>
                                    <Typography sx={{textTransform: 'uppercase', color: "lightgray", fontSize: "1em"}}>
                                        Your rating
                                    </Typography>
                                    <Typography>
                                        50%
                                    </Typography>
                                </Box>
                                <Box sx={{
                                    display: 'flex',
                                    flexDirection: 'column',
                                    alignItems: 'center'
                                }}>
                                    <Typography sx={{textTransform: 'uppercase', color: 'lightgray', fontSize: "1em"}}>
                                        Current proposals
                                    </Typography>
                                    <Typography>
                                        0
                                    </Typography>
                                </Box>
                            </Box>
                        </Box>
                        <Box sx={{display: 'flex', flexDirection: 'column'}}>
                            <Box sx={{display: 'flex', gap: 2}}>
                                <Box sx={{display: 'flex', flexDirection: 'column'}}>
                                    <Typography variant="h6">Alex Fresneda</Typography>
                                    <Typography variant="body2"
                                                sx={{color: 'lightgray'}}>Madrid, Spain</Typography>
                                </Box>
                                <VidclickUiEditButton/>
                            </Box>
                            <Divider sx={{marginTop: '1rem'}}/>
                            <Box sx={{display: 'flex', gap: 4, margin: 1, padding: 1}}>
                                <Box sx={{display: 'flex', flexDirection: 'column', gap: 2}}>
                                    <Typography variant='body1'
                                                sx={{color: 'lightgray'}}>Email</Typography>
                                    <Typography variant='body1'
                                                sx={{color: 'lightgray'}}>Town</Typography>
                                    <Typography variant='body1'
                                                sx={{color: 'lightgray'}}>Country</Typography>
                                    <Typography variant='body1'
                                                sx={{color: 'lightgray'}}>Raised</Typography>
                                </Box>
                                <Box sx={{display: 'flex', flexDirection: 'column', gap: 2}}>
                                    <Typography variant='body1'>fresneda@gmail.es</Typography>
                                    <Typography variant='body1'>Madrid</Typography>
                                    <Typography variant='body1'>Spain</Typography>
                                    <Typography variant='body1'>0€</Typography>
                                </Box>
                            </Box>
                        </Box>
                    </CardContent>
                </Card>
            </Container>
        </ThemeProvider>
    );
}