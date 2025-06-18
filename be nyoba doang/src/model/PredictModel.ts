export interface PredictRequest {
  filePath: string;
}

export interface PredictResponse {
  prediction: PredictionResult
}

export interface PredictionResult {
  name: string;
  confidence: number;
  class: string;
  imagePath: string;
  description?: string;
}
