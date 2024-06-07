import { FastifyInstance } from "fastify";

export default class AppUtil {
    private _api: FastifyInstance

    constructor(api: FastifyInstance) {
        this._api = api
    }

    public get it() {
        return this._api
    }

    public async createUser(email: string, username: string, password: string) {
        const res = await this.it.inject({
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

    public async loginUser(username: string, password: string) {
        const res = await this.it.inject({
            method: "POST",
            url: "/api/user/login",
            body: {
                username: username,
                password: password
            }
        })
        return res
    }

    public async createBasement(ip: string, port: number, token: string) {
        const res = await this.it.inject({
            method: "POST",
            url: "/api/basement",
            body: {
                floor: ip,
                level: port
            },
            headers: {
                cookie: `token=${token}`
            }
        })
        return res
    }

    public async hostBasement(token: string) {
        const res = await this.it.inject({
            method: "POST",
            url: "/api/basement/host",
            headers: {
                cookie: `token=${token}`
            }
        })
        return res
    }

    public async joinBasement(basemnetId: string, token: string) {
        const res = await this.it.inject({
            method: "GET",
            url: `/api/basement/${basemnetId}/join`,
            headers: {
                cookie: `token=${token}`
            }
        })
        return res
    }
}