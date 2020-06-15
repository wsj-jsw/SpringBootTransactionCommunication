package com.wsj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wsj.dataobject.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
