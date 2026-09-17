package br.com.castanha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 * Caixa de mensagem padronizada para os retornos dos comandos do PDV.
 */
final class JOptionPane {

    private static final Color SUCCESS = new Color(13, 148, 136);
    private static final Color ERROR = new Color(220, 38, 38);
    private static final Color TEXT = new Color(30, 41, 59);

    private JOptionPane() {
    }

    static void showMessageDialog(Component parent, Object message) {
        String text = String.valueOf(message);
        boolean successful = "Programa executado com sucesso".equals(text);
        Color accent = successful ? SUCCESS : ERROR;
        String title = successful ? "Comando concluído" : "Não foi possível concluir";

        Window owner = parent == null
                ? java.awt.KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow()
                : SwingUtilities.getWindowAncestor(parent);
        JDialog dialog = new JDialog(owner, "Destrava PDVs", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(Color.WHITE);
        root.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225)));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(accent);
        header.setBorder(new EmptyBorder(18, 22, 18, 22));
        JLabel heading = new JLabel(title);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 18));
        heading.setForeground(Color.WHITE);
        header.add(heading, BorderLayout.CENTER);

        JLabel content = new JLabel(toHtml(text));
        content.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        content.setForeground(TEXT);
        content.setBorder(new EmptyBorder(0, 22, 0, 22));

        JButton closeButton = new JButton("OK");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(accent);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.setBorder(new EmptyBorder(9, 24, 9, 24));
        closeButton.addActionListener(event -> dialog.dispose());

        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(0, 22, 20, 22));
        footer.add(closeButton);

        root.add(header, BorderLayout.NORTH);
        root.add(content, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);
        dialog.setContentPane(root);
        dialog.getRootPane().setDefaultButton(closeButton);
        dialog.pack();
        dialog.setLocationRelativeTo(owner);
        dialog.setVisible(true);
    }

    private static String toHtml(String text) {
        return "<html><div style='width: 280px; text-align: center;'>"
                + escapeHtml(text).replace("\n", "<br>")
                + "</div></html>";
    }

    private static String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
