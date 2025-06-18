import { Router } from "express";
import { KainController } from "../controller/KainController";

export const kainRouter = Router();

kainRouter.get("/class/:class", KainController.getKainByClass);
kainRouter.get("/", KainController.getAllKain);
