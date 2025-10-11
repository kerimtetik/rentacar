package NesneyeYonelikProje;

public class UserFactory {
	public static User createUser(String userType, String tc, String name) {
        if ("admin".equalsIgnoreCase(userType)) {
            return new AdminUser(name, name, name, name, name,name, 0);
        } else if ("customer".equalsIgnoreCase(userType)) {
            return new CustomerUser(name, name, name, name, name, tc, 0);
        }
        throw new IllegalArgumentException("Unknown user type: " + userType);
    }
}

