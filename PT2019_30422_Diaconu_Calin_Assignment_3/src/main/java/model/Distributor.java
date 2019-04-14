package model;

public class Distributor {
    private int iddistributor;
    private String name;
    private String email;

    public Distributor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Distributor(int iddistributor, String name, String email) {
        this.iddistributor = iddistributor;
        this.name = name;
        this.email = email;
    }

    public Distributor(int iddistributor) {
        this.iddistributor = iddistributor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getIddistributor() {
        return iddistributor;
    }

    public void setIddistributor(int iddistributor) {
        this.iddistributor = iddistributor;
    }
}
