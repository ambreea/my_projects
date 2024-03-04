package org.andreea.springmvcshoppingcart.dao;

import org.andreea.springmvcshoppingcart.entity.Product;
import org.andreea.springmvcshoppingcart.model.PaginationResult;
import org.andreea.springmvcshoppingcart.model.ProductInfo;

public interface ProductDAO {



    public Product findProduct(String code);

    public ProductInfo findProductInfo(String code) ;


    public PaginationResult<ProductInfo> queryProducts(int page,
                                                       int maxResult, int maxNavigationPage  );

    public PaginationResult<ProductInfo> queryProducts(int page, int maxResult,
                                                       int maxNavigationPage, String likeName);

    public void save(ProductInfo productInfo);

    public void removeProduct(String code);

}