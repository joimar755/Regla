/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regla;

import Home.Frm_diagnostico;
import controlador.Controlador_api;

/**
 *
 * @author joimar
 */
public class regla {
    public static void main(String[] args) {
        Frm_diagnostico fd = new Frm_diagnostico();
        Controlador_api ctl = new Controlador_api(fd);
        ctl.iniciar();
        fd.setVisible(true);
    }
}
