import './styles.css';
import React, {useEffect, useState} from "react";
import {styled} from '@mui/material/styles';
import Container from '@mui/material/Container'
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import IconButton, {IconButtonProps} from '@mui/material/IconButton';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import * as DropdownMenu from '@radix-ui/react-dropdown-menu';
import * as Progress from '@radix-ui/react-progress';
import {
    DotsVerticalIcon
} from '@radix-ui/react-icons';

interface ExpandMoreProps extends IconButtonProps {
    expand: boolean;
}

const ExpandMore = styled((props: ExpandMoreProps) => {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const {expand, ...other} = props;
    return <IconButton {...other} />;
})(({theme, expand}) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
        duration: theme.transitions.duration.shortest,
    }),
}));


export function VidclickUiCard() {
    const [expanded, setExpanded] = useState(false);

    const handleExpandClick = () => {
        setExpanded(!expanded);
    };

    const [progress, setProgress] = useState(13);

    useEffect(() => {
        const timer = setTimeout(() => setProgress(66), 500);
        return () => clearTimeout(timer);
    }, []);


    return (
        <Container maxWidth={"xl"}
                   sx={{display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', my: 2}}
                   disableGutters>
            <Card sx={{maxWidth: 345}}>
                <CardHeader
                    action={
                        <IconButton aria-label="settings">
                            <DropdownMenu.Root>
                                <DropdownMenu.Trigger asChild>
                                    <button className="IconButton" aria-label="Customise options">
                                        <DotsVerticalIcon />
                                    </button>
                                </DropdownMenu.Trigger>

                                <DropdownMenu.Portal>
                                    <DropdownMenu.Content className="DropdownMenuContent" sideOffset={5}>
                                        <DropdownMenu.Item className="DropdownMenuItem">
                                            Update
                                        </DropdownMenu.Item>
                                        <DropdownMenu.Item className="DropdownMenuItem">
                                            Remove
                                        </DropdownMenu.Item>
                                    </DropdownMenu.Content>
                                </DropdownMenu.Portal>
                            </DropdownMenu.Root>
                        </IconButton>
                    }
                    title="Title"
                    subheader="Date"
                />
                <CardMedia
                    component="img"
                    height="194"
                    image="/static/images/cards/paella.jpg"
                    alt="Image"
                />
                <CardContent>
                    <Progress.Root className="ProgressRoot" value={progress}>
                        <Progress.Indicator
                            className="ProgressIndicator"
                            style={{ transform: `translateX(-${100 - progress}%)` }}
                        />
                    </Progress.Root>
                </CardContent>
                <CardActions disableSpacing>
                    <ExpandMore
                        expand={expanded}
                        onClick={handleExpandClick}
                        aria-expanded={expanded}
                        aria-label="show more"
                    >
                        <ExpandMoreIcon/>
                    </ExpandMore>
                </CardActions>
                <Collapse in={expanded}
                          timeout="auto"
                          unmountOnExit>
                    <CardContent>
                        Description
                    </CardContent>
                </Collapse>
            </Card>
        </Container>
    );
}
