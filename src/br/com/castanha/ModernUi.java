package br.com.castanha;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** Estilos visuais reutilizados pelas telas do aplicativo. */
final class ModernUi {

    private static final Color NAVY = new Color(15, 42, 72);
    private static final Color BACKGROUND = new Color(241, 245, 249);
    private static final Color PRIMARY = new Color(31, 111, 201);
    private static final Color SUCCESS = new Color(13, 148, 136);
    private static final Color WARNING = new Color(217, 119, 6);
    private static final Color NEUTRAL = new Color(71, 85, 105);
    private static final Color MUTED = new Color(100, 116, 139);

    private ModernUi() {
    }

    static void styleLogin(JFrame window, JLabel passwordLabel, JLabel heading,
            JPasswordField passwordField, JButton enterButton) {
        window.setTitle("Destrava PDVs | Acesso");
        window.setResizable(false);

        heading.setText("Acesso ao sistema");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 24));
        heading.setForeground(Color.WHITE);
        passwordLabel.setText("Senha de acesso");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passwordLabel.setForeground(MUTED);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                new EmptyBorder(10, 12, 10, 12)));
        passwordField.setPreferredSize(new Dimension(280, 44));
        passwordField.setToolTipText("Informe sua senha para continuar");

        styleButton(enterButton, PRIMARY);
        enterButton.setText("Entrar");
        enterButton.setPreferredSize(new Dimension(280, 46));
        window.getRootPane().setDefaultButton(enterButton);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BACKGROUND);
        JPanel header = createHeader("Destrava PDVs", "Acesso ao sistema");

        JPanel form = new JPanel();
        form.setOpaque(false);
        form.setLayout(new javax.swing.BoxLayout(form, javax.swing.BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(30, 38, 34, 38));
        passwordLabel.setAlignmentX(0.0f);
        passwordField.setAlignmentX(0.0f);
        enterButton.setAlignmentX(0.0f);
        form.add(passwordLabel);
        form.add(javax.swing.Box.createVerticalStrut(8));
        form.add(passwordField);
        form.add(javax.swing.Box.createVerticalStrut(22));
        form.add(enterButton);

        root.add(header, BorderLayout.NORTH);
        root.add(form, BorderLayout.CENTER);
        window.setContentPane(root);
        window.setMinimumSize(new Dimension(390, 300));
        window.pack();
        window.setLocationRelativeTo(null);
    }

    static void styleCommandWindow(JFrame window, JButton unlockButton,
            JButton ticketButton, JButton restartButton, JButton diskButton) {
        window.setResizable(false);
        window.setMinimumSize(new Dimension(560, 390));

        styleButton(unlockButton, PRIMARY);
        styleButton(ticketButton, SUCCESS);
        styleButton(restartButton, WARNING);
        styleButton(diskButton, NEUTRAL);

        JPanel root = new JPanel(new BorderLayout(0, 22));
        root.setBackground(BACKGROUND);
        root.setBorder(new EmptyBorder(0, 30, 30, 30));
        root.add(createHeader("Comandos do PDV", window.getTitle()), BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 14));
        body.setOpaque(false);
        JLabel instruction = new JLabel("Escolha uma ação para o caixa selecionado");
        instruction.setFont(new Font("Segoe UI", Font.BOLD, 13));
        instruction.setForeground(MUTED);

        JPanel actions = new JPanel(new GridLayout(2, 2, 14, 14));
        actions.setOpaque(false);
        actions.add(unlockButton);
        actions.add(restartButton);
        actions.add(ticketButton);
        actions.add(diskButton);

        body.add(instruction, BorderLayout.NORTH);
        body.add(actions, BorderLayout.CENTER);
        root.add(body, BorderLayout.CENTER);

        window.setContentPane(root);
        window.pack();
        window.setLocationRelativeTo(null);
    }

    private static JPanel createHeader(String titleText, String subtitleText) {
        JPanel header = new JPanel();
        header.setBackground(NAVY);
        header.setBorder(new EmptyBorder(26, 28, 24, 28));
        header.setLayout(new javax.swing.BoxLayout(header, javax.swing.BoxLayout.Y_AXIS));

        JLabel title = new JLabel(titleText);
        title.setAlignmentX(0.0f);
        title.setFont(new Font("Segoe UI", Font.BOLD, 25));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(subtitleText);
        subtitle.setAlignmentX(0.0f);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(203, 213, 225));

        header.add(title);
        header.add(javax.swing.Box.createVerticalStrut(5));
        header.add(subtitle);
        return header;
    }

    private static void styleButton(JButton button, Color background) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(background);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setMargin(new Insets(16, 18, 16, 18));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
    }
}
