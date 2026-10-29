import http from "k6/http";
import { check, sleep } from "k6";
export const options = {
  scenarios: {
    breakpoint: {
      executor: "ramping-arrival-rate", // Incrementa la carga exponencial
      preAllocatedVUs: 1000, //VUs alocados inicialmente
      maxVUs: 1e7, //VUs maximo
      stages: [
        { duration: "5m", target: 100000 }, // just slowly ramp-up to a HUGE load
      ],
    },
  },
  thresholds: {
    http_req_failed: [
      {
        threshold: "rate<=0.05",
        abortOnFail: true,
      },
    ],
  },
};

export default () => {
  // define URL and payload
  const url = "http://localhost:8080/personas";

  // send a post request and save response as a variable
  const res = http.get(url);

  // Log the request body
  console.log(res.body);

  // check that response is 200
  check(res, {
    "response code was 200": (res) => res.status === 200,
  });
  sleep(1);
};
