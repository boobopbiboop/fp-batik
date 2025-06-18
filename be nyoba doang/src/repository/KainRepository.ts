import db from "../config/database";
import { Kain } from '../model/KainModel';

export class KainRepository {
  static async getByName(name: string) : Promise<Kain | null> {
    return db.kain.findUnique({
      where: {
        name: name
      },
    });
  }

  static async getAll(): Promise<Kain[]> {
    return db.kain.findMany({
      orderBy: {
        name: 'asc'
      }
    });
  }

  static async getByClass(kainClass: string): Promise<Kain | null> {
    return db.kain.findFirst({
      where: {
        class: kainClass
      }
    });
  }
}