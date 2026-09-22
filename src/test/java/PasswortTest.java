
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswortTest {

    //mindestens 8 Zeichen(Grenzfälle: 7/8/9, leere Strings, null).
    @ParameterizedTest
    @ValueSource(strings = {"Ottomotto", "12345678"})
    void hasMinLength_expectTrue(String password) {
        assertTrue(Passwort.hasMinLength(password, 8));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Otto", "1234567", ""})
    void hasMinLength_expectFalse(String password) {
        assertFalse(Passwort.hasMinLength(password, 8));
    }

    //mindestens eine Ziffer (0–9)(keine Ziffer, genau eine, mehrere,
    // nur Ziffern, Unicode-Fallen ignorieren)
    @ParameterizedTest
    @ValueSource(strings = {"gnt23", "1234567", "oooo0", "1"})
    void containsDigit_expectTrue(String password) {
        assertTrue(Passwort.containsDigit(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"gnttr", "ooooo", ""})
    void containsDigit_expectFalse(String password) {
        assertFalse(Passwort.containsDigit(password));
    }

    //Groß und Kleinbuchstaben(nur UPPER, nur lower, gemischt, ein Buchstabe)
    @ParameterizedTest
    @ValueSource(strings = {"gnTTr", "OOooO", "Aa"})
    void containsUpperAndLower_expectTrue(String password) {
        assertTrue(Passwort.containsUpperAndLower(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"gnttr", "OOOOO", "A", "a", ""})
    void containsUpperAndLower_expectFalse(String password) {
        assertFalse(Passwort.containsUpperAndLower(password));
    }

    //Häufige Passwörter
    @ParameterizedTest
    @ValueSource(strings = {"Password2026", "Admin1234", "Temp1234"})
    void isCommonPassword_expectTrue(String password) {
        assertTrue(Passwort.isCommonPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Winter2026", "A1s2d3f4", "coolProg2"})
    void isCommonPassword_expectFalse(String password) {
        assertFalse(Passwort.isCommonPassword(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc1defg!", "A1s2d3f4{", "Abcdef1g)"})
    void isValidPassword_expectTrue(String password) {
        assertTrue(Passwort.isValid(password));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc1def", "Abcdefgh", "abcdefg1", "ABCDEFG1", "Passwort1"})
    void isValidPassword_expectFalse(String password) {
        assertFalse(Passwort.isValid(password));
    }

    //mindestens ein Sonderzeichen
    @ParameterizedTest
    @ValueSource(strings = {"Abc1def1!", "Abcd_efgh", "abcd?efg1", "ABCDEFG1!?", "P_asswort1"})
    void containsSpecialChar_expectTrue(String password) {
        assertTrue(Passwort.containsSpecialChar(password, Passwort.SPECIAL_CHARACTERS));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Abc1def", "Abcde\nafgh", "abcdefg1", "ABCDEFG1", "Passwort1"})
    void containsSpecialChar_expectFalse(String password) {
        assertFalse(Passwort.containsSpecialChar(password, Passwort.SPECIAL_CHARACTERS));
    }
}