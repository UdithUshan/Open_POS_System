package src.main.java.POSSystem.com.pos.model;

public class User {
    private Long id;
    private String userName;
    private String passwordHash;
    private String fullName;
    private Role role;

    public User(Long id, String userName, String passwordHash, String fullName, Role role) {
        this.id = id;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPasswoHash() {
        return passwordHash;
    }
    public String getFullName() {
        return fullName;
    }
    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public boolean hasPermission(String action) {
        if(role == Role.ADMIN){
            return true;
        }

        switch (action) {
            case "PROCESS_SALE":
                return role == Role.MANAGER || role == Role.CASHIER;
            case "MANAGE_PRODUCTS":
                return role == Role.MANAGER;
            case "VIEW_REPORTS":
                return role == Role.MANAGER;

            default:
                return false;
        }
    }
}
