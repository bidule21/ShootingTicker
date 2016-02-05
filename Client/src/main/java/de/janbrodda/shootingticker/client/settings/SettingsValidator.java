package de.janbrodda.shootingticker.client.settings;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;

/**
 *
 * @author jan
 */
public class SettingsValidator {
    private static final UrlValidator validator = UrlValidator.getInstance();
    
    public static boolean validate(Settings settings){
        boolean apiUrlValid = validator.isValid(settings.apiUrl);
        boolean apiKeyValid = !StringUtils.isNotBlank(settings.apiKey);
        
        //TODO add more validation checks for settings
        
        return false;
    }
}
