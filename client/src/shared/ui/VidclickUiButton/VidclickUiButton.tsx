import styled from "styled-components";
import {Button, ButtonProps} from "@mui/material";

interface VidclickUiButtonProps extends ButtonProps {
    children: React.ReactNode;
    $vdBackgroundColor?: string;
    $vdColor?: string;
    $vdBackgroundColorHover?: string;
}

const CustomizedButton = styled(Button)<VidclickUiButtonProps>`
  && {
    background-color: ${(props) => props.$vdBackgroundColor ? props.$vdBackgroundColor : "#1B1464"};
    color: ${(props) => (props.$vdColor ? props.$vdColor : "#FFFFFF")};
    &:hover {
      background-color: ${(props) => 
              props.$vdBackgroundColorHover ? props.$vdBackgroundColorHover : `rgba(199, 199, 199, 0.6)`};
    }
  }
`;

export function VidclickUiButton(props: VidclickUiButtonProps) {
    const {children, ...otherProps} = props;
    return (
        <CustomizedButton {...otherProps}>{children}</CustomizedButton>
    );
}