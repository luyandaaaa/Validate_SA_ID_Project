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
        try {
            String gender = getGender(idNumber);
            System.out.println("✓ Valid gender digits - " + gender);
        } catch (IllegalArgumentException e) {
            System.out.println("✗ " + e.getMessage());
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

    public static String getGender(String id) {
        if (id.length() != 13 || !id.matches("\\d{13}")) {
            throw new IllegalArgumentException("Invalid ID number");
        }

        int genderCode = Integer.parseInt(id.substring(6, 10));
        return genderCode < 5000 ? "Female" : "Male";
    }

    private static boolean isValidDate(String dateStr) {
        int year = Integer.parseInt(dateStr.substring(0, 2));
        int month = Integer.parseInt(dateStr.substring(2, 4));
        int day = Integer.parseInt(dateStr.substring(4, 6));

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

    private static boolean isValidCitizenship(String idNumber) {
        int citizenshipDigit = Integer.parseInt(idNumber.substring(10, 11));
        return citizenshipDigit == 0 || citizenshipDigit == 1;
    }

    private static boolean isValidChecksum(String idNumber) {
        int sumOdd = 0;
        // Step 1: Sum digits at odd positions (0-based): 0,2,4,6,8,10
        for (int i = 0; i < 12; i += 2) {
            sumOdd += Character.getNumericValue(idNumber.charAt(i));
        }

        // Step 2: Concatenate even-position digits and multiply by 2
        StringBuilder evenDigits = new StringBuilder();
        for (int i = 1; i < 12; i += 2) {
            evenDigits.append(idNumber.charAt(i));
        }
        int evenNumber = Integer.parseInt(evenDigits.toString()) * 2;

        // Step 3: Sum digits of evenNumber
        int sumEven = 0;
        for (char c : String.valueOf(evenNumber).toCharArray()) {
            sumEven += Character.getNumericValue(c);
        }

        // Step 4: Total sum
        int total = sumOdd + sumEven;

        // Step 5: Calculate check digit
        int calculatedCheckDigit = (10 - (total % 10)) % 10;
        int actualCheckDigit = Character.getNumericValue(idNumber.charAt(12));

        if (actualCheckDigit != calculatedCheckDigit) {
            System.out
                    .println("✗ Checksum failed - expected " + calculatedCheckDigit + " but found " + actualCheckDigit);
            return false;
        }

        System.out.println("✓ Valid checksum");
        return true;
    }
}