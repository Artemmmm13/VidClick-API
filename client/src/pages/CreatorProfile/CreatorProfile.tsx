import {CreatorProfileCard} from "../../widgets/cards/CreatorProfileCard/ui";
import {CreateCardMenu} from "../../widgets/cards/CreateCardMenu/ui";
import {VidclickUiCard} from "../../shared/ui/VidclickUiCard";


export function CreatorProfile() {
    return (
        <div>
            <CreatorProfileCard/>
            <CreateCardMenu/>
            <VidclickUiCard/>
        </div>
    );
}