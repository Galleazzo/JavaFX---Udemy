package basico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CasasBahia extends JFrame implements ActionListener {
    private JLabel valorLabel, diasLabel, resultadoLabel;
    private JTextField valorField, diasField, resultadoField;
    private JButton calcularButton;

    public CasasBahia() {
        setTitle("Casas Bahia");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela na tela

        // cria os componentes com uma fonte maior e em negrito
        Font font = new Font("Arial", Font.BOLD, 16);
        valorLabel = new JLabel("Valor da Parcela:");
        valorLabel.setFont(font);
        diasLabel = new JLabel("Dias em Atraso:");
        diasLabel.setFont(font);
        resultadoLabel = new JLabel("Valor Parcela Atrasada:");
        resultadoLabel.setFont(font);

        // cria os campos de texto com uma fonte maior
        valorField = new JTextField(10);
        valorField.setFont(font);
        diasField = new JTextField(10);
        diasField.setFont(font);
        resultadoField = new JTextField(10);
        resultadoField.setFont(font);
        resultadoField.setEditable(false);

        // cria o botão com uma cor de fundo diferente e uma fonte maior
        calcularButton = new JButton("Calcular");
        calcularButton.setFont(font);
        calcularButton.setBackground(new Color(255, 204, 0)); // cor amarela
        calcularButton.addActionListener(this);

        // cria um painel com uma borda e um layout em grid
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // borda vazia para dar espaço
        panel.add(valorLabel);
        panel.add(valorField);
        panel.add(diasLabel);
        panel.add(diasField);
        panel.add(resultadoLabel);
        panel.add(resultadoField);
        panel.add(new JLabel()); // adiciona um componente vazio para centralizar o botão
        panel.add(calcularButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        double valor = Double.parseDouble(valorField.getText());
        int dias = Integer.parseInt(diasField.getText());

        double resultado = ((valor * 0.02) * dias) + valor;

        resultadoField.setText(String.format("%.2f", resultado));
    }

    public static void main(String[] args) {
        new CasasBahia();
    }
}
