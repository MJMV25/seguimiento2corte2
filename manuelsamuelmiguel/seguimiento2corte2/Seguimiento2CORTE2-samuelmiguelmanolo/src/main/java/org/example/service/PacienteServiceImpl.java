package org.example.service;
import org.example.domain.Paciente;
import org.example.infraestructure.repository.PacienteRepository;

import java.util.List;

public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void save(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public Paciente findByTelefono(String telefono) {
        return pacienteRepository.findByTelefono(telefono);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public void deleteByTelefono(String telefono) {
        pacienteRepository.deleteByTelefono(telefono);
    }
}
