export interface Kain {
  id: number;
  name: string;
  class: string;
  description: string;
  origin: string;
  century: number;
  type: string;
  meaning: string;
  pilosophy: string;
  dirUrl: string;
  history: string;
  createdAt: Date;
  updatedAt: Date;
}

export interface GetKainByClassRequest {
  class: string;
} 

export interface GetKainByClassResponse {
  kainData: Kain;
  pictures: string[];
}