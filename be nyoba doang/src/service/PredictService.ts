import axios from "axios";
import fs from "fs";
import { StatusCodes } from "http-status-codes";
import path from "path";
import { ResponseError } from "../error/ResponseError";
import {
  PredictionResult,
  PredictRequest,
  PredictResponse,
} from "../model/PredictModel";
import { KainRepository } from "../repository/KainRepository";

export class PredictService {
  static async predict(req: PredictRequest): Promise<PredictResponse> {
    const roboflowUrl = process.env.ROBOFLOW_URL as string;
    const roboflowApiKey = process.env.ROBOFLOW_API_KEY as string;

    if (!roboflowUrl || !roboflowApiKey) {
      throw new ResponseError(
        StatusCodes.INTERNAL_SERVER_ERROR,
        "Internal Server Error"
      );
    }

    const filePath = req.filePath as string;

    const base64Image = fs.readFileSync(filePath, { encoding: "base64" });

    const roboflowResponse: any = await axios.post(
      `${roboflowUrl}`,
      base64Image,
      {
        params: {
          api_key: roboflowApiKey,
        },
        headers: {
          "Content-Type": "application/x-www-form-urlencoded",
        },
      }
    );

    console.log("Roboflow response:", roboflowResponse.status);

    if (roboflowResponse.status !== StatusCodes.OK) {
      throw new ResponseError(
        StatusCodes.INTERNAL_SERVER_ERROR,
        "Internal Server Error"
      );
    }

    const roboflowPred: any = roboflowResponse.data.predictions;

    if (fs.existsSync(filePath)) {
      fs.unlinkSync(filePath);
    }
    if (roboflowPred.length === 0 || roboflowPred.confidence < 0.5) {
      throw new ResponseError(
        StatusCodes.BAD_REQUEST,
        "This image does not contain any kain"
      );
    }

    const predictionRes: PredictionResult = {
      name: "",
      confidence: roboflowPred[0].confidence,
      class: roboflowPred[0].class,
      imagePath: "",
      description: roboflowPred[0].description || "",
    };

    const kainDir = process.env.KAIN_PATH as string;
    const imagePath = path
      .join(kainDir, predictionRes.class)
      .replace(/\\/g, "/");
    if (!fs.existsSync(imagePath)) {
      throw new ResponseError(
        StatusCodes.NOT_FOUND,
        "Image directory not found for this kain"
      );
    }
    const images = fs.readdirSync(imagePath);
    if (images.length === 0) {
      throw new ResponseError(
        StatusCodes.NOT_FOUND,
        "No images found for this kain"
      );
    }
    const randomImage = images[Math.floor(Math.random() * images.length)];
    predictionRes.imagePath = path
      .join(imagePath, randomImage)
      .replace(/\\/g, "/");

    const kain = await KainRepository.getByClass(predictionRes.class);

    if (!kain) {
      throw new ResponseError(StatusCodes.NOT_FOUND, "Kain not found");
    }

    predictionRes.name = kain.name;
    predictionRes.description = kain.description || predictionRes.description;
    const res: PredictResponse = {
      prediction: predictionRes,
    };

    console.log("Prediction result:", res);
    return res;
  }
}
