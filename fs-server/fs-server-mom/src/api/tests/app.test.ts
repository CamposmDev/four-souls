import { LightMyRequestResponse } from "fastify"
import build from "../fastify/app"
import { PrismaClient } from "@prisma/client"
import { CreateUserBodyRes, HostBasementBodyRes, JoinBasementBodyRes, LoginUserBodyRes } from "types/response"
import AppDecorator from "./util/AppDecorator"
import PrismaDecorator from "./util/PrismaDecorator"

let app: AppDecorator
let prisma: PrismaDecorator

const testEmail0 = "example@email.com"
const testUsername0 = "Camposm"
const testPassword0 = "supersecret"
const testEmail1 = "dozer@email.com"
const testUsername1 = "Dozer"
const testPassword1 = "supersecret"
const JWT = 'token'
const testBasementIp1 = "localhost"
const testBasementPort0 = 3000

async function createUser0(): Promise<LightMyRequestResponse> {
    return await app.createUser(testEmail0, testUsername0, testPassword0)
}

async function createUser1() {
    return await app.createUser(testEmail1, testUsername1, testPassword1)
}

beforeAll(async () => {
    app = new AppDecorator(build("silent"))
    prisma = new PrismaDecorator(new PrismaClient())
})

describe("User Module", () => {    
    describe("Create User End Point", () => {
        afterEach(async () => {
            await prisma.deleteUserByUsername(testUsername0)
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
            const res = await app.createUser(testEmail0, "Camposm1", testPassword0)
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })

        test("Duplicate Username", async () => {
            await createUser0()
            const res = await app.createUser("example1@email.com", testUsername0, testPassword0)
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })

        test("Invalid Password", async () => {
            const res = await app.createUser(testEmail0, testUsername0, "secret")
            expect(res.statusCode).toBe(400)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined
        })
    })

    describe("Login User End Point", () => {
        afterEach(async () => {
            await prisma.deleteUserByUsername(testUsername0)
        })
        test("Correct Result", async () => {
            await createUser0()
            const res = await app.loginUser(testUsername0, testPassword0)
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
        /* TODO - implement me */
    })
})

describe("Basement Module", () => {
    beforeAll(async () => {
        /* create user 0 and set as admin */
        const res = await createUser0();
        const body = JSON.parse(res.body)
        await prisma.setUserAdmin(body.id)
        /* create user 1 */
        await createUser1()
    })

    afterAll(async () => {
        await prisma.deleteUserByUsername(testUsername0)
        await prisma.deleteUserByUsername(testUsername1)
    })
    
    describe("Create Basement End Point", () => {
        afterEach(async () => {
            /* delete basement after each test */
            await prisma.deleteBasementByIpPort(testBasementIp1, testBasementPort0)
        })
        test("Correct Result", async () => {
            const loginRes = await app.loginUser(testUsername0, testPassword0)
            const token = loginRes.cookies.find(x => x.name === JWT)
            if (!token) throw Error("Missing JWT")
            const res = await app.createBasement(testBasementIp1, testBasementPort0, token.value)
            expect(res.statusCode).toBe(201)
            const body = JSON.parse(res.body)
            expect(body).toBeDefined()
            expect(body.message).toBeDefined()
        })
    })

    describe("Host Basement End Point", () => {
        afterEach(async () => {
            /* delete basement after each test */
            await prisma.deleteBasementByIpPort(testBasementIp1, testBasementPort0)
        })
        test("Correct Result", async () => {
            const loginRes = await app.loginUser(testUsername0, testPassword0)
            const token = loginRes.cookies.find(x => x.name === JWT)
            if (!token) throw Error("Missing JWT")
            await app.createBasement(testBasementIp1, testBasementPort0, token.value)
            const res = await app.hostBasement(token.value);
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
        beforeAll(async () => {
            /* create basement */
            const loginRes = await app.loginUser(testUsername0, testPassword0)
            const token = loginRes.cookies.find(x => x.name === JWT)
            if (!token) throw new Error("Missing JWT")
            await app.createBasement(testBasementIp1, testBasementPort0, token.value)
        })

        afterAll(async () => {
            /* delete basement */
            await prisma.deleteBasementByIpPort(testBasementIp1, testBasementPort0)
        })

        test("Correct Result", async() => {
            /* login as user0 */
            const loginRes0 = await app.loginUser(testUsername0, testPassword0)
            const token0 = loginRes0.cookies.find(x => x.name === JWT)
            if (!token0) throw Error("Missing JWT")
            /* request to host a basement */
            const hostRes0 = await app.hostBasement(token0.value)
            const hostBody0 = JSON.parse(hostRes0.body) as HostBasementBodyRes
            /* acquire basementId */
            const basementId = hostBody0.id
            /* login as user1 */
            const loginRes1 = await app.loginUser(testUsername1, testPassword1)
            const token1 = loginRes1.cookies.find(x => x.name === JWT)
            if (!token1) throw Error("Missing JWT")
            /* request to join a basement */
            const joinRes1 = await app.joinBasement(basementId, token1.value)
            expect(joinRes1.statusCode).toBe(200)
            const joinBody1 = JSON.parse(joinRes1.body) as JoinBasementBodyRes
            expect(joinBody1).toBeDefined()
            expect(joinBody1.id).toBeDefined()
            expect(joinBody1.floor).toBeDefined()
            expect(joinBody1.level).toBeDefined()
        })
    })
})