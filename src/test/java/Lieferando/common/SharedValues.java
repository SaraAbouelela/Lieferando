package Lieferando.common;
public class SharedValues {
    private static String token;

    private static String clientSecret;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedValues.token = token;
    }
    public static void setClientSecret(String clientScrt)
    {
        SharedValues.clientSecret = clientScrt;
    }
    public static String getClientSecret()
    {
        return SharedValues.clientSecret;
    }
}
