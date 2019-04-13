package model;

public class Client {
    public int idclient;
    private String name;
    private String address;
    private String email;

    public Client(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Client(int idclient, String name, String address, String email) {
        this.idclient = idclient;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public int getIdclient() {
        return idclient;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
