import { Router } from "express";
import { KainController } from "../controller/KainController";

export const kainRouter = Router();

kainRouter.get("/class/:class", KainController.getSambalByClass);
kainRouter.get("/", KainController.getAllSambal);
