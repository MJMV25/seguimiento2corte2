package org.example.infraestructure.repository;
import org.example.domain.Cita;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CitaRepositoryImpl implements CitaRepository {
    private static final String FILE_NAME = "citas.txt";

    @Override
    public void save(Cita cita) {
        List<Cita> citas = findAll();
        citas.removeIf(c -> c.getFecha().equals(cita.getFecha()) && c.getHora().equals(cita.getHora()));
        citas.add(cita);
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Cita c : citas) {
                writer.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cita> findByPacienteTelefono(String telefono) {
        List<Cita> citas = findAll();
        return citas.stream()
                .filter(c -> c.getPaciente().getTelefono().equals(telefono))
                .toList();
    }

    @Override
    public void delete(Cita cita) {
        List<Cita> citas = findAll();
        citas.removeIf(c -> c.getFecha().equals(cita.getFecha()) && c.getHora().equals(cita.getHora()));
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Cita c : citas) {
                writer.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cita> findAll() {
        List<Cita> citas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                citas.add(Cita.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return citas;
    }
}

