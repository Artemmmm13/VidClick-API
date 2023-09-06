import {Container, Typography} from "@mui/material";
import {VidclickUiCreateOfferButton} from "./VidclickUiCreateOfferButton";

export function CreateCardMenu() {
    return (
            <Container maxWidth={"xl"}
                       sx={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center'}}
                       disableGutters>
                <Typography mb={4}>Current fundraising offers: </Typography>
                <VidclickUiCreateOfferButton/>
            </Container>
    )
}