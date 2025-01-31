package com.db.validator;

public class NameValidator {
    public static Message message;
    
    public static String getMessage() {
        return message.toString();
    }
    
    private NameValidator() {}
    
    public static boolean isValidUserName(final String username) {
        message = validateUserName(username);
        return message.isValid();
    }
    
    private static Message validateUserName(final String username) {
        if (username == null || username.isBlank()) {
            return Message.EMPTY;
        }
        if (username.length() < 2) {
            return Message.TO_SHORT;
        }
        if (username.length() > 50) {
            return Message.TO_BIG;
        }
        if (!hasAlpha(username)) {
            return Message.NO_CHAR;
        }
        return Message.VALID;
    }
    
    private static boolean hasAlpha(final String username) {
        return username.matches(".*[a-zA-Z].*");
    }
    
    public enum Message {
        EMPTY {
            @Override
            public String toString() {
                return "User name is empty";
            }
        },
        TO_SHORT {
            @Override
            public String toString() {
                return "User name is to short";
            }
        },
        TO_BIG {
            @Override
            public String toString() {
                return "User name is to big";
            }
        },
        NO_CHAR {
            @Override
            public String toString() {
                return "User name must have characters";
            }
        },
        VALID {
            @Override
            public String toString() {
                return "User name is valid";
            }
        };
        
        @Override
        public abstract String toString();
        
        public boolean isValid() {
            return this == VALID;
        }
    }
}
