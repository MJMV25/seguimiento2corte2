package org.example.infraestructure.repository;
import org.example.domain.Cita;
import java.util.List;

public interface CitaRepository {
    void save(Cita cita);
    List<Cita> findByPacienteTelefono(String telefono);
    void delete(Cita cita);
    List<Cita> findAll();
}

