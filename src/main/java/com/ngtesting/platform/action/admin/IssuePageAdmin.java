package com.ngtesting.platform.action.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ngtesting.platform.action.BaseAction;
import com.ngtesting.platform.config.Constant;
import com.ngtesting.platform.model.IsuField;
import com.ngtesting.platform.model.IsuPage;
import com.ngtesting.platform.model.IsuPageTab;
import com.ngtesting.platform.model.TstUser;
import com.ngtesting.platform.service.IssueFieldService;
import com.ngtesting.platform.service.IssuePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(Constant.API_PATH_ADMIN + "issue_page/")
public class IssuePageAdmin extends BaseAction {
	@Autowired
	IssuePageService pageService;

    @Autowired
    IssueFieldService fieldService;

	@RequestMapping(value = "load", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> load(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();

		TstUser userVo = (TstUser) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_PROFILE);
		Integer orgId = userVo.getDefaultOrgId();

		List<IsuPage> pages = pageService.list(orgId);

		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		ret.put("pages", pages);
		return ret;
	}

	@RequestMapping(value = "get", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> get(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();

		TstUser userVo = (TstUser) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_PROFILE);
		Integer orgId = userVo.getDefaultOrgId();

		Integer pageId = json.getInteger("id");
		IsuPage page = null;
		if (pageId == null) {
			page = new IsuPage();
            IsuPageTab tab = new IsuPageTab();
            tab.setId(-1);
            page.getTabs().add(tab);
		} else {
			page = pageService.get(pageId, orgId);
		}

		ret.put("page", page);
		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}

    @RequestMapping(value = "getDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getDetail(HttpServletRequest request, @RequestBody JSONObject json) {
        Map<String, Object> ret = new HashMap<String, Object>();

        TstUser userVo = (TstUser) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_PROFILE);
        Integer orgId = userVo.getDefaultOrgId();

        Integer pageId = json.getInteger("id");
        IsuPage page = pageService.get(pageId, orgId);

        IsuPageTab tab = page.getTabs().get(0);

        List<IsuField> fields = fieldService.listOrgField(orgId, tab.getId());

        ret.put("page", page);
        ret.put("fields", fields);
        ret.put("code", Constant.RespCode.SUCCESS.getCode());
        return ret;
    }

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();

		TstUser userVo = (TstUser) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_PROFILE);
		Integer orgId = userVo.getDefaultOrgId();

		IsuPage page = JSON.parseObject(JSON.toJSONString(json), IsuPage.class);
        pageService.save(page, orgId);

		page = pageService.get(page.getId(), orgId);
        IsuPageTab tab = page.getTabs().get(0);

		List<IsuField> fields = fieldService.listOrgField(orgId, tab.getId());

		ret.put("page", page);
        ret.put("fields", fields);

		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request, @RequestBody JSONObject json) {
		Map<String, Object> ret = new HashMap<String, Object>();

		TstUser userVo = (TstUser) request.getSession().getAttribute(Constant.HTTP_SESSION_USER_PROFILE);
		Integer orgId = userVo.getDefaultOrgId();

		Integer id = json.getInteger("id");

		boolean success = pageService.delete(id, orgId);

		ret.put("code", Constant.RespCode.SUCCESS.getCode());
		return ret;
	}

}
