package com.uma.example.springuma.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Cuenta;
@RestController
public class HelloController {
  

  List<Cuenta> listCuentas  = new ArrayList<>();
  
  @GetMapping("/hello")
  public String index() {
    return "Mantenimiento y Pruebas del Software, UMA";
  }

  @GetMapping("/get_cuenta")
  public Cuenta cuenta() {
    return new Cuenta(123);
  }

  @GetMapping("/nombre")
  public String hello(@RequestParam(value = "name", defaultValue = "cristian") String name) {
    return String.format("Hola alumno %s!", name);
  }

  @GetMapping("/nombre/{name}")
  public String get(@PathVariable("name") String name) {
    return String.format("Hola alumno has pasado el path %s!", name);
  }

  @PostMapping(value = "/addCuenta",  consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody Cuenta cuenta) {
    if (listCuentas.contains(cuenta))
      return ResponseEntity.internalServerError().body("La cuenta ya existe");
    else{
      listCuentas.add(cuenta);
      return ResponseEntity.ok().body("Una nueva cuenta se ha anyadido");

    }
	}

  @GetMapping("/list_cuentas")
  public List<Cuenta> cuentas() {
    return listCuentas;
  }

}
