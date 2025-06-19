/*
  Carlos Rodríguez Martín
  José Ruiz Pareja
*/

import http from "k6/http";

import { check, sleep } from "k6";

export const options = {
  stages: [
    { duration: "2m", target: 1917 },
    { duration: "1m", target: 0 },
  ],
  thresholds: {
    http_req_failed: [{ threshold: "rate<0.005", abortOnFail: true }],
  },
};

export default function () {
  const res = http.get("http://localhost:8080/medico/1");
  const success = check(res, {
    "status is 200": (r) => r.status === 200,
  });
  sleep(1);
}
