package model;

public class Order {
    private int idorder;
    private int clientid;
    private int productid;
    private int productamount;
    private int totalprice;

    public Order(int clientid, int productid, int productamount, int totalprice) {
        this.clientid = clientid;
        this.productid = productid;
        this.productamount = productamount;
        this.totalprice = totalprice;
    }

    public Order(int idorder, int clientid, int productid, int productamount, int totalprice) {
        this.idorder = idorder;
        this.clientid = clientid;
        this.productid = productid;
        this.productamount = productamount;
        this.totalprice = totalprice;
    }

    public Order(int idorder) {
        this.idorder = idorder;
    }

    public int getProductamount() {
        return productamount;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public int getClientid() {
        return clientid;
    }

    public int getProductid() {
        return productid;
    }

    public int getIdorder() {
        return idorder;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public void setIdorder(Integer idorder) {
        this.idorder = idorder;
    }

    public void setProductamount(Integer productamount) {
        this.productamount = productamount;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public void setTotalprice(Integer totalprice) {
        this.totalprice = totalprice;
    }
}
