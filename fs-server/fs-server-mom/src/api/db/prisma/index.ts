import { FourSoulsDB } from "../../types";
import PrismaDB from "./PrismaDB";

const db: FourSoulsDB = new PrismaDB()

export { db }