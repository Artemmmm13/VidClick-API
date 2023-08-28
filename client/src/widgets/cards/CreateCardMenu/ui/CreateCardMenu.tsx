import {Container, ThemeProvider, Typography} from "@mui/material";
import theme from "../../../../app/providers/mui";
import {VidclickUiCreateOfferButton} from "./VidclickUiCreateOfferButton";

export function CreateCardMenu() {
    return (
        <ThemeProvider theme={theme}>
            <Container maxWidth={"xl"}
                       sx={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}
                       disableGutters>
                <Typography mb={4}>Current fundraising offers: </Typography>
                <VidclickUiCreateOfferButton/>
            </Container>
        </ThemeProvider>
    )
}