import { Request, Response } from "express";
import { StatusCodes } from "http-status-codes";
import { ResponseError } from "../error/ResponseError";
import { successResponse, errorResponse } from "../utils/api-response";
import { GetKainByClassRequest } from "../model/KainModel";
import { KainService } from "../service/KainService";

export class KainController {
  static async getSambalByClass(req: Request, res: Response) {
    try {
      const sambalReq: GetKainByClassRequest = {
        class: req.params.class,
      };
      const response = await KainService.getKainByClass(sambalReq);
      successResponse(res, StatusCodes.OK, "Success Getting Sambal", response);
    } catch (err) {
      if (err instanceof Error) {
        errorResponse(res, err);
      } else {
        errorResponse(res, new ResponseError(StatusCodes.INTERNAL_SERVER_ERROR, 'Internal Server Error'));
      }
    }
  }

  static async getAllSambal(req: Request, res: Response) {
    try {
      const response = await KainService.getAllKain();
      successResponse(res, StatusCodes.OK, "Success Getting All Sambal", response);
    } catch (err) {
      if (err instanceof Error) {
        errorResponse(res, err);
      } else {
        errorResponse(res, new ResponseError(StatusCodes.INTERNAL_SERVER_ERROR, 'Internal Server Error'));
      }
    }
  }
}
