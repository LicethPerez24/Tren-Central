package com.apiTren.crudJava.services;

import com.apiTren.crudJava.exception.ViajesNotFoundException;
import com.apiTren.crudJava.models.ViajesModel;
import com.apiTren.crudJava.repositories.ViajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class ViajesService {

    @Autowired
    private ViajesRepository viajesRepository;

    public List<ViajesModel> listarViajes() {
        return viajesRepository.findAll();
    }

    public ViajesModel obtenerViajePorId(Long id) {
        return viajesRepository.findById(id)
                .orElseThrow(() -> new ViajesNotFoundException("Viaje con ID " + id + " no encontrado"));
    }

    @Transactional
    public ViajesModel crearViaje(ViajesModel viajes) {
        return viajesRepository.save(viajes);
    }

    @Transactional
    public ViajesModel actualizarViaje(Long id, ViajesModel viajeActualizado) {
        if (!viajesRepository.existsById(id)) {
            throw new ViajesNotFoundException("Viaje con ID " + id + " no encontrado");
        }
        viajeActualizado.setId(id);
        return viajesRepository.save(viajeActualizado);
    }

    @Transactional
    public void eliminarViaje(Long id) {
        if (!viajesRepository.existsById(id)) {
            throw new ViajesNotFoundException("Viaje con ID " + id + " no encontrado");
        }
        viajesRepository.deleteById(id);
    }
}

