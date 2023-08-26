import { Link, LinkProps } from "react-router-dom";
import styled from "styled-components";
interface IVidclickUiLinkProps extends LinkProps {
    $textDecoration?: string;
    $color?: string;
    children: React.ReactNode;
}

const CustomizedLink = styled(Link)<IVidclickUiLinkProps>`
  text-decoration: ${(props) => props.$textDecoration ? props.$textDecoration : 'none'};
  color: ${(props) => props.$color ? props.$color : 'inherit'};
`;

export function VidclickUiLink(props: IVidclickUiLinkProps) {
    const { children, ...otherProps } = props;
    return (
        <div>
            <CustomizedLink {...otherProps}>
                {children}
            </CustomizedLink>
        </div>
    );
}
