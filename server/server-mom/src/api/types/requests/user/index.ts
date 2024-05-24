type CreateUserBodyReq = {
    email: string
    username: string
    password: string
}

type LoginUserBodyReq = {
    username: string
    password: string
}

export {
    CreateUserBodyReq,
    LoginUserBodyReq
}