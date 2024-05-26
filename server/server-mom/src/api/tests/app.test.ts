import { FastifyInstance, LightMyRequestResponse } from "fastify"
import build from "../fastify/app"
import { $Enums, PrismaClient, User } from "@prisma/client"
import { CreateUserBodyRes, HostBasementBodyRes, LoginUserBodyRes } from "types/response"
import { execPath } from "process"

let api: FastifyInstance
let prisma: PrismaClient

const testEmail0 = "example@email.com"
const testUsername0 = "Camposm"
const testPassword0 = "supersecret"
const testEmail1 = "sample@email.com"
const testUsername1 = "Dozer"
const testPassword1 = "supersecret"
const JWT: string = 'token'
const testBasementIp1 = "localhost"
const testBasementPort0 = 3000

export async function createUser(email: string, username: string, password: string): Promise<LightMyRequestResponse> {
    const res = await api.inject({
        method: "POST",
        url: "/api/user",
        body: {
            email: email,
            username: username,
            password: password
        }
    })
    return res
}

async function createUser0(): Promise<LightMyRequestResponse> {
    return await createUser(testEmail0, testUsername0, testPassword0)
}

async function setUserAdmin(userId: string): Promise<User> {
    const user: User = await prisma.user.update({
        where: {
            id: userId
        },
        data: {
            role: $Enums.Role.ADMIN
        }
    })
    return user
}

async function loginUser(username: string, password: string): Promise<LightMyRequestResponse> {
    const res = await api.inject({
        method: "POST",
        url: "/api/user/login",
        body: {
            username: username,
            password: password
        }
    })
    return res
}

async function deleteUserByUsername(username: string) {
    await prisma.user.deleteMany({
        where: {
            username: username
        }
    })
}

async function createBasement(ip: string, port: number, token: string): Promise<LightMyRequestResponse> {
    const res = await api.inject({
        method: "POST",
        url: "/api/basement",
        body: {
            ip: ip,
            port: port
        },
        headers: {
            cookie: `token=${token}`
        }
    })
    return res
}

async function hostBasement(token: string): Promise<LightMyRequestResponse> {
    const res = await api.inject({
        method: "POST",
        url: "/api/basement/host",
        headers: {
            cookie: `token=${token}`
        }
    })
    return res
}

async function deleteBasementByIpAndPort(ip: string, port: number) {
    await prisma.basement.deleteMany({
        where:{
            floor: ip,
            level: port
        }
    })
}

beforeAll(async () => {
    api = build("silent")
    prisma = new PrismaClient()
})

describe("User Module", () => {    
    describe("Create User End Point", () => {
        afterEach(async () => {
            await prisma.user.deleteMany({
                where: {
                    username: testUsername0
                }
            })
        })
        test("Correct Result", async () => {
            const res = await createUser0()
            expect(res.statusCode).toBe(201)
            const body = JSON.parse(res.body) as CreateUserBodyRes
            expect(body).toBeDefined()
            expect(body.id).toBeDefined()
            expect(body.username).toBe(testUsername0)
        })

        test("Duplicate Email", async() => {
            await createUser0()
            const res = await createUser(testEmail0, "Camposm1", testPassword0)
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })

        test("Duplicate Username", async () => {
            await createUser0()
            const res = await createUser("example1@email.com", testUsername0, testPassword0)
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })

        test("Invalid Password", async () => {
            const res = await createUser(testEmail0, testUsername0, "secret")
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined
        })
    })

    describe("Login User End Point", () => {
        afterEach(async () => {
            await deleteUserByUsername(testUsername0)
        })
        test("Correct Result", async () => {
            await createUser0()
            const res = await loginUser(testUsername0, testPassword0)
            expect(res.statusCode).toBe(200)
            const body = JSON.parse(res.body) as LoginUserBodyRes
            expect(body).toBeDefined()
            expect(body.id).toBeDefined()
            expect(body.username).toBeDefined()
            expect(body.username).toBe(testUsername0)
            const cookie = res.cookies.find(x => x.name === JWT)
            expect(cookie).toBeDefined()
            expect(cookie?.value).toBeDefined()
        })
    })

    describe("Logout User End Point", () => {

    })
})

describe("Basement Module", () => {
    beforeAll(async () => {
        const res = await createUser0();
        const body = JSON.parse(res.body)
        await setUserAdmin(body.id)
    })

    afterAll(async () => {
        await deleteUserByUsername(testUsername0)
    })

    afterEach(async () => {
        await deleteBasementByIpAndPort(testBasementIp1, testBasementPort0)
    })
    
    describe("Create Basement End Point", () => {
        test("Correct Result", async () => {
            const loginRes = await loginUser(testUsername0, testPassword0)
            const token = loginRes.cookies.find(x => x.name === JWT)
            if (!token) throw Error("Missing JWT")
            const res = await createBasement(testBasementIp1, testBasementPort0, token.value)
            expect(res.statusCode).toBe(201)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })
    })

    describe("Host Basement End Point", () => {
        test("Correct Result", async () => {
            const loginRes = await loginUser(testUsername0, testPassword0)
            const token = loginRes.cookies.find(x => x.name === JWT)
            if (!token) throw Error("Missing JWT")
            await createBasement(testBasementIp1, testBasementPort0, token.value)
            const res = await hostBasement(token.value);
            expect(res.statusCode).toBe(200)
            const body = JSON.parse(res.body) as HostBasementBodyRes
            expect(body).toBeDefined()
            expect(body.id).toBeDefined()
            expect(body.key).toBeDefined()
            expect(body.floor).toBeDefined()
            expect(body.level).toBeDefined()
        })
    })
    

    describe("Join Basement End Point", () => {

    })
})