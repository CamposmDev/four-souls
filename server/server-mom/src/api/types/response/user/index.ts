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
    createdAt: Date,
    updatedAt: Date
}