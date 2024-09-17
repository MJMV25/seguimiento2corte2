package org.example.service;
import org.example.domain.Cita;
import java.util.List;

public interface CitaService {
    void save(Cita cita);
    List<Cita> findByPacienteTelefono(String telefono);
    void delete(Cita cita);
    List<Cita> findAll();
}
