package business;

import dataAccess.ProductDAO;
import dataAccess.ReflectionDAO;
import model.Product;

public class ProductBLL {
    public static void addProduct(Product product) {
        ReflectionDAO.addElement(product);
    }

    public static void editProduct(Product product) {
        ProductDAO.editProduct(product);
    }

    public static Product getProduct(int id) {
        return ProductDAO.getProduct(id);
    }
}
