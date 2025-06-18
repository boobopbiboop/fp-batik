import fs from 'fs';
import path from 'path';
import { ResponseError } from '../error/ResponseError';
import { StatusCodes } from 'http-status-codes';
import { Validation } from "../utils/validation";
import { KainValidation } from '../validation/KainValidation';
import { GetKainByClassRequest, GetKainByClassResponse } from '../model/KainModel';
import { KainRepository } from '../repository/KainRepository';
import { Kain } from '../model/KainModel';

export class KainService {
  static async getKainByClass(request: GetKainByClassRequest): Promise<GetKainByClassResponse> {
    const data = Validation.validation(KainValidation.GET_BY_CLASS, request);

    const kain: Kain | null = await KainRepository.getByClass(data.class);

    if (!kain) {
      throw new ResponseError(StatusCodes.NOT_FOUND, "Kain not found");
    }

    const kainDir = process.env.KAIN_PATH as string;
    const imagePath = path.join(kainDir, kain.dirUrl).replace(/\\/g, '/');
    if (!fs.existsSync(imagePath)) {
      throw new ResponseError(StatusCodes.NOT_FOUND, "Image directory not found for this kain");
    }
    const images = fs.readdirSync(imagePath);
    if (images.length === 0) {
      throw new ResponseError(StatusCodes.NOT_FOUND, "No images found for this kain");
    }

    const randomImages = [];
    randomImages.push(path.join(imagePath, images[0]).replace(/\\/g, '/'));
    for (let i = 0; i < 3; i++) {
      const randomImage = images[Math.floor(Math.random() * images.length)];
      randomImages.push(path.join(imagePath, randomImage).replace(/\\/g, '/'));
    }

    return {
      kainData: kain,
      pictures: randomImages
    };
  }

  static async getAllKain(): Promise<Kain[]> {
    const kains = await KainRepository.getAll();
    if (!kains || kains.length === 0) {
      throw new ResponseError(StatusCodes.NOT_FOUND, "No kain found");
    }

    const kainDir = process.env.KAIN_PATH as string;
    for (const sambal of kains) {
      const imagePath = path.join(kainDir, sambal.dirUrl).replace(/\\/g, '/');
      const images = fs.readdirSync(imagePath);
      const randomImage = images[Math.floor(Math.random() * images.length)];
      sambal.dirUrl = path.join(imagePath, randomImage).replace(/\\/g, '/');
    }
    return kains;
  }
}