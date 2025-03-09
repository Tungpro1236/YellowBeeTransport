package Utils;

import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        name = name.trim();
        if (name.length() < 5 || name.length() > 50) {
            return false;
        }
        String nameRegex = "^[A-Z][a-zA-Z]*(\\s[A-Z][a-zA-Z]*)*$";
        return Pattern.matches(nameRegex, name);
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            return false;
        }
        address = address.trim();

        // Kiểm tra độ dài từ 5 - 30 ký tự
        if (address.length() < 5 || address.length() > 30) {
            return false;
        }

        // Regex kiểm tra định dạng địa chỉ hợp lệ
        String addressRegex = "^[A-Z][a-zA-Z0-9]*(\\s[A-Z0-9][a-zA-Z0-9]*)*$";
        return Pattern.matches(addressRegex, address);
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        phone = phone.trim();
        String phoneRegex = "^\\d{10}$";
        return Pattern.matches(phoneRegex, phone);
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        email = email.trim();
        if (email.length() < 8 || email.length() > 50) {
            return false;
        }
        String emailRegex = "^[a-zA-Z][a-zA-Z0-9._%+-]*@[a-zA-Z]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        username = username.trim();
        if (username.length() < 5 || username.length() > 10) {
            return false;
        }
        String usernameRegex = "^[a-zA-Z0-9]{5,10}$";
        return Pattern.matches(usernameRegex, username);
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        password = password.trim();
        if (password.length() < 3 || password.length() > 8) {
            return false;
        }
        String passwordRegex = "^[a-zA-Z0-9]{3,8}$";
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidImageURL(String imageUrl) {
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            return false;
        }
        imageUrl = imageUrl.trim();
        if (imageUrl.length() < 5 || imageUrl.length() > 250) {
            return false;
        }
        return true;
    }
}
