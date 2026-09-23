package it.uniroma2.nightplan.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Centralised runtime configuration.
 *
 * <p>Values are resolved in the following order:
 * <ol>
 *     <li>environment variables (e.g. {@code NIGHTPLAN_DB_PASSWORD});</li>
 *     <li>{@code config/config.properties} in the working directory;</li>
 *     <li>{@code config.properties} on the classpath (legacy location);</li>
 *     <li>built-in defaults.</li>
 * </ol>
 * Credentials are never committed: copy {@code config/config.properties.example}
 * to {@code config/config.properties} and fill in your own values.
 */
public final class AppConfig {

    private static final Logger LOGGER = Logger.getLogger("NightPlan");
    private static final Path CONFIG_FILE = Paths.get("config", "config.properties");
    private static final String CLASSPATH_CONFIG = "config.properties";

    private static final String DEFAULT_DB_URL = "jdbc:mysql://localhost:3306/nightplan"
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final Properties PROPERTIES = load();

    private AppConfig() {
        // utility class
    }

    public static String dbUrl() {
        return get("db.url", "NIGHTPLAN_DB_URL", DEFAULT_DB_URL);
    }

    public static String dbUsername() {
        return get("db.username", "NIGHTPLAN_DB_USERNAME", "root");
    }

    public static String dbPassword() {
        return get("db.password", "NIGHTPLAN_DB_PASSWORD", "");
    }

    public static String serverHost() {
        return get("server.host", "NIGHTPLAN_SERVER_HOST", "localhost");
    }

    public static int serverPort() {
        String value = get("server.port", "NIGHTPLAN_SERVER_PORT", "2521");
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            LOGGER.warning(() -> "Invalid server.port value '" + value + "', falling back to 2521");
            return 2521;
        }
    }

    public static String googleClientSecretsPath() {
        return get("google.clientSecretsPath", "NIGHTPLAN_GOOGLE_CLIENT_SECRETS",
                Paths.get("config", "client_secrets.json").toString());
    }

    public static String googleTokensDirectory() {
        return get("google.tokensDirectory", "NIGHTPLAN_GOOGLE_TOKENS_DIR", "tokens");
    }

    private static String get(String key, String envVariable, String defaultValue) {
        String fromEnv = System.getenv(envVariable);
        if (fromEnv != null && !fromEnv.isBlank()) {
            return fromEnv;
        }
        return PROPERTIES.getProperty(key, defaultValue);
    }

    private static Properties load() {
        Properties properties = new Properties();
        if (Files.isRegularFile(CONFIG_FILE)) {
            try (InputStream in = Files.newInputStream(CONFIG_FILE)) {
                properties.load(in);
                LOGGER.fine(() -> "Configuration loaded from " + CONFIG_FILE.toAbsolutePath());
                return properties;
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Cannot read " + CONFIG_FILE.toAbsolutePath(), e);
            }
        }
        try (InputStream in = AppConfig.class.getClassLoader().getResourceAsStream(CLASSPATH_CONFIG)) {
            if (in != null) {
                properties.load(in);
                LOGGER.fine("Configuration loaded from classpath");
            } else {
                LOGGER.info("No configuration file found, using environment variables and defaults");
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Cannot read classpath configuration", e);
        }
        return properties;
    }
}
