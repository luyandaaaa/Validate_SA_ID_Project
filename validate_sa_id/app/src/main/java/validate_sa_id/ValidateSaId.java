package validate_sa_id;

public class ValidateSaId {
    public static boolean isIdNumberValid(String idNumber) {
        System.out.println("\nValidating ID Number: " + idNumber);
        
        // Check length
        if (idNumber == null || idNumber.length() != 13) {
            System.out.println("✗ Invalid length - ID number must be exactly 13 digits");
            return false;
        }

        // Check if all characters are digits
        if (!idNumber.matches("\\d+")) {
            System.out.println("✗ Invalid characters - ID number must contain only digits");
            return false;
        }

        // Check date validity
        if (!isValidDate(idNumber.substring(0, 6))) {
            return false;
        }

        // Check gender digits (SSSS)
        int genderDigits = Integer.parseInt(idNumber.substring(6, 10));
        if (genderDigits < 0 || genderDigits > 9999) {
            System.out.println("✗ Invalid gender digits - must be between 0000-9999");
            return false;
        }

        // Check citizenship
        if (!isValidCitizenship(idNumber)) {
            System.out.println("✗ Invalid citizenship digit - must be 0 (SA citizen) or 1 (permanent resident)");
            return false;
        }

        // Check checksum
        if (!isValidChecksum(idNumber)) {
            System.out.println("✗ Invalid checksum - failed Luhn algorithm validation");
            return false;
        }

        System.out.println("✓ Valid South African ID number");
        return true;
    }
