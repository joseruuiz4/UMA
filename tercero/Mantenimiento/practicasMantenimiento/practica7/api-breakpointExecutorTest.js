/*
  Carlos Rodríguez Martín
  José Ruiz Pareja
*/

import http from "k6/http";
import { check } from "k6";

export const options = {
  scenarios: {
    break_test: {
      executor: "ramping-arrival-rate",
      preAllocatedVUs: 1000,
      maxVUs: 10000000,
      stages: [{ target: 100000, duration: "10m" }],
    },
  },
  thresholds: {
    http_req_failed: [
      {
        threshold: "rate<=0.01",
        abortOnFail: true,
      },
    ],
  },
};

export default function () {
  http.get("http://localhost:8080/medico/1");
}
