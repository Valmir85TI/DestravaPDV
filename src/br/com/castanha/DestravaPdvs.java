package br.com.castanha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Tela inicial para selecionar o PDV que deve ser destravado.
 */
public class DestravaPdvs extends JFrame {

    private static final Color NAVY = new Color(15, 42, 72);
    private static final Color BLUE = new Color(31, 111, 201);
    private static final Color BLUE_HOVER = new Color(24, 88, 160);
    private static final Color BACKGROUND = new Color(241, 245, 249);

    public DestravaPdvs() {
        configureWindow();
        add(createContent());
        pack();
        setLocationRelativeTo(null);
    }

    private void configureWindow() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Destrava PDVs");
        setMinimumSize(new Dimension(670, 510));
        setResizable(false);
    }

    private JPanel createContent() {
        JPanel content = new JPanel(new BorderLayout(0, 24));
        content.setBackground(BACKGROUND);
        content.setBorder(new EmptyBorder(0, 32, 32, 32));
        content.add(createHeader(), BorderLayout.NORTH);
        content.add(createCaixasPanel(), BorderLayout.CENTER);
        return content;
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout(0, 4));
        header.setBackground(NAVY);
        header.setBorder(new EmptyBorder(28, 30, 26, 30));

        JLabel title = new JLabel("Destrava PDVs");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Selecione o caixa para executar os comandos de destravamento");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(203, 213, 225));

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        return header;
    }

    private JPanel createCaixasPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 16));
        panel.setBackground(BACKGROUND);

        JLabel instruction = new JLabel("CAIXAS DISPONÍVEIS");
        instruction.setFont(new Font("Segoe UI", Font.BOLD, 12));
        instruction.setForeground(new Color(100, 116, 139));

        JPanel grid = new JPanel(new GridLayout(0, 4, 14, 14));
        grid.setBackground(BACKGROUND);

        for (Map.Entry<String, String> caixa : caixasDisponiveis().entrySet()) {
            grid.add(createCaixaButton(caixa.getKey(), caixa.getValue()));
        }

        panel.add(instruction, BorderLayout.NORTH);
        panel.add(grid, BorderLayout.CENTER);
        return panel;
    }

    private Map<String, String> caixasDisponiveis() {
        Map<String, String> caixas = new LinkedHashMap<>();
        caixas.put("CAIXA 01", "br.com.castanha.Caixa01");
        caixas.put("CAIXA 09", "br.com.castanha.Caixa09");
        caixas.put("CAIXA 10", "br.com.castanha.Caixa10");
        caixas.put("CAIXA 11", "br.com.castanha.Caixa11");
        caixas.put("CAIXA 12", "br.com.castanha.Caixa12");
        caixas.put("CAIXA 13", "br.com.castanha.Caixa13");
        caixas.put("CAIXA 14", "br.com.castanha.Caixa14");
        caixas.put("CAIXA 15", "br.com.castanha.Caixa15");
        caixas.put("CAIXA 16", "br.com.castanha.Caixa16");
        caixas.put("CAIXA 17", "br.com.castanha.Caixa17");
        caixas.put("CAIXA 18", "br.com.castanha.Caixa18");
        caixas.put("CAIXA 19", "br.com.castanha.Caixa19");
        caixas.put("CAIXA 20", "br.com.castanha.Caixa20");
        return caixas;
    }

    private JButton createCaixaButton(String label, String destination) {
        JButton button = new JButton(label);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(15, 18, 15, 18));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        button.addChangeListener(event -> {
            if (button.getModel().isRollover()) {
                button.setBackground(BLUE_HOVER);
            } else {
                button.setBackground(BLUE);
            }
        });
        button.addActionListener(event -> openCaixa(destination));
        return button;
    }

    private void openCaixa(String className) {
        try {
            JFrame caixa = (JFrame) Class.forName(className).getDeclaredConstructor().newInstance();
            caixa.setVisible(true);
            dispose();
        } catch (ReflectiveOperationException exception) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível abrir a tela selecionada.",
                    "Destrava PDVs",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
