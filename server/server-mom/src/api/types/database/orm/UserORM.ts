import { Prisma, User } from "@prisma/client";

export default interface UserORM {
    create(data: Prisma.UserCreateInput): Promise<User>
    getById(id: string): Promise<User | null>
    getByUsername(username: string): Promise<User>
    isEmailTaken(email: string): Promise<Boolean>
    isUsernameTaken(username: string): Promise<Boolean>
    deleteByUsername(username: string): Promise<User | null>
}