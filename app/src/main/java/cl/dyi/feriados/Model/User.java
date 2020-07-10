package cl.dyi.feriados.Model;

import androidx.annotation.NonNull;

public class User {
    private int id;
    private String name;
    private String email;
    private String dni;
    private String address;
    private String phone;
    private String role;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    @NonNull
    @Override
    public String toString() {
        return "El usuario "+this.name+" ha sido registrado.";
    }
}
