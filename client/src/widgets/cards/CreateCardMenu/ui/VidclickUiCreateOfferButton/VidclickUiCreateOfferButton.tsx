import {Dialog, Button, Flex, Text, TextField} from "@radix-ui/themes";
import {PlusCircledIcon} from "@radix-ui/react-icons";
import styled from "styled-components";

const StyledButton = styled(Button)`
  && {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 500px;
    height: 200px;
    gap: 4px;
    font-size: 1.5rem;
    opacity: .7;
  }
`;

const StyledIcon = styled(PlusCircledIcon)`
    && {
      width: 48px;
      height: 48px;
    }
`

export function VidclickUiCreateOfferButton() {
    return (
        <Dialog.Root>
            <Dialog.Trigger>
                    <StyledButton variant="soft">
                        <StyledIcon/>
                        Create new proposal
                    </StyledButton>
            </Dialog.Trigger>

            <Dialog.Content style={{ maxWidth: 450 }}>
                <Dialog.Title>Create your new proposal</Dialog.Title>
                <Flex direction="column" gap="3">
                    <label>
                        <Text as="div" size="2" mb="1" weight="bold">
                            Name
                        </Text>
                        <TextField.Input
                            defaultValue="Freja Johnsen"
                            placeholder="Enter your full name"
                        />
                    </label>
                    <label>
                        <Text as="div" size="2" mb="1" weight="bold">
                            Email
                        </Text>
                        <TextField.Input
                            defaultValue="freja@example.com"
                            placeholder="Enter your email"
                        />
                    </label>
                </Flex>

                <Flex gap="3" mt="4" justify="end">
                    <Dialog.Close>
                        <Button variant="soft" color="gray">
                            Cancel
                        </Button>
                    </Dialog.Close>
                    <Dialog.Close>
                        <Button>Save</Button>
                    </Dialog.Close>
                </Flex>
            </Dialog.Content>
        </Dialog.Root>
    )
}