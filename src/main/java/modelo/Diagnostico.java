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

    private boolean dolor_lumbar;
    private boolean dolor_miccion;
    private boolean orina_con_sangre;
    private boolean fiebre;
    private boolean orina_frecuente;

    public Diagnostico(String nombre, String email) {
        super(nombre, email);
        this.dolor_lumbar = dolor_lumbar;
        this.dolor_miccion = dolor_miccion;
        this.orina_con_sangre = orina_con_sangre;
        this.fiebre = fiebre;
        this.orina_frecuente = orina_frecuente;
    }

    public Diagnostico(boolean dolor_lumbar, boolean dolor_miccion, boolean orina_con_sangre, boolean fiebre, boolean orina_frecuente, String nombre, String email) {
        super(nombre, email);
        this.dolor_lumbar = dolor_lumbar;
        this.dolor_miccion = dolor_miccion;
        this.orina_con_sangre = orina_con_sangre;
        this.fiebre = fiebre;
        this.orina_frecuente = orina_frecuente;
    }

    public boolean isDolor_lumbar() {
        return dolor_lumbar;
    }

    public void setDolor_lumbar(boolean dolor_lumbar) {
        this.dolor_lumbar = dolor_lumbar;
    }

    public boolean isDolor_miccion() {
        return dolor_miccion;
    }

    public void setDolor_miccion(boolean dolor_miccion) {
        this.dolor_miccion = dolor_miccion;
    }

    public boolean isOrina_con_sangre() {
        return orina_con_sangre;
    }

    public void setOrina_con_sangre(boolean orina_con_sangre) {
        this.orina_con_sangre = orina_con_sangre;
    }

    public boolean isFiebre() {
        return fiebre;
    }

    public void setFiebre(boolean fiebre) {
        this.fiebre = fiebre;
    }

    public boolean isOrina_frecuente() {
        return orina_frecuente;
    }

    public void setOrina_frecuente(boolean orina_frecuente) {
        this.orina_frecuente = orina_frecuente;
    }

    

    public String toJson() {
        return "{"
                + "\"nombre\": \"" + getNombre() + "\","
                + "\"email\": \"" + getEmail() + "\","
                + "\"hechos\": {"
                + "\"dolor_lumbar\": " + dolor_lumbar + ","
                + "\"dolor_miccion\": " + dolor_miccion + ","
                + "\"orina_con_sangre\": " + orina_con_sangre + ","
                + "\"fiebre\": " + fiebre + ","
                + "\"orina_frecuente\": " + orina_frecuente
                + "}"
                + "}";
    }
}
