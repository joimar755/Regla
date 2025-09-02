package Home;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author joimar
 */
public class DiagnosticoApp extends JFrame {
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JComboBox<String> cbDolorAbdominal;
    private JComboBox<String> cbNauseas;
    private JComboBox<String> cbVomito;
    private JComboBox<String> cbDiarrea;
    private JComboBox<String> cbFiebre;
    private JTextArea resultado;

    public DiagnosticoApp() {
        setTitle("Diagnóstico Médico");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel preguntasPanel = new JPanel(new GridLayout(8, 2, 5, 5));

        // Campos nombre y email
        preguntasPanel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        preguntasPanel.add(txtNombre);

        preguntasPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        preguntasPanel.add(txtEmail);

        // Preguntas
        cbDolorAbdominal = crearPregunta(preguntasPanel, "¿Tiene dolor abdominal?");
        cbNauseas = crearPregunta(preguntasPanel, "¿Tiene náuseas?");
        cbVomito = crearPregunta(preguntasPanel, "¿Ha tenido vómito?");
        cbDiarrea = crearPregunta(preguntasPanel, "¿Presenta diarrea?");
        cbFiebre = crearPregunta(preguntasPanel, "¿Tiene fiebre?");

        // Botón diagnosticar
        JButton btnDiagnosticar = new JButton("Diagnosticar");
        btnDiagnosticar.addActionListener(this::diagnosticar);
        
        resultado = new JTextArea(5, 30);
        resultado.setEditable(false);

        add(preguntasPanel, BorderLayout.CENTER);
        add(btnDiagnosticar, BorderLayout.SOUTH);
        add(new JScrollPane(resultado), BorderLayout.EAST);
    }

    private JComboBox<String> crearPregunta(JPanel panel, String texto) {
        JLabel label = new JLabel(texto);
        JComboBox<String> combo = new JComboBox<>(new String[]{"Seleccionar", "Sí", "No"});
        panel.add(label);
        panel.add(combo);
        return combo;
    }

    private void diagnosticar(ActionEvent e) {
        try {
            // Crear JSON con hechos incluyendo nombre y email
            String jsonInput = "{"
                    + "\"nombre\": \"" + txtNombre.getText() + "\","
                    + "\"email\": \"" + txtEmail.getText() + "\","
                    + "\"hechos\": {"
                    + "\"dolor_abdominal\": " + toBoolean(cbDolorAbdominal) + ","
                    + "\"nauseas\": " + toBoolean(cbNauseas) + ","
                    + "\"vomito\": " + toBoolean(cbVomito) + ","
                    + "\"diarrea\": " + toBoolean(cbDiarrea) + ","
                    + "\"fiebre\": " + toBoolean(cbFiebre)
                    + "}"
                    + "}";

            System.out.println("JSON a enviar: " + jsonInput);

            // Llamada a la API
            URL url = new URL("http://localhost:3000/test"); // tu endpoint
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            // Enviar JSON
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer respuesta
            int status = conn.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            (status >= 200 && status < 300) ? conn.getInputStream() : conn.getErrorStream(),
                            "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            resultado.setText("Código HTTP: " + status + "\nRespuesta API:\n" + response.toString());

        } catch (IOException ex) {
            resultado.setText("Error al conectar con la API");
        }
    }

    private String toBoolean(JComboBox<String> comboBox) {
        String value = comboBox.getSelectedItem().toString();
        return (value.equalsIgnoreCase("Sí") || value.equalsIgnoreCase("Si")) ? "true" : "false";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DiagnosticoApp().setVisible(true));
    }
}
