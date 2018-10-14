package com.reyun.adi.account.service.impl;

import com.reyun.adi.account.model.*;
import com.reyun.adi.account.repository.*;
import com.reyun.adi.account.service.AdiAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.tree.ExpandVetoException;
import java.util.*;

@Service
public class AdiAuthorityServiceImpl implements AdiAuthorityService {
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    MediaRepository mediaRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ContinentRepository continentRepository;

    @Autowired
    UserTrialCategoryRepository userTrailCatRepository;

    @Autowired
    UserTrialMediaRepository userTrialMediaRepository;


    @Override
    public List<Media> listMediaByCountry(int countryId) {
        Specification querySpecifi = new Specification<Media>() {
            @Override
            public Predicate toPredicate(Root<Media> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                predicates.add(criteriaBuilder.equal(root.get("countryId"), countryId));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return mediaRepository.findAll(querySpecifi);
    }

    @Override
    public List<ProductType> listProductCategory(int typeId) {
        Map<Integer, List<ProductCategory>> maps = new HashMap<>();

        Specification querySpecifi = new Specification<ProductCategory>() {
            @Override
            public Predicate toPredicate(Root<ProductCategory> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                predicates.add(criteriaBuilder.notEqual(root.get("name"), "--"));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<ProductCategory> categories = productCategoryRepository.findAll(querySpecifi);
        for (ProductCategory category : categories) {
            List<ProductCategory> productCategories;
            if (maps.get(category.getTypeId()) != null) {
                productCategories = maps.get(category.getTypeId());
                productCategories.add(category);

            } else {
                productCategories = new ArrayList<>();
                productCategories.add(category);

            }

            maps.put(category.getTypeId(), productCategories);
        }

        Sort sort = new Sort(Sort.Direction.ASC, "id");
        List<ProductType> productTypes = productTypeRepository.findAll(sort);

        for (ProductType productType : productTypes) {
            List<ProductCategory> cats = maps.get(productType.getId());
            productType.setProductCategoryList(cats);
        }
        return productTypes;

    }

    @Override
    public List<Country> listCountryByContinent(int continentId) {
        Specification querySpecifi = new Specification<Country>() {
            @Override
            public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (continentId > -1) {
                    predicates.add(criteriaBuilder.equal(root.get("continentId"), continentId));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return countryRepository.findAll(querySpecifi);
    }

    @Override
    public List<Continent> listContinent() {
        return continentRepository.findAll();
    }

    @Override
    public List<Map<String, Object>> listAllCountries() {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Continent> continents = continentRepository.findAll();
        for (Continent continent : continents) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", continent.getId());
            map.put("name", continent.getName());
            Country countryParam = new Country();
            countryParam.setContinentId(continent.getId());
            Example<Country> example = Example.of(countryParam);
            List<Country> countries = countryRepository.findAll(example);
            map.put("countryList", countries);
            list.add(map);

        }
        return list;

    }

    @Override
    @Transactional
    public int createUserTrialCategory(long userId, int zoonId, String catIds) {
        if (catIds.isEmpty()) {
            return 0;
        }
        String[] catIdArray = catIds.split(",");
        //获取 typeid
        List<Long> catIdList = new ArrayList<>();
        for (String catId : catIdArray) {
            catIdList.add(Long.parseLong(catId));
        }

        Map<Long, Integer> typeMap = new HashMap<>();
        List<ProductCategory> productCategories = productCategoryRepository.findAll(catIdList);
        for (ProductCategory productCategory : productCategories) {
            typeMap.put(productCategory.getId(), productCategory.getTypeId());
        }


        List<UserTrailCategory> userTrailCategories = new ArrayList<>();
        for (String catId : catIdArray) {
            UserTrailCategory userTrailCategory = new UserTrailCategory();
            userTrailCategory.setUserId(userId);
            userTrailCategory.setCatId(Long.parseLong(catId));
            userTrailCategory.setStatus(true);
            userTrailCategory.setTypeId(typeMap.get(Long.parseLong(catId)));
            userTrailCategory.setCreatedTime(new Date());
            userTrailCategory.setModifyTime(new Date());
            userTrailCategory.setZoneId(zoonId);
            userTrailCategories.add(userTrailCategory);
        }
        List list = userTrailCatRepository.save(userTrailCategories);
        return list.size();


    }

    @Override
    public int deleteUserTrilCategory(long userId) {
        return userTrailCatRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public int modifyUserTrialCategory(long userId, int zoonId, String catIds) {
        deleteUserTrilCategory(userId);
        int insetResult = createUserTrialCategory(userId, zoonId, catIds);
        return insetResult;
    }

    @Override
    public int deleteUserTrilMedia(long userId) {
        return userTrialMediaRepository.deleteByUserId(userId);
    }

    @Override
    public int createUserTrialMedia(long userId, int zoonId, String mediaIds) {
        if (mediaIds.isEmpty()) {
            return 0;
        }
        String[] idArray = mediaIds.split(",");
        //获取 typeid
        List<Long> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Long.parseLong(id));
        }

        Map<Long, Media> mediaMap = new HashMap<>();
        List<Media> medias = mediaRepository.findAll(idList);
        for (Media media : medias) {
            mediaMap.put(media.getId(), media);
        }


        List<UserTrialMedia> userTrialMediaList = new ArrayList<>();
        for (String id : idArray) {
            Long mediaId = Long.parseLong(id);
            String mediaName = mediaMap.get(mediaId).getName();
            UserTrialMedia userTrialMedia = new UserTrialMedia();
            userTrialMedia.setMediaId(mediaId);
            userTrialMedia.setMediaName(mediaName);
            userTrialMedia.setUserId(userId);
            userTrialMedia.setStatus(true);
            userTrialMedia.setZoonId(zoonId);
            userTrialMedia.setCreatedTime(new Date());
            userTrialMedia.setModifyTime(new Date());
            userTrialMediaList.add(userTrialMedia);

        }
        return userTrialMediaRepository.save(userTrialMediaList).size();

    }

    @Override
    public int modifyUserTrialMddia(long userId, int zoonId, String mediaIds) {
        deleteUserTrilMedia(userId);
        return createUserTrialMedia(userId, zoonId, mediaIds);

    }
}
