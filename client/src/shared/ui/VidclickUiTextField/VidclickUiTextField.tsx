import styled from "styled-components";
import { TextField, TextFieldProps } from "@mui/material";

type VidclickUiTextFieldProps = TextFieldProps & {
    $vdBorderColor?: string;
    $vdColor?: string;
};

const CustomizedTextField = styled(TextField)<VidclickUiTextFieldProps>`
  && {
    & .MuiOutlinedInput-root.Mui-focused fieldset {
      border-color: ${(props) => props.$vdBorderColor ? props.$vdBorderColor : '#1B1464'};
    }
    & .MuiInputLabel-root.Mui-focused {
      color:  ${(props) => props.$vdColor ? props.$vdColor : '#1B1464'};
    }
    margin-bottom: 1em;
  }
`;

export function VidclickUiTextField(props: VidclickUiTextFieldProps) {
    return <CustomizedTextField {...props} />;
}
