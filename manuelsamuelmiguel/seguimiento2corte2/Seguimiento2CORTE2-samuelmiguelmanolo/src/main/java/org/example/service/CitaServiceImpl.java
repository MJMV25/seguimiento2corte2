package org.example.service;
import org.example.domain.Cita;
import org.example.infraestructure.repository.CitaRepository;

import java.util.List;

public class CitaServiceImpl implements CitaService {
    private final CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public void save(Cita cita) {
        citaRepository.save(cita);
    }

    @Override
    public List<Cita> findByPacienteTelefono(String telefono) {
        return citaRepository.findByPacienteTelefono(telefono);
    }

    @Override
    public void delete(Cita cita) {
        citaRepository.delete(cita);
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }
}

