package ttps.entregable5.cuentasclaras.model;

public class Credenciales {

    private String token;
    private int exp;
    private String username;
    private String userId;

    public Credenciales() {
    }

    public Credenciales(String token, int exp, String username, String userID) {
        this.token = token;
        this.exp = exp;
        this.username = username;
        this.userId = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usr) {
        this.username = usr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userID) {
        this.userId = userID;
    }
}
