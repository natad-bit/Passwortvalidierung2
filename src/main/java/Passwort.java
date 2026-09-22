import java.util.Locale;
import java.util.Set;

public class Passwort {
    public static final Set<String> COMMON_PASSWORDS = Set.of(
            "password1",
            "password123",
            "password1234",
            "password2024",
            "password2025",
            "password2026",
            "pass1234",
            "qwerty1234",
            "admin123",
            "admin1234",
            "welcome1",
            "welcome123",
            "secret123",
            "changeme1",
            "changeit1",
            "test1234",
            "testing1",
            "developer1",
            "temp1234",
            "mysecretkey1",
            "supersecret1",
            "jwtsecretkey1",
            "yourapikeyhere1",
            "insert_your_key_1"
    );


    public static void main(String[] args) {
        System.out.println("Enter your passwort:");
    }

    //mindestens 8 Zeichen
    public static boolean hasMinLength(String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is empty!");
            return false;
        }
        return password.length() >= 8;
    }

    //mindestens eine Ziffer (0–9)
    public static boolean containsDigit(String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is empty!");
            return false;
        }
        char[] passwordChars = password.toCharArray();
        for (char passwordChar : passwordChars) {
            if (Character.isDigit(passwordChar)) {
                return true;
            }
        }
        return false;
    }

    //Groß und Kleinbuchstaben
    public static boolean containsUpperAndLower(String password) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is empty!");
            return false;
        }
        char[] passwordChars = password.toCharArray();
        boolean foundUpper = false;
        boolean foundLower = false;
        for (char passwordChar : passwordChars) {
            if (Character.isUpperCase(passwordChar)) {
                foundUpper = true;
            }
            if (Character.isLowerCase(passwordChar)) {
                foundLower = true;
            }
            if (foundUpper && foundLower) {
                return true;
            }
        }
        return false;
    }

    //Häufige Passwörter
    public static boolean isCommonPassword(String password) {
        String normalizedPassword = password.trim().toLowerCase(Locale.ROOT);
        return normalizedPassword.isEmpty() || COMMON_PASSWORDS.contains(normalizedPassword);
    }

    // Bonus:
    //public static boolean containsSpecialChar(String password, String allowed);

    // nutzt die obenstehenden Checks
    public static boolean isValid(String password) {
        return hasMinLength(password) && containsDigit(password)
                && containsUpperAndLower(password) && !isCommonPassword(password);
    }

}
