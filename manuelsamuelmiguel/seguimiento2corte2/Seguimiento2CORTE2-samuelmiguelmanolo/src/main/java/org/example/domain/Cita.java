package org.example.domain;
import java.io.Serializable;

public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fecha;
    private String hora;
    private String motivo;
    private Paciente paciente;

    // Constructores, getters y setters

    public Cita() {
    }

    public Cita(String fecha, String hora, String motivo, Paciente paciente) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.paciente = paciente;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    @Override
    public String toString() {
        return fecha + "," + hora + "," + motivo + "," + paciente.toString();
    }

    public static Cita fromString(String str) {
        String[] parts = str.split(",", 4);
        Paciente paciente = Paciente.fromString(parts[3]);
        return new Cita(parts[0], parts[1], parts[2], paciente);
    }
}
