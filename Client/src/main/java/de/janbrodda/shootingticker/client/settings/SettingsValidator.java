package de.janbrodda.shootingticker.client.settings;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author jan
 */
public class SettingsValidator {

    private static final UrlValidator urlValidator = new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS);

    public static ValidationResult validate(Settings settings) {
        boolean apiUrlValid = urlValidator.isValid(settings.apiUrl);
        boolean apiKeyValid = StringUtils.isNotBlank(settings.apiKey);
        boolean proxySettingsValid = true;
        if (settings.useProxy) {
            proxySettingsValid
                    = StringUtils.isNotBlank(settings.proxyHost)
                    && settings.proxyPort > 0
                    && settings.proxyPort <= 65535
                    && SettingsValidator.checkIfPortActive(settings.proxyHost, settings.proxyPort);

        }
        
        if (!apiUrlValid){
            return new ValidationResult(false, "The API-Adress is not valid");
        } else if (!apiKeyValid){
            return new ValidationResult(false, "The API-Secret is not valid");
        } else if (!proxySettingsValid){
            return new ValidationResult(false, "The Proxy Settings are not valid");
        } else return new ValidationResult(true, "");
    }

    private static boolean checkIfPortActive(String host, int port) {
        Socket s = null;
        try {
            s = new Socket();
            s.setReuseAddress(true);
            SocketAddress sa = new InetSocketAddress(host, port);
            s.connect(sa, 3000);
            return true;
        } catch (IOException e) {
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                }
            }
        }
        return false;
    }
}
