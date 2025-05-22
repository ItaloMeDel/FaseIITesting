public class User {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String role;
    private boolean status;

    public User(int id, String name, String lastname, String email, String passwordHash, String phoneNumber, String role, boolean status) {
        setId(id);
        setName(name);
        setLastname(lastname);
        setEmail(email);
        setPasswordHash(passwordHash);
        setPhoneNumber(phoneNumber);
        setRole(role);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser mayor a cero");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Nombre inválido");
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.trim().isEmpty()) throw new IllegalArgumentException("Apellido inválido");
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            throw new IllegalArgumentException("Email inválido");
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.length() < 6)
            throw new IllegalArgumentException("Contraseña demasiado corta o nula");
        this.passwordHash = passwordHash;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{9}"))
            throw new IllegalArgumentException("Teléfono inválido (solo números, 7-15 dígitos)");
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) throw new IllegalArgumentException("Rol inválido");
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
