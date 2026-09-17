package br.com.castanha;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Centraliza a leitura de credenciais e configurações sensíveis.
 *
 * Os valores são lidos, em ordem de prioridade, de variáveis de ambiente
 * ou de um arquivo {@code config.properties} colocado ao lado do executável.
 * Esse arquivo NÃO deve ser versionado (veja config.properties.example).
 */
public final class AppConfig {

    private static final Properties PROPS = load();

    private AppConfig() {
    }

    private static Properties load() {
        Properties props = new Properties();
        Path path = Path.of("config.properties");
        if (Files.exists(path)) {
            try (FileInputStream in = new FileInputStream(path.toFile())) {
                props.load(in);
            } catch (IOException e) {
                System.err.println("Não foi possível ler config.properties: " + e.getMessage());
            }
        }
        return props;
    }

    private static String value(String propertyKey, String envVar) {
        String fromEnv = System.getenv(envVar);
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        return PROPS.getProperty(propertyKey);
    }

    public static String getSshUser() {
        return value("ssh.user", "DESTRAVAPDV_SSH_USER");
    }

    public static String getSshPassword() {
        return value("ssh.password", "DESTRAVAPDV_SSH_PASSWORD");
    }

    /** Retorna o conjunto de PINs válidos para o login do aplicativo. */
    public static Set<String> getLoginPins() {
        String raw = value("login.pins", "DESTRAVAPDV_LOGIN_PINS");
        Set<String> pins = new HashSet<>();
        if (raw != null) {
            for (String pin : raw.split(",")) {
                String trimmed = pin.trim();
                if (!trimmed.isEmpty()) {
                    pins.add(trimmed);
                }
            }
        }
        return pins;
    }
}
