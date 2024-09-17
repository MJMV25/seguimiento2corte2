package org.example.aplication;
import org.example.domain.Paciente;
import org.example.domain.Cita;
import org.example.service.PacienteService;
import org.example.service.PacienteServiceImpl;
import org.example.service.CitaService;
import org.example.service.CitaServiceImpl;
import org.example.infraestructure.repository.PacienteRepository;
import org.example.infraestructure.repository.PacienteRepositoryImpl;
import org.example.infraestructure.repository.CitaRepository;
import org.example.infraestructure.repository.CitaRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteService pacienteService;
    private static final CitaService citaService;

    static {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        pacienteService = new PacienteServiceImpl(pacienteRepository);

        CitaRepository citaRepository = new CitaRepositoryImpl();
        citaService = new CitaServiceImpl(citaRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Registrar nuevo paciente");
            System.out.println("2. Actualizar datos del paciente");
            System.out.println("3. Registrar nueva cita");
            System.out.println("4. Eliminar cita");
            System.out.println("5. Mostrar lista de pacientes");
            System.out.println("6. Mostrar citas de un paciente");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    actualizarPaciente();
                    break;
                case 3:
                    registrarCita();
                    break;
                case 4:
                    eliminarCita();
                    break;
                case 5:
                    mostrarPacientes();
                    break;
                case 6:
                    mostrarCitas();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void registrarPaciente() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        Paciente paciente = new Paciente(nombre, apellido, edad, genero, direccion, telefono);
        pacienteService.save(paciente);
        System.out.println("Paciente registrado exitosamente.");
    }

    private static void actualizarPaciente() {
        System.out.print("Teléfono del paciente a actualizar: ");
        String telefono = scanner.nextLine();
        Paciente paciente = pacienteService.findByTelefono(telefono);
        if (paciente != null) {
            System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
            String nombre = scanner.nextLine();
            if (!nombre.isEmpty()) paciente.setNombre(nombre);
            System.out.print("Nuevo apellido (dejar en blanco para no cambiar): ");
            String apellido = scanner.nextLine();
            if (!apellido.isEmpty()) paciente.setApellido(apellido);
            System.out.print("Nueva edad (dejar en blanco para no cambiar): ");
            String edadStr = scanner.nextLine();
            if (!edadStr.isEmpty()) paciente.setEdad(Integer.parseInt(edadStr));
            System.out.print("Nuevo género (dejar en blanco para no cambiar): ");
            String genero = scanner.nextLine();
            if (!genero.isEmpty()) paciente.setGenero(genero);
            System.out.print("Nueva dirección (dejar en blanco para no cambiar): ");
            String direccion = scanner.nextLine();
            if (!direccion.isEmpty()) paciente.setDireccion(direccion);

            pacienteService.save(paciente);
            System.out.println("Paciente actualizado exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private static void registrarCita() {
        System.out.print("Teléfono del paciente para la cita: ");
        String telefono = scanner.nextLine();
        Paciente paciente = pacienteService.findByTelefono(telefono);
        if (paciente != null) {
            System.out.print("Fecha (dd/MM/yyyy): ");
            String fecha = scanner.nextLine();
            System.out.print("Hora (HH:mm): ");
            String hora = scanner.nextLine();
            System.out.print("Motivo de la cita: ");
            String motivo = scanner.nextLine();

            Cita cita = new Cita(fecha, hora, motivo, paciente);
            citaService.save(cita);
            System.out.println("Cita registrada exitosamente.");
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private static void eliminarCita() {
        System.out.print("Ingrese fecha de la cita a eliminar (dd/MM/yyyy): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese hora de la cita a eliminar (HH:mm): ");
        String hora = scanner.nextLine();

        List<Cita> citas = citaService.findAll();
        Cita cita = citas.stream()
                .filter(c -> c.getFecha().equals(fecha) && c.getHora().equals(hora))
                .findFirst()
                .orElse(null);

        if (cita != null) {
            citaService.delete(cita);
            System.out.println("Cita eliminada exitosamente.");
        } else {
            System.out.println("Cita no encontrada.");
        }
    }

    private static void mostrarPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Lista de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        }
    }

    private static void mostrarCitas() {
        System.out.print("Teléfono del paciente: ");
        String telefono = scanner.nextLine();
        Paciente paciente = pacienteService.findByTelefono(telefono);
        if (paciente != null) {
            List<Cita> citas = citaService.findByPacienteTelefono(telefono);
            if (citas.isEmpty()) {
                System.out.println("No hay citas registradas para el paciente.");
            } else {
                System.out.println("Citas de " + paciente.getNombre() + " " + paciente.getApellido() + ":");
                for (Cita cita : citas) {
                    System.out.println(cita);
                }
            }
        } else {
            System.out.println("Paciente no encontrado.");
        }
    }
}

