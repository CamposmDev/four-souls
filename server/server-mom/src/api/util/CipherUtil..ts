import { randomBytes } from 'crypto'
import CryptoJS from 'crypto-js'

const CIPHER_KEY = process.env.CRYPTOJS_SECRET ? process.env.CRYPTOJS_SECRET : "secret"

export default class CryptoUtil {
    public encrypt(message: string | CryptoJS.lib.WordArray): string {
        return CryptoJS.AES.encrypt(message, CIPHER_KEY).toString()
    }

    public decrypt(ciphertext: string | CryptoJS.lib.CipherParams): string {
        return CryptoJS.AES.decrypt(ciphertext, CIPHER_KEY).toString(CryptoJS.enc.Utf8)
    }

    public generate(): string {
        return randomBytes(16).toString('hex')
    }
}