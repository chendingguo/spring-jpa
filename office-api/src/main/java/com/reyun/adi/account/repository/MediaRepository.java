package com.reyun.adi.account.repository;


import com.reyun.adi.account.model.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MediaRepository  extends JpaRepository<Media, Long>, JpaSpecificationExecutor<Media> {



}
