package com.reyun.adi.account.service.impl;

import com.reyun.adi.account.model.*;
import com.reyun.adi.account.repository.*;
import com.reyun.adi.account.service.AdiAuthorityService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class AdiAuthorityServiceImpl implements AdiAuthorityService {
    @PersistenceContext
    EntityManager em;


    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    AdiUserRepository adiUserRepository;

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

    @Autowired
    UserCountryRepository userCountryRepository;

    @Override
    public List<Media> listMediaByCountry(String countryIds) {
        //国家为空时返回判断
        if(StringUtils.isEmpty(countryIds)){
            return new ArrayList<>();
        }
        Specification querySpecifi = new Specification<Media>() {
            @Override
            public Predicate toPredicate(Root<Media> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                if (StringUtils.isNotEmpty(countryIds)) {
                    Path<Object> path = root.get("countryId");
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
                    String[] countryIdArray = countryIds.split(",");
                    for (String countryId : countryIdArray) {
                        in.value(Integer.parseInt(countryId));
                    }
                    return criteriaBuilder.and(in);
                }
                return criteriaBuilder.conjunction();

            }
        };
        List<Media> list = mediaRepository.findAll(querySpecifi);
        List<Long> markList = new ArrayList<>();

        //排重
        List<Media> noRepeatElementsList = new ArrayList<>();
        for (Media media : list) {

            media.setMediaId(media.getId());
            media.setMediaName(media.getName());
            if (!markList.contains(media.getId())) {
                noRepeatElementsList.add(media);
            }
        }
        return noRepeatElementsList;
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
    public int createUserTrialCategory(long userId, int zoneId, String catIds) {
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

        StringBuilder sqlBuilder =new StringBuilder("insert into  user_trial_category (user_id,type_id,cat_id,zone_id) values");
        List<UserTrialCategory> userTrailCategories = new ArrayList<>();
        for (String catId : catIdArray) {
            UserTrialCategory userTrailCategory = new UserTrialCategory();
            userTrailCategory.setUserId(userId);
            userTrailCategory.setCatId(Long.parseLong(catId));
            userTrailCategory.setStatus(true);
            userTrailCategory.setTypeId(typeMap.get(Long.parseLong(catId)));
            userTrailCategory.setCreatedTime(new Date());
            userTrailCategory.setModifyTime(new Date());
            userTrailCategory.setZoneId(zoneId);

            userTrailCategories.add(userTrailCategory);
            sqlBuilder.append("(");
            sqlBuilder.append(userTrailCategory.getUserId()).append(",");
            sqlBuilder.append(userTrailCategory.getTypeId()).append(",");
            sqlBuilder.append(userTrailCategory.getCatId()).append(",");
            sqlBuilder.append(userTrailCategory.getZoneId());
            sqlBuilder.append(")").append(",");

        }
        //List list = userTrailCatRepository.save(userTrailCategories);
//        em.flush();
//        em.clear();
      String sql=sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(",")).toString();
       Query query = em.createNativeQuery(sql);
        query.executeUpdate();

        return userTrailCategories.size();


    }

    @Override
    public int deleteUserTrilCategory(long userId,int zoneId) {
        return userTrailCatRepository.deleteByUserId(userId,zoneId);
    }

    @Override
    @Transactional
    public int modifyUserTrialCategory(long userId, int zoneId, String catIds) {
        deleteUserTrilCategory(userId,zoneId);
        if (StringUtils.isNotEmpty(catIds)) {
            int insertResult = createUserTrialCategory(userId, zoneId, catIds);
            return insertResult;
        }
        return 0;
    }

    @Override
    public List<UserTrialCategory> listUserCategory(long userId, int zoneId) {
        UserTrialCategory param = new UserTrialCategory();
        param.setUserId(userId);
        param.setZoneId(zoneId);
        Example<UserTrialCategory> paramExample = Example.of(param);
        return userTrailCatRepository.findAll(paramExample);
    }

    @Override
    public int deleteUserTrilMedia(long userId,int zoneId) {

        return userTrialMediaRepository.deleteByUserId(userId,zoneId);
    }

    @Override
    public int deleteUserCountry(long userId) {

        return userCountryRepository.deleteByUserId(userId);
    }

    @Override
    public int createUserTrialMedia(long userId, int zoneId, String mediaIds) {
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

        StringBuilder sqlBuilder =new StringBuilder("insert into  user_trial_media (user_id,media_id,media_name,zone_id) values");
        List<UserTrialMedia> userTrialMediaList = new ArrayList<>();
        for (String id : idArray) {
            Long mediaId = Long.parseLong(id);
            String mediaName = mediaMap.get(mediaId).getName();
            UserTrialMedia userTrialMedia = new UserTrialMedia();
            userTrialMedia.setMediaId(mediaId);
            userTrialMedia.setMediaName(mediaName);
            userTrialMedia.setUserId(userId);
            userTrialMedia.setStatus(true);
            userTrialMedia.setZoneId(zoneId);
            userTrialMedia.setCreatedTime(new Date());
            userTrialMedia.setModifyTime(new Date());
            userTrialMediaList.add(userTrialMedia);

            sqlBuilder.append("(");
            sqlBuilder.append(userTrialMedia.getUserId()).append(",");
            sqlBuilder.append(userTrialMedia.getMediaId()).append(",");
            sqlBuilder.append("'"+userTrialMedia.getMediaName()+"'").append(",");
            sqlBuilder.append(userTrialMedia.getZoneId());
            sqlBuilder.append(")").append(",");

        }

        String sql=sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(",")).toString();
        Query query = em.createNativeQuery(sql);



        return  query.executeUpdate();

    }

    @Override
    @Transactional
    public int modifyUserTrialMedia(long userId, int zoneId, String mediaIds, String countryIds) {
        deleteUserTrilMedia(userId,zoneId);
        if (StringUtils.isNotEmpty(countryIds) && !"".equals(countryIds)) {
            modifyUserCountry(userId, countryIds);
        } else {
            deleteUserCountry(userId);
        }
        if (StringUtils.isNotEmpty(mediaIds)) {
            return createUserTrialMedia(userId, zoneId, mediaIds);
        } else {
            return 0;
        }


    }

    @Override
    public List<UserTrialMedia> listUserMedia(long userId, int zoneId) {
        UserTrialMedia param = new UserTrialMedia();
        param.setUserId(userId);
        param.setZoneId(zoneId);
        Example<UserTrialMedia> paramExample = Example.of(param);
        return userTrialMediaRepository.findAll(paramExample);
    }

    @Override
    public List<UserCountry> listUserCountry(long userId) {
        UserCountry param = new UserCountry();
        param.setUserId(userId);
        param.setStatus(true);
        Example<UserCountry> example = Example.of(param);
        return userCountryRepository.findAll(example);
    }

    @Override
    @Transactional
    public int modifyUserCountry(long userId, String countryIds) {
        userCountryRepository.deleteByUserId(userId);

        if (countryIds.isEmpty()) {
            return 0;
        }
        String[] idArray = countryIds.split(",");
        //获取 typeid
        List<Long> idList = new ArrayList<>();
        for (String id : idArray) {
            idList.add(Long.parseLong(id));
        }

        Map<Long, Country> countryHashMap = new HashMap<>();
        List<Country> countries = countryRepository.findAll(idList);
        for (Country country : countries) {
            countryHashMap.put(country.getId(), country);
        }


        List<UserCountry> userCountries = new ArrayList<>();
        for (String id : idArray) {
            Long longId = Long.parseLong(id);
            String countryName = countryHashMap.get(longId).getName();
            UserCountry userCountry = new UserCountry();
            userCountry.setUserId(userId);
            userCountry.setCountryId(Integer.parseInt(id));
            userCountry.setCountryName(countryName);
            userCountry.setStatus(true);
            userCountries.add(userCountry);

        }
        return userCountryRepository.save(userCountries).size();


    }

    @Override
    public User modifyUserZone(long userId, int zoneId) {
        User user = adiUserRepository.findOne(userId);
        user.setZoneId(zoneId);
        return adiUserRepository.save(user);
    }
}
