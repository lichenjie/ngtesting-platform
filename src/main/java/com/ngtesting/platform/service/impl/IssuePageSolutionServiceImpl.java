package com.ngtesting.platform.service.impl;

import com.ngtesting.platform.dao.IssuePageSolutionDao;
import com.ngtesting.platform.model.IsuPageSolution;
import com.ngtesting.platform.model.IsuPageSolutionItem;
import com.ngtesting.platform.service.IssuePageSolutionService;
import com.ngtesting.platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class IssuePageSolutionServiceImpl extends BaseServiceImpl implements IssuePageSolutionService {
    @Autowired
    UserService userService;

    @Autowired
    IssuePageSolutionDao pageSolutionDao;

    @Override
    public List<IsuPageSolution> list(Integer orgId) {
        return pageSolutionDao.list(orgId);
    }

    @Override
    public IsuPageSolution get(Integer solutionId, Integer orgId) {
        return pageSolutionDao.get(solutionId, orgId);
    }

    @Override
    public Map<String, Map<String, String>> getItemsMap(Integer solutionId, Integer orgId) {
        List<IsuPageSolutionItem> items = pageSolutionDao.getItems(solutionId, orgId);

        Map<String, Map<String, String>> map = new LinkedHashMap<>();
        for (IsuPageSolutionItem item : items) {
            String typeKey = item.getIssueTypeId() + "-" + item.getIssueTypeName();
            if (!map.containsKey(typeKey)) {
                map.put(typeKey, new LinkedHashMap<>());
            }

            String pageKey = item.getIssuePageId() + "-" + item.getIssuePageName();
            map.get(typeKey).put(item.getOpt().toString(), pageKey);
        }

        return map;
    }

    @Override
    public IsuPageSolution save(IsuPageSolution vo, Integer orgId) {
        if (vo.getId() == null) {

            vo.setOrgId(orgId);
            pageSolutionDao.save(vo);
        } else {
            Integer count = pageSolutionDao.update(vo);
            if (count == 0) {
                return null;
            }
        }

        return vo;
    }

    @Override
    public boolean delete(Integer id, Integer orgId) {
        Integer count = pageSolutionDao.delete(id, orgId);
        if (count == 0) {
            return false;
        }

        return true;
    }
}
