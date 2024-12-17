/**
 * 用户信息类别
 */
export type UserType = {
    id: number;
    username: string;
    userAccount: string;
    userAvatar?: string;
    gender: number;
    userStatus: number;
    phone: string;
    email: string;
    userRole: number;
    tags: string[];
    createTime: Date;
}
