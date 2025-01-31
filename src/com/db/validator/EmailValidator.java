package com.db.validator;

public class EmailValidator {
    public static Message message;
    
    public static String getMessage() {
        return message.toString();
    }
    
    private EmailValidator() {}
    
    public static boolean isValidEmail(final String email) {
        message = validateEmail(email);
        return message.isValid();
    }
    
    private static Message validateEmail(final String email) {
        if (email == null || email.isBlank()) {
            return Message.EMPTY;
        }
        
        if (hasValidFormat(email)) {
            if (email.trim().length() < 3) {
                return Message.TO_SHORT;
            } else if (email.trim().length() > 320) {
                return Message.TO_BIG;
            }
        } else {
            return Message.INVALID_DOMAIN;
        }
        
        return Message.VALID;
    }

    public static String normalizeEmail(final String email) {
        if (email == null || email.isBlank()) {
            return email;
        }
        if (!email.contains("@")) {
            return email + "@gmail.com";
        }
        if (!hasValidFormat(email)) {
            return email.split("@")[0] + "@gmail.com";
        }
        return email;
    }
    
    private static boolean hasValidFormat(final String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    
    public enum Message {
        EMPTY {
            @Override
            public String toString() {
                return "Email is empty";
            }
        },
        TO_SHORT {
            @Override
            public String toString() {
                return "Email is too short";
            }
        },
        TO_BIG {
            @Override
            public String toString() {
                return "Email is too big";
            }
        },
        INVALID_DOMAIN {
            @Override
            public String toString() {
                return "Email is not valid";
            }
        },
        VALID {
            @Override
            public String toString() {
                return "Email is valid";
            }
        };
        
        @Override
        public abstract String toString();
        
        public boolean isValid() {
            return this == VALID;
        }
    }
}
