package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.Continent;
import com.reyun.adi.account.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContinentRepository extends JpaRepository<Continent, Long>, JpaSpecificationExecutor<Continent> {



}
