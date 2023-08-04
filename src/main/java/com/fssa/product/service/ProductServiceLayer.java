package com.fssa.product.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.fssa.product.dao.ProductDao;
import com.fssa.product.exception.DaoException;
import com.fssa.product.exception.DaoExceptionErrors;
import com.fssa.product.exception.ValidatorIntializationException;
import com.fssa.product.model.Product;
import com.fssa.product.validator.ProductValidator;

/**
 * The service layer class that acts as an intermediary between the application's business logic and data access layer.
 */
public class ProductServiceLayer {

    // Instance variables for HospitalValidator and HospitalDao
    public static  ProductValidator ProductValidator;
    public static  ProductDao ProductDao;

    // Constructor to initialize the service layer with HospitalValidator and HospitalDao instances.
    public ProductServiceLayer(ProductValidator ProductValidator, ProductDao ProductDao) {
        this.ProductValidator = ProductValidator;
        this.ProductDao = ProductDao;
    }

    /**
     * Adds a new Product to the system if it passes validation checks.
     *
     * @param Product The Product object to be added.
     * @return true if the Product is successfully added, false otherwise.
     * @throws IllegalArgumentException      if the Product object is null.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     */
    public static boolean addProduct(Product Product)
            throws IllegalArgumentException,  SQLException, DaoException, ValidatorIntializationException {
       
        if (Product == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        if (ProductValidator.validate(Product)) {
            return ProductDao.addProduct(Product);
        } else {
            return false;
        }
    }

    /**
     * Updates an existing hospital in the system if it passes validation checks.
     *
     * @param hospital The updated hospital object.
     * @return true if the hospital is successfully updated, false otherwise.
     * @throws IllegalArgumentException      if the hospital object is null.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     */
    public boolean updateProduct(Product Product)
            throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
        System.out.println(Product);
        if (Product == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        if (ProductValidator.validate(Product)) {
            return ProductDao.update(Product);
        } else {
            return false;
        }
    }

    /**
     * Retrieves a list of all hospitals from the system.
     *
     * @return An ArrayList containing all hospitals in the system.
     * @throws IllegalArgumentException      if there is an issue with initializing the validator.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     */
    public ArrayList ReadProduct()
            throws IllegalArgumentException, ValidatorIntializationException, SQLException, DaoException {
        return ProductDao.readFullProductList();
    }

    /**
     * Deletes a hospital from the system by its name.
     *
     * @param name The name of the hospital to be deleted.
     * @return true if the hospital is successfully deleted, false otherwise.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     * @throws com.fssa.product.exception.DaoException 
     */
    public boolean deleteProduct(String name) throws SQLException, DaoException, ValidatorIntializationException {
        if (name == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        if (ProductValidator.validateProductName(name)) {
            return ProductDao.deleteProduct(name);
        } else {
            return false;
        }
    }

    /**
     * Finds a hospital in the system by its name.
     *
     * @param name The name of the hospital to be found.
     * @return The Hospital object if found, null otherwise.
     * @throws SQLException                  if there is an issue with the SQL database operation.
     * @throws DaoException                  if there is an issue with the data access layer.
     * @throws ValidatorInitializationException if there is an issue with initializing the validator.
     */
    public Product FindByNameProduct(String name)
            throws SQLException, DaoException, ValidatorIntializationException {
        if (name == null) {
            throw new IllegalArgumentException(DaoExceptionErrors.INVALID_INPUT);
        }
        if (ProductValidator.validateProductName(name)) {
            return ProductDao.findProductByName(name);
        } else {
            return null;
        } 
    }
}

