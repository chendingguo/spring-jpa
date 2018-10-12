package com.reyun.adi.account.service.impl;

import com.reyun.adi.account.model.Media;
import com.reyun.adi.account.repository.MediaRepository;
import com.reyun.adi.account.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {
    @Autowired
    MediaRepository mediaRepository;

    @Override
    public Page<Media> listMedia(int pageIndex, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize, sort);
        return mediaRepository.findAll(pageable);
    }

    @Override
    public Page<Media> findMediaByStatus(int pageIndex, int pageSize, int status) {
        Pageable pageable = new PageRequest(pageIndex - 1, pageSize);
        return mediaRepository.findMediaByStatus(status,pageable);
    }


}
