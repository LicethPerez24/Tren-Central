package com.apiTren.crudJava.controllers;


import com.apiTren.crudJava.exception.ViajesNotFoundException;
import com.apiTren.crudJava.models.ViajesModel;
import com.apiTren.crudJava.services.ViajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class ViajesController {

    @Autowired
    private ViajesService viajesService;

    @GetMapping ("viajes")
    public ResponseEntity<Map<String, Object>> listarViajes() {
        List<ViajesModel> viajes = viajesService.listarViajes();
        Map<String, Object> response = new HashMap<>();
        response.put("Message", "Lista de viajes obtenida con éxito");
        response.put("Data", viajes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("viaje/{id}")
    public ResponseEntity<Object> obtenerViajePorId(@PathVariable Long id) {
        try {
            ViajesModel viajes = viajesService.obtenerViajePorId(id);
            return new ResponseEntity<>(viajes, HttpStatus.OK);
        } catch (ViajesNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("viajes")
    public ResponseEntity<Object> crearViaje(@Validated @RequestBody ViajesModel viajes) {
        ViajesModel nuevoViaje = viajesService.crearViaje(viajes);
        return new ResponseEntity<>("Viaje creado con éxito: " + nuevoViaje.getId(), HttpStatus.CREATED);
    }

    @PutMapping("viaje/{id}")
    public ResponseEntity<Object> actualizarViaje(@PathVariable Long id, @Validated @RequestBody ViajesModel viajes) {
        try {
            ViajesModel actualizado = viajesService.actualizarViaje(id, viajes);
            return new ResponseEntity<>("Viaje actualizado con éxito: " + actualizado.getId(), HttpStatus.OK);
        } catch (ViajesNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("viaje/{id}")
    public ResponseEntity<Object> eliminarViaje(@PathVariable Long id) {
        try {
            viajesService.eliminarViaje(id);
            return new ResponseEntity<>("Viaje eliminado con éxito", HttpStatus.NO_CONTENT);
        } catch (ViajesNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } 
    }
}
