/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Regla;

import Home.Frm_diagnostico;
import Home.MDI_diagnostico;
import MDI.MDI_principal;
import controlador.Controlador_API_MDI;
import controlador.Controlador_api;

/**
 *
 * @author joimar
 */
public class regla {
    public static void main(String[] args) {
        Frm_diagnostico fd = new Frm_diagnostico();
        MDI_principal m = new MDI_principal();
        MDI_diagnostico frmd = new MDI_diagnostico();
        Controlador_api ctl = new Controlador_api(fd);
        Controlador_API_MDI api = new Controlador_API_MDI(m, frmd);
        api.iniciar();
        m.setVisible(true);
        frmd.setVisible(true);
        //MDI.iniciar(); 
        //m.setVisible(true);
        
        //ctl.iniciar();
        //fd.setVisible(true);
        
    }
}
