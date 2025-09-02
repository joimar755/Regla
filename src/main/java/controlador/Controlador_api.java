/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Home.Frm_diagnostico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Diagnostico;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author joimar
 */
public class Controlador_api implements ActionListener {

    private Frm_diagnostico vista_diagnostico;

    public Controlador_api(Frm_diagnostico vista_diagnostico) {
        this.vista_diagnostico = vista_diagnostico;
        this.vista_diagnostico.Btn_diagnostico.addActionListener(this);
        this.vista_diagnostico.Btn_Mostrar.addActionListener(this);
        // Llenar combobox con "Sí" y "No"
        JComboBox<String>[] comboboxes = new JComboBox[]{
            vista_diagnostico.combobox1, vista_diagnostico.combobox2, vista_diagnostico.combobox3, vista_diagnostico.combobox4, vista_diagnostico.combobox5
        };

        // Llenar cada combobox con "Sí" y "No"
        for (JComboBox<String> cb : comboboxes) {
            cb.addItem("Sí");
            cb.addItem("No");
        }
        DefaultTableModel modelo = new DefaultTableModel();
        String[] Nombres_Columnas = {"nombre", "email", "diagnostico"};
        modelo.setColumnIdentifiers(Nombres_Columnas);
        vista_diagnostico.Table_Relacion.setModel(modelo);

    }

    public void iniciar() {
        vista_diagnostico.setTitle("Diagnostico");
        vista_diagnostico.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == vista_diagnostico.Btn_diagnostico) {
            try {
                Diagnostico diag = new Diagnostico(
                        toBoolean(vista_diagnostico.combobox1),
                        toBoolean(vista_diagnostico.combobox2),
                        toBoolean(vista_diagnostico.combobox3),
                        toBoolean(vista_diagnostico.combobox4),
                        toBoolean(vista_diagnostico.combobox5),
                        vista_diagnostico.txtnombre.getText(),
                        vista_diagnostico.txtemail.getText()
                );

                String json = diag.toJson();
                String respuesta = enviarDiagnostico(json);

                // Extraer solo el diagnostico del JSON
                String diagnostico = "";
                if (respuesta.contains("\"diagnostico\"")) {
                    diagnostico = respuesta.split("\"diagnostico\"\\s*:\\s*\"")[1].split("\"")[0];
                }

                vista_diagnostico.txtrespuesta.setText(diagnostico);

            } catch (Exception ex) {
                ex.printStackTrace();
                vista_diagnostico.txtrespuesta.setText("Error: " + ex.getMessage());
            }
        }

        if (source == vista_diagnostico.Btn_Mostrar) {
            try {
                mostrarDatos();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(vista_diagnostico,
                        "Error al obtener pacientes: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private boolean toBoolean(JComboBox<String> comboBox) {
        String value = (String) comboBox.getSelectedItem();
        return value.equalsIgnoreCase("Sí");
    }

    private String enviarDiagnostico(String jsonInput) throws Exception {
        URL url = new URL("http://localhost:3000/test");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        InputStream stream;
        if (con.getResponseCode() >= 400) { // error del servidor
            stream = con.getErrorStream();
        } else {
            stream = con.getInputStream();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line.trim());
        }
        return response.toString();
    }

    private void mostrarDatos() {
        try {
            URL url = new URL("http://localhost:3000/pacientes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            JSONArray pacientes = new JSONArray(sb.toString());

            DefaultTableModel model = (DefaultTableModel) vista_diagnostico.Table_Relacion.getModel();
            model.setRowCount(0); // limpiar la tabla

            for (int i = 0; i < pacientes.length(); i++) {
                JSONObject paciente = pacientes.getJSONObject(i);

                String nombre = paciente.getString("nombre");
                String email = paciente.getString("email");

                JSONArray diagnosticos = paciente.getJSONArray("diagnosticos");

                for (int j = 0; j < diagnosticos.length(); j++) {
                    JSONObject diag = diagnosticos.getJSONObject(j);
                    String diagnostico = diag.getString("diagnostico");

                    model.addRow(new Object[]{nombre, email, diagnostico});
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // JOptionPane.showMessageDialog(this "Error al obtener datos: " + e.getMessage());
        }
    }

}
