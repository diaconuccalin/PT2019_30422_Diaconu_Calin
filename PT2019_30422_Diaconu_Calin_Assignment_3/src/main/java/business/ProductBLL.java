package business;

import dataAccess.ProductDAO;
import model.Product;

public class ProductBLL {
    public static void addProduct(Product product) {
        ProductDAO.addProduct(product);
    }

    public static void editProduct(Product product) {
        ProductDAO.editProduct(product);
    }

    public static Product getProduct(int id) {
        return ProductDAO.getProduct(id);
    }
}
