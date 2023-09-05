import {Tabs} from "@radix-ui/themes";
import {Link, Outlet} from "react-router-dom";
import styled from "styled-components";

export function ProfileNavigation() {

    const CustomTabsTrigger = styled(Tabs.Trigger)`
      && {
        &[data-state='active']::before {
          background-color: #1B1464;
        }
      }
    `;

    const CustomTabsRoot = styled(Tabs.Root)`
        && {
          padding-top: 2em;
        }
    `
    const CustomTabsList = styled(Tabs.List)`
        && {
          justify-content: center;
        }
    `

    const linkStyle = {
        textDecoration: 'none',
        color: 'inherit',
    };

    return (
        <div>
            <CustomTabsRoot defaultValue="account">
                <CustomTabsList>
                    <CustomTabsTrigger value="account">
                        <Link to={'/profile'}
                              style={linkStyle}>
                            Account
                        </Link>
                    </CustomTabsTrigger>
                    <CustomTabsTrigger value="statistics">
                        <Link to={'./statistics'}
                              style={linkStyle}>
                            Statistics
                        </Link>
                    </CustomTabsTrigger>
                    <CustomTabsTrigger value="settings">
                        <Link to={'./settings'}
                              style={linkStyle}>
                            Settings
                        </Link>
                    </CustomTabsTrigger>
                </CustomTabsList>
            </CustomTabsRoot>
            <Outlet/>
        </div>
    )
        ;
}