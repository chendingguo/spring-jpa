package com.reyun.adi.account.service;

import com.reyun.adi.account.model.Media;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaService {
     Page<Media> listMedia(int pageIndex, int pageSize);

    Page<Media> findMediaByStatus(int pageIndex, int pageSize,int status);
}
