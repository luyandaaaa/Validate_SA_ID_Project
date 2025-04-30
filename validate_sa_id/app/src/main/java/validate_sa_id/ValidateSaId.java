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
    private static boolean isValidDate(String dateStr) {
        int year = Integer.parseInt(dateStr.substring(0, 2));
        int month = Integer.parseInt(dateStr.substring(2, 4));
        int day = Integer.parseInt(dateStr.substring(4, 6));

        // We'll validate the date without making century assumptions
        // Just ensure the date components form a valid date in any century

        // Validate month
        if (month < 1 || month > 12) {
            System.out.println("✗ Invalid month - must be between 01-12");
            return false;
        }

        // Validate day
        if (day < 1 || day > 31) {
            System.out.println("✗ Invalid day - must be between 01-31");
            return false;
        }

        // Check specific month day limits
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day > 30) {
                System.out.println("✗ Invalid day - month " + month + " only has 30 days");
                return false;
            }
        } else if (month == 2) {
            if (day > 28) {
                System.out.println("✗ Invalid day - February only has 28 days (ignoring leap years)");
                return false;
            }
        }

        System.out.printf("✓ Valid date: %02d/%02d/%02d (DD/MM/YY format)%n", day, month, year);
        return true;
    }

