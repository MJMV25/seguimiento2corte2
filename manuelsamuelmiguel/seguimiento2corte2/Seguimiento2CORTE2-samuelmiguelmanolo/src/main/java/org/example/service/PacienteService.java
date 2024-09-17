package org.example.service;
import org.example.domain.Paciente;
import java.util.List;

public interface PacienteService {
    void save(Paciente paciente);
    Paciente findByTelefono(String telefono);
    List<Paciente> findAll();
    void deleteByTelefono(String telefono);
}

