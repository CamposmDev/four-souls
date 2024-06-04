import { $Enums } from "@prisma/client"

export type CreateUserBodyRes = {
    id: string,
    username: string
}

export type LoginUserBodyRes = {
    id: string,
    username: string
}

export type GetUserByIdBodyRes = {
    id: string,
    username: string,
    role: $Enums.Role
    createdAt: Date,
    updatedAt: Date
}