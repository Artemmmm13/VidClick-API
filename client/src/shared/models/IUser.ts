export enum UserRole {
    USER = 'user',
    CREATOR = 'creator'
}
export interface IUser {
    id: string;
    name: string;
    email: string;
    password: string;
    creatorProfileImage: string;
    role: UserRole;
    accountCreatedOn: number;
}