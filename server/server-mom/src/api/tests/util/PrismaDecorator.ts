import { $Enums, User } from "@prisma/client";
import { PrismaClient } from "@prisma/client/extension";

export default class PrismaUtil {
    private _prisma: PrismaClient

    constructor(prisma: PrismaClient) {
        this._prisma = prisma
    }

    public get it() {
        return this._prisma
    }

    public async setUserAdmin(userId: string): Promise<User> {
        const user: User = await this.it.user.update({
            where: {
                id: userId
            },
            data: {
                role: $Enums.Role.ADMIN
            }
        })
        return user
    }

    public async deleteUserByUsername(username: string): Promise<void> {
        await this.it.user.deleteMany({
            where: {
                username: username
            }
        })
    }
    
    public async deleteBasementByIpPort(floor: string, level: number): Promise<void> {
        await this.it.basement.deleteMany({
            where:{
                floor: floor,
                level: level
            }
        })
    }
}