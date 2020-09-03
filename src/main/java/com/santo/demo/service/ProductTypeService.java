package com.santo.demo.service;

import com.santo.demo.exception.ProductTypeException;
import com.santo.demo.model.ProductType;
import com.santo.demo.repository.ProductTypeRepository;
import com.santo.demo.viewModel.ProductViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {
    @Autowired
    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public ProductType createProductType(ProductViewModel productViewModel) {
        Optional<ProductType> existProductType = productTypeRepository.findByType(productViewModel.getType());
        if (existProductType.isPresent()) {
            throw new ProductTypeException("Type name " + productViewModel.getType() + " was exist");
        }
        ProductType productType = new ProductType();
        productType.setType(productViewModel.getType());
        productType.setCreatedDate(new Date());
        productType.setUpdatedDate(new Date());
        return productTypeRepository.save(productType);
    }

    public Optional<ProductType> getProductTypeById(Long id) {
        return productTypeRepository.findById(id);
    }

    public Optional<ProductType> getProductTypeByTypename(String type) {
        return productTypeRepository.findByType(type);
    }

    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }

    public ProductType updateProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }
}
