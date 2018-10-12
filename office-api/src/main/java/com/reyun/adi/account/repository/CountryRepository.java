package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.Country;
import com.reyun.adi.account.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {



}
