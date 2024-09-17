package org.example.infraestructure.repository;
import org.example.domain.Paciente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepositoryImpl implements PacienteRepository {
    private static final String FILE_NAME = "pacientes.txt";

    @Override
    public void save(Paciente paciente) {
        List<Paciente> pacientes = findAll();
        pacientes.removeIf(p -> p.getTelefono().equals(paciente.getTelefono()));
        pacientes.add(paciente);
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Paciente p : pacientes) {
                writer.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Paciente findByTelefono(String telefono) {
        List<Paciente> pacientes = findAll();
        return pacientes.stream()
                .filter(p -> p.getTelefono().equals(telefono))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pacientes.add(Paciente.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    @Override
    public void deleteByTelefono(String telefono) {
        List<Paciente> pacientes = findAll();
        pacientes.removeIf(p -> p.getTelefono().equals(telefono));
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Paciente p : pacientes) {
                writer.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

