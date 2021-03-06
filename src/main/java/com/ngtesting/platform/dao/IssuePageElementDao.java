package com.ngtesting.platform.dao;

import com.ngtesting.platform.model.IsuPageElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IssuePageElementDao {
    void save(IsuPageElement elm);

    void updateProp(@Param("id") String id,
                    @Param("prop") String prop,
                    @Param("val") String val,
                    @Param("orgId") Integer orgId);

    void saveOrdrs(@Param("maps") List<Map> maps,
                   @Param("tabId") Integer tabId,
                   @Param("orgId") Integer orgId);

    void removeOthers(@Param("maps") List<Map> maps,
                      @Param("tabId") Integer tabId,
                      @Param("orgId") Integer orgId);

//    void add(IsuPageElement element);
//    Integer remove(@Param("id") Integer id, @Param("orgId") Integer orgId);
//    Integer getMaxFieldOrdr(Integer tabId);
}