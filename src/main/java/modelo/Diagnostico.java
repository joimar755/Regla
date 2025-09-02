/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author joimar
 */
public class Diagnostico extends Paciente {

    private boolean dolorAbdominal;
    private boolean nauseas;
    private boolean vomito;
    private boolean diarrea;
    private boolean fiebre;

    public Diagnostico(String nombre, String email) {
        super(nombre, email);
        this.dolorAbdominal = dolorAbdominal;
        this.nauseas = nauseas;
        this.vomito = vomito;
        this.diarrea = diarrea;
        this.fiebre = fiebre;
    }

    public Diagnostico(boolean dolorAbdominal, boolean nauseas, boolean vomito, boolean diarrea, boolean fiebre, String nombre, String email) {
        super(nombre, email);
        this.dolorAbdominal = dolorAbdominal;
        this.nauseas = nauseas;
        this.vomito = vomito;
        this.diarrea = diarrea;
        this.fiebre = fiebre;
    }

    public boolean isDolorAbdominal() {
        return dolorAbdominal;
    }

    public void setDolorAbdominal(boolean dolorAbdominal) {
        this.dolorAbdominal = dolorAbdominal;
    }

    public boolean isNauseas() {
        return nauseas;
    }

    public void setNauseas(boolean nauseas) {
        this.nauseas = nauseas;
    }

    public boolean isVomito() {
        return vomito;
    }

    public void setVomito(boolean vomito) {
        this.vomito = vomito;
    }

    public boolean isDiarrea() {
        return diarrea;
    }

    public void setDiarrea(boolean diarrea) {
        this.diarrea = diarrea;
    }

    public boolean isFiebre() {
        return fiebre;
    }

    public void setFiebre(boolean fiebre) {
        this.fiebre = fiebre;
    }

    public String toJson() {
        return "{"
                + "\"nombre\": \"" + getNombre() + "\","
                + "\"email\": \"" + getEmail() + "\","
                + "\"hechos\": {"
                + "\"dolor_abdominal\": " + dolorAbdominal + ","
                + "\"nauseas\": " + nauseas + ","
                + "\"vomito\": " + vomito + ","
                + "\"diarrea\": " + diarrea + ","
                + "\"fiebre\": " + fiebre
                + "}"
                + "}";
    }
}
