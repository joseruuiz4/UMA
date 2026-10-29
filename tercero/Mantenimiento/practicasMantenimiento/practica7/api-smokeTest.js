/*
  Carlos Rodríguez Martín
  José Ruiz Pareja
*/

import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  vus: 5,
  duration: "1m",
  thresholds: {
    http_req_failed: ["rate==0"],
    http_req_duration: ["avg<500"],
  },
  abortOnFail: true,
};

export default function () {
  const url = "http://localhost:8080/medico/1";
  const res = http.get(url);

  check(res, {
    "response code was 200": (r) => r.status === 200,
  });
}
