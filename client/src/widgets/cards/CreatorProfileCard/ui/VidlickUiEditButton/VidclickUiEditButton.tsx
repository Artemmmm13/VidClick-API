import {Dialog, Button, Flex, Text, TextField} from "@radix-ui/themes";
import styled from "styled-components";
import {Pencil2Icon} from "@radix-ui/react-icons";

const StyledButton = styled(Button)`
  && {
    display: block;
    align-self: center;
  }
`

export function VidclickUiEditButton() {
    return (
        <Dialog.Root>
            <Dialog.Trigger>
                <StyledButton variant="soft" color="orange">
                    <Pencil2Icon width="16" height="16" /> Edit profile
                </StyledButton>
            </Dialog.Trigger>

            <Dialog.Content style={{maxWidth: 450}}>
                <Dialog.Title>Edit profile</Dialog.Title>
                <Dialog.Description size="2"
                                    mb="4">
                    Make changes to your profile.
                </Dialog.Description>

                <Flex direction="column"
                      gap="3">
                    <label>
                        <Text as="div"
                              size="2"
                              mb="1"
                              weight="bold">
                            Name
                        </Text>
                        <TextField.Input
                            defaultValue="Alex Fresneda"
                            placeholder="Enter your full name"
                        />
                    </label>
                    <label>
                        <Text as="div"
                              size="2"
                              mb="1"
                              weight="bold">
                            Email
                        </Text>
                        <TextField.Input
                            defaultValue="fresneda@example.com"
                            placeholder="Enter your email"
                        />
                    </label>
                    <label>
                        <Text as="div"
                              size="2"
                              mb="1"
                              weight="bold">
                            Phone number
                        </Text>
                        <TextField.Input
                            defaultValue="+37256960434"
                            placeholder="Enter your phone number"
                        />
                    </label>
                    <label>
                        <Text as="div"
                              size="2"
                              mb="1"
                              weight="bold">
                            Town
                        </Text>
                        <TextField.Input
                            defaultValue="Madrid"
                            placeholder="Enter your native town"
                        />
                    </label>
                    <label>
                        <Text as="div"
                              size="2"
                              mb="1"
                              weight="bold">
                            Country
                        </Text>
                        <TextField.Input
                            defaultValue="Spain"
                            placeholder="Enter your native country"
                        />
                    </label>
                </Flex>

                <Flex gap="3"
                      mt="4"
                      justify="end">
                    <Dialog.Close>
                        <Button variant="soft"
                                color="gray">
                            Cancel
                        </Button>
                    </Dialog.Close>
                    <Dialog.Close>
                        <Button>Save</Button>
                    </Dialog.Close>
                </Flex>
            </Dialog.Content>
        </Dialog.Root>
    );
}