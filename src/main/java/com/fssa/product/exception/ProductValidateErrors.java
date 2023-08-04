package com.fssa.product.exception;

/**
 * An interface containing constant error messages for Product data validation.
 * These error messages can be used to identify specific issues when validating Product-related data.
 */
public interface ProductValidateErrors {

    /**
     * Represents an error message for cases where the Product is null or empty.
     */
    public static final String INVALID_PRODUCT_NULL = "Product can't be null or empty";

    /**
     * Represents an error message for cases where the Product name is invalid or empty.
     */
    public static final String INVALID_PRODUCT_NAME = "ProductName Invalid or empty";

    /**
     * Represents an error message for cases where the product ID is less than or equal to 0.
     */
    public static final String INVALID_PRODUCT_ID = "ProductId must be greater than 0";

    /**
     * Represents an error message for cases where the ProductDescription  is null or empty.
     */
    public static final String INVALID_PRODUCTDESCRIPTION_NULL = "ProductDescription can't be null or empty";

    /**
     * Represents an error message for cases where the ProductDescription is invalid.
     */
    public static final String INVALID_PRODUCTDESCRIPTION = "ProductDescription Invalid";

    /**
     * Represents an error message for cases where the URL is null or empty.
     */
    public static final String INVALID_URL_NULL = "URL can't be null or empty";

    /**
     * Represents an error message for cases where the URL is invalid.
     */
    public static final String INVALID_URL = "Invalid URL";
}
