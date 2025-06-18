import "./config/env";
import express from "express";
import cors from "cors";
import { predictRouter } from "./router/PredictRouter";
import { kainRouter } from "./router/KainRouter";

const app = express();

app.use(express.urlencoded({ extended: true }));

app.use(
  cors({
    credentials: true,
    origin: [
      "http://localhost:3000",
    ],
  })
);

app.use(express.json());


app.use("/kain", express.static("kain"));

app.use("/api", predictRouter);
app.use("/api/kain", kainRouter);

const port = Number(process.env.PORT_SERVER) || 5000;

app.listen(port, "0.0.0.0", () => {
  console.log(`Server is running on port ${port}`);
});
