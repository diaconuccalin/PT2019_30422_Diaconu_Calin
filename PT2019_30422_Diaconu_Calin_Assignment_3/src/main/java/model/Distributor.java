package model;

public class Distributor {
    int iddistributor;
    String name;
    String email;

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
