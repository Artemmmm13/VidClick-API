import {Tabs} from "@radix-ui/themes";
import {Link, Outlet} from "react-router-dom";
import styled from "styled-components";

export function ProfileNavigation() {

    const linkStyle = {
        textDecoration: 'none',
        color: 'inherit',
    };

    return (
        <div>
            <Tabs.Root defaultValue="account">
                <Tabs.List>
                    <Tabs.Trigger value="account">
                        <Link to={'/profile'} style={linkStyle}>
                            Account
                        </Link>
                    </Tabs.Trigger>
                    <Tabs.Trigger value="statistics">
                        <Link to={'./statistics'} style={linkStyle}>
                            Statistics
                        </Link>
                    </Tabs.Trigger>
                    <Tabs.Trigger value="settings">
                        <Link to={'./settings'} style={linkStyle}>
                            Settings
                        </Link>
                    </Tabs.Trigger>
                </Tabs.List>
            </Tabs.Root>
            <Outlet/>
        </div>
    )
        ;
}