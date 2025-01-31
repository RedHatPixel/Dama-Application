package com.db.validator;

public class PasswordValidator {
    public static Message message;
    
    public static String getMessage() {
        return message.toString();
    }
    
    private PasswordValidator() {}
    
    public static boolean isValidPassword(final String password) {
        message = validatePassword(password);
        return message.isValid();
    }
    
    private static Message validatePassword(final String password) {
        if (password == null || password.isBlank()) {
            return Message.EMPTY;
        }
        if (password.length() < 8 || password.length() > 100) {
            return Message.LENGTH_OUT;
        }
        if (!hasDigit(password)) {
            return Message.NO_DIGIT;
        }
        if (!hasSpecialCharacter(password)) {
            return Message.NO_SPECIAL_CHAR;
        }
        return Message.VALID;
    }
    
    private static boolean hasDigit(final String password) {
        return password.matches(".*\\d.*");
    }
    
    private static boolean hasSpecialCharacter(final String password) {
        return password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }
    
    public enum Message {
        EMPTY {
            @Override
            public String toString() {
                return "Password is empty";
            }
        },
        LENGTH_OUT {
            @Override
            public String toString() {
                return "Password length is weak";
            }
        },
        NO_DIGIT {
            @Override
            public String toString() {
                return "Password must contain digit";
            }
        },
        NO_SPECIAL_CHAR {
            @Override
            public String toString() {
                return "Password must contain special character";
            }
        },
        VALID {
            @Override
            public String toString() {
                return "Password is valid";
            }
        };
        
        @Override
        public abstract String toString();
        
        public boolean isValid() {
            return this == VALID;
        }
    }
}
