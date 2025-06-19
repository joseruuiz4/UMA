import http from "k6/http";
import { check, sleep } from "k6";

export const options = {
  stages: [
    { duration: "1m", target: 1500 }, // subimos a un carga promedio de 1500 vu en 5 minutos
    { duration: "2m", target: 1500 }, // nos mantenemos a 2000 por 30 minutos
    { duration: "1m", target: 0 }, // bajamos a 0 VU
  ],

  thresholds: {
    http_req_failed: ["rate<=0.01"], // tasa de error <= 1%
    http_req_duration: [
      "p(95)<=500", // 95% de las peticiones <= 500ms
      "p(99)<=1000", // 99% de las peticiones <= 1000ms
    ],
    checks: ["rate>=0.95"], // comprobaciones exitosas >= 95%
  },
};

export default function () {
  const url = "http://localhost:8080/personas";
  const res = http.get(url);

  // Log de la respuesta
  console.log(res.body);

  // Verificamos que el código de respuesta sea 200
  const success = check(res, {
    "response code was 200": (r) => r.status === 200,
  });

  sleep(1); // simula espera entre peticiones
}
