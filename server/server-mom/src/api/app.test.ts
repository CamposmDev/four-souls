import { FastifyInstance, LightMyRequestResponse } from "fastify"
import build from "./fastify/app"
import { CreateUserBodyReq } from "api/types"

let api: FastifyInstance
beforeAll(async () => {
    api = build()
})

describe("User Module", () => {
    const testEmail0 = "example@email.com"
    const testName0 = "Camposm"
    const testPassword0 = "i_will_become_back_my_money"
    
    describe("Create User End Points", () => {
        test("User Create Endpoint: Correct Result", async () => {
            const res: LightMyRequestResponse = await api.inject({
                method: "POST",
                url: "/user",
                body: {
                    email: testEmail0,
                    name: testName0,
                    password: testPassword0
                }
            })
            expect(res.statusCode).toBe(200)
            const body = JSON.parse(res.body) as CreateUserBodyReq
        })
    })
})