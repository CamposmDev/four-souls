import bcrypt from "bcrypt"

const SALT_ROUNDS = 10

export default class BcryptUtil {
    public async hash(key: string): Promise<string> {
        const salt = await bcrypt.genSalt(SALT_ROUNDS)
        const hash = await bcrypt.hash(key, salt)
        return hash
    }

    public async compare(key: string, hash: string): Promise<Boolean> {
        return await bcrypt.compare(key, hash)
    }
}