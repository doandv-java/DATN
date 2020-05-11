package haui.stores.utils;

import org.passay.CharacterRule;
import org.passay.PasswordGenerator;

import java.util.Arrays;
import java.util.List;

import static org.passay.EnglishCharacterData.*;

public class GenratePassword {
    public static String generatePassword() {
        List<CharacterRule> rules = Arrays.asList(
                new CharacterRule(UpperCase, 1),
                new CharacterRule(LowerCase, 1),
                new CharacterRule(Digit, 1));
        PasswordGenerator generator = new PasswordGenerator();
        return generator.generatePassword(8, rules);
    }
}
