export type CreateChestBodyReq = {
    location: string,
    gate: number
}

export type JoinChestParams = {
    id: string
}

export type UnlockChestParams = {
    id: string
}

export type UnlockChestBodyReq = {
    key: string
}