package org.example.infraestructure.repository;
import org.example.domain.Paciente;
import java.util.List;

public interface PacienteRepository {
    void save(Paciente paciente);
    Paciente findByTelefono(String telefono);
    List<Paciente> findAll();
    void deleteByTelefono(String telefono);
}

