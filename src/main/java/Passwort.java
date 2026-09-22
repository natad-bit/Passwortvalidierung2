import java.util.*;

public class Passwort {
    public static final Set<String> COMMON_PASSWORDS = Set.of(
            "password1", "passwort1",
            "password123", "passwort123",
            "password1234", "passwort1234",
            "password2024", "passwort2024",
            "password2025", "passwort2025",
            "password2026", "passwort2026",
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

    public static final String SPECIAL_CHARACTERS = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

    public static final int MINIMUM_CHARACTERS = 8;

    public static final String REASON_MIN_LENGTH = "Password should be more than " + MINIMUM_CHARACTERS + " characters";
    public static final String REASON_DIGIT = "Password should contain minimum one digit";
    public static final String REASON_CASE = "Password should contain upper and lower case letters";
    public static final String REASON_NOT_COMMON = "Password shouldn't be common";
    public static final String REASON_SPECIAL_CHARACTERS = "Password should contain at least one special characters";

    public static List<String> ValidationResult = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid;
        do {
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            isValid = isValid(password);
            if (!ValidationResult.isEmpty()) {
                System.out.println(ValidationResult.toString());
                ValidationResult.clear();
            }
        } while (!isValid);
        System.out.println("Great password!");
    }

    //mindestens 8 Zeichen
    public static boolean hasMinLength(String password, int minLength) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is empty!");
            return false;
        }
        if (password.length() < minLength) {
            ValidationResult.add(REASON_MIN_LENGTH);
            return false;
        } else {
            return true;
        }
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
        ValidationResult.add(REASON_DIGIT);
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
        ValidationResult.add(REASON_CASE);
        return false;
    }

    //Häufige Passwörter
    public static boolean isCommonPassword(String password) {
        String normalizedPassword = password.trim().toLowerCase(Locale.ROOT);
        if (normalizedPassword.isEmpty() || COMMON_PASSWORDS.contains(normalizedPassword)) {
            ValidationResult.add(REASON_NOT_COMMON);
            return true;
        } else {
            return false;
        }
    }

    // Bonus: mindestens ein Sonderzeichen
    public static boolean containsSpecialChar(String password, String allowed) {
        if (password == null || password.isEmpty()) {
            System.out.println("Password is empty!");
            return false;
        }
        char[] passwordChars = password.toCharArray();
        for (char passwordChar : passwordChars) {
            if (allowed.contains(passwordChar + "")) {
                return true;
            }
        }
        ValidationResult.add(REASON_SPECIAL_CHARACTERS);
        return false;
    }

    // nutzt die obenstehenden Checks
    public static boolean isValid(String password) {
        return hasMinLength(password, MINIMUM_CHARACTERS) && containsDigit(password)
                && containsUpperAndLower(password) &&
                !isCommonPassword(password) && containsSpecialChar(password, SPECIAL_CHARACTERS);
    }

}
