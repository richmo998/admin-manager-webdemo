package com.wonhigh.i18n.ms.web.controller.role;

import com.wonhigh.i18n.ms.common.model.RoleAllocationEntity;
import com.wonhigh.i18n.ms.common.model.RoleEntity;
import com.wonhigh.i18n.ms.manager.role.RoleAllocationManager;
import com.wonhigh.i18n.ms.manager.role.RoleManager;
import com.wonhigh.i18n.ms.service.role.RoleAllocationService;
import com.wonhigh.i18n.ms.service.role.RoleService;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.common.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleAllocationService roleAllocationService;

    @Resource
    private RoleManager roleManager;
    @Resource
    private RoleAllocationManager roleAllocationManager;

    private static final Logger logger = LogManager.getLogger(RoleController.class);
    /**
     * 根据菜单地址找到
     * 角色的加载初始页面
     * @param req
     * @return
     */
    @RequestMapping(value="/role/index")
    public String getRoleIndex(HttpServletRequest req){
        //前端page文件下，相关业务的初始页path
        return "/role/role";
    }

    @RequestMapping(value="/detail/validrolename")
    @ResponseBody
    public Map<String, Object>  queryValUserName(HttpServletRequest req, Model model) throws Exception{
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("result", 0);
        String roleName = req.getParameter("roleName");
        if (StringUtils.isBlank(roleName)) {
            return obj;
        }
        RoleEntity entity = roleManager.getRole(roleName);
        if(null != entity){
            logger.info("当前角色【{}】已存在",roleName);
            obj.put("result", 1);
        }
        return obj;

    }
    /**
     * 根据菜单地址找到
     * 角色分配的加载初始页面
     * @param req
     * @return
     */
    @RequestMapping(value="roleAllocation/index")
    public String getRoleAllocationIndex(HttpServletRequest req){
        //前端page文件下，相关业务的初始页path
        return "/roleAllocation/roleAllocation";
    }
    @RequestMapping(value = "/detail/selectOneRoleDetail")
    @ResponseBody
    public RoleEntity selectOneRoleDetail(HttpServletRequest req, Model model) {
       RoleEntity roleEntity = null;
       String roleId =  req.getParameter("roleId");
       if(StringUtils.isBlank(roleId)){
           return roleEntity;
       }
        try {
            roleEntity = roleManager.getRoleByRoleId(Integer.parseInt(roleId));
        } catch (ManagerException e) {
            e.printStackTrace();
        }
        return roleEntity;
    }

    @RequestMapping(value = "/detail/selectOneRoleAllocationDetail")
    @ResponseBody
    public RoleAllocationEntity selectOneRoleAllocationDetail(HttpServletRequest req, Model model) throws Exception {
        RoleAllocationEntity roleEntity = null;
        String id =  req.getParameter("id");
        if(StringUtils.isBlank(id)){
            return roleEntity;
        }
        Map<String ,Object> parms = new HashMap<>();
        try {
            parms.put("queryCondition"," and id = "+id);
            List<RoleAllocationEntity> roleAllocationList = roleAllocationManager.getRoleAllocationList(null, parms);
            if(null != roleAllocationList && roleAllocationList.size()>0){
                roleEntity = roleAllocationList.get(0);
            }

        } catch (ManagerException e) {

            e.printStackTrace();
        }
        return roleEntity;

    }
    /**
     * 更新角色信息
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detail/addRole")
    @ResponseBody
    public Map<String, Object> addRole(HttpServletRequest req, Model model) throws Exception {
        RoleEntity entity = toRoleEntity(req);
        int result;
        Map<String, Object> obj = new HashMap<String, Object>();

        try {
            if (StringUtils.isBlank(req.getParameter("roleName"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "角色名称不能为空");
                return obj;
            }
            roleManager.add(entity);
            result = 1;
            obj.put("result", result);
        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    /**
     * 添加角色分配信息
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detail/addRoleAllocation")
    @ResponseBody
    public Map<String, Object> addRoleAllocation(HttpServletRequest req, Model model) throws Exception {
        RoleAllocationEntity entity = toRoleAllocationEntity(req);
        int result;
        Map<String, Object> obj = new HashMap<String, Object>();
        try {
            if (StringUtils.isBlank(req.getParameter("roleName"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "角色名称不能为空");
                return obj;
            }
            if (StringUtils.isBlank(req.getParameter("uri"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "分配菜单uri不能为空");
                return obj;
            }
            roleAllocationManager.add(entity);
            result = 1;
            obj.put("result", result);
        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    /**
     * 更新角色信息
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detail/modifyRole")
    @ResponseBody
    public Map<String, Object> modifyRole(HttpServletRequest req, Model model) throws Exception {
         RoleEntity entity = toRoleEntity(req);
        int result;
        Map<String, Object> obj = new HashMap<String, Object>();

        try {
            if (StringUtils.isBlank(req.getParameter("roleName"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "角色名称不能为空");
                return obj;
            }
            roleManager.updateRole(entity);
            result = 1;
            obj.put("result", result);
        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        return obj;

    }

    /**
     * 更新角色分配信息
     * @param req
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "detail/modifyRoleAllocation")
    @ResponseBody
    public Map<String, Object> modifyRoleAllocation(HttpServletRequest req, Model model) throws Exception {
        RoleAllocationEntity entity = toRoleAllocationEntity(req);
        int result;
        Map<String, Object> obj = new HashMap<String, Object>();

        try {
            if (StringUtils.isBlank(req.getParameter("roleName"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "角色名称不能为空");
                return obj;
            }
            if (StringUtils.isBlank(req.getParameter("uri"))) {
                result = -1;
                obj.put("result", result);
                obj.put("errorMsg", "分配菜单uri不能为空");
                return obj;
            }
            roleAllocationManager.updateRoleAllocation(entity);
            result = 1;
            obj.put("result", result);
        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        return obj;

    }
    /**
     * 获取角色分页列表
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/roleList")
    @ResponseBody
    public Map<String, Object> queryRoleListForPage(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = setParams(req);

        int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));

        List<RoleEntity> roleEntityList = null;
        int number = 0;
        try {
            number = roleService.findCount(paramMap);
            roleEntityList = roleManager.queryRoleListForPage(paramMap, pageNo, pageSize);

        } catch (ManagerException e1) {
            e1.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("total", number);
        obj.put("rows", roleEntityList);
        return obj;
    }

    /**
     * 获取角色列表
     * 所有数据
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/allRoleList")
    @ResponseBody
    public Map<String, Object> queryAllRoleList(HttpServletRequest req, Model model) {

        List<RoleEntity> roleEntityList = null;
        try {
            roleEntityList = roleManager.getRoleList(null,null);

        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("roleList", roleEntityList);
        return obj;
    }
    /**
     * 获取所有菜单数据
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/allMenuList")
    @ResponseBody
    public Map<String,Object> getAllMenuList(HttpServletRequest req, Model model){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("role_id","1");
        List<RoleAllocationEntity> menuList = null;
        try {
            menuList = roleAllocationManager.getRoleAllocationList(null,paramMap);

        } catch (ManagerException e1) {
            e1.printStackTrace();
        }
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("menuList", menuList);
        return obj;
    }

    /**
     * 获取分配权限列表
     * @param req
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/roleAllocationList")
    @ResponseBody
    public Map<String, Object> queryRoleAllocationListForPage(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = setParams(req);

        int pageNo = StringUtils.isEmpty(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page"));
        int pageSize = StringUtils.isEmpty(req.getParameter("rows")) ? 20 : Integer.parseInt(req.getParameter("rows"));

        List<RoleAllocationEntity> roleEntityList = null;
        int number = 0;
        try {
            number = roleAllocationService.findCount(paramMap);
            roleEntityList = roleAllocationManager.queryRoleAllocationListForPage(paramMap, pageNo, pageSize);

        } catch (ManagerException e1) {
            e1.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("total", number);
        obj.put("rows", roleEntityList);
        return obj;
    }
    private Map<String, Object> setParams(HttpServletRequest req) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        if (req.getParameter("createTime") != null && !req.getParameter("createTime").equals("")) {
            paramMap.put("createTime", req.getParameter("createTime"));
        }
        if (req.getParameter("updateTime") != null && !"".equals(req.getParameter("updateTime"))) {
            paramMap.put("updateTime", req.getParameter("updateTime"));
        }
        if (req.getParameter("enable") != null && !"".equals(req.getParameter("enable"))) {
            paramMap.put("enable", req.getParameter("enable"));
        }
        if (req.getParameter("roleName") != null && !"".equals(req.getParameter("roleName"))) {
            paramMap.put("roleName", req.getParameter("roleName"));
        }
        //此参数用于精确查询
        if (req.getParameter("role_id") != null && !"".equals(req.getParameter("role_id"))) {
            paramMap.put("role_id", req.getParameter("role_id"));
        }
        //此参数用于模糊查询
        if (req.getParameter("roleId") != null && !"".equals(req.getParameter("roleId"))) {
            paramMap.put("roleId", req.getParameter("roleId"));
        }

        if (req.getParameter("remark") != null && !"".equals(req.getParameter("remark"))) {
            paramMap.put("remark", req.getParameter("remark"));
        }
        if (req.getParameter("uri") != null && !"".equals(req.getParameter("uri"))) {
            paramMap.put("uri", req.getParameter("uri"));
        }
        if (req.getParameter("menuName") != null && !"".equals(req.getParameter("menuName"))) {
            paramMap.put("menuName", req.getParameter("menuName"));
        }
        return paramMap;
    }

    /**
     * 转角色对象
     * @param req
     * @return
     */
    private RoleEntity toRoleEntity(HttpServletRequest req) {
        RoleEntity roleEntity = new RoleEntity();
        if (req.getParameter("enable") != null && !"".equals(req.getParameter("enable"))) {
            roleEntity.setEnable( req.getParameter("enable"));
        }
        if (req.getParameter("roleId") != null && !"".equals(req.getParameter("roleId"))) {
            roleEntity.setRoleId( Integer.parseInt(req.getParameter("roleId")));
        }
        if (req.getParameter("roleName") != null && !"".equals(req.getParameter("roleName"))) {
            roleEntity.setRoleName( req.getParameter("roleName"));
        }

        if (req.getParameter("remark") != null && !"".equals(req.getParameter("remark"))) {
            roleEntity.setRemark( req.getParameter("remark"));
        }
        return roleEntity;
    }

    /**
     * 转角色分配对象
     * @param req
     * @return
     */
    private RoleAllocationEntity toRoleAllocationEntity(HttpServletRequest req) {
        RoleAllocationEntity roleAllocationEntity = new RoleAllocationEntity();
        if (StringUtils.isNotBlank(req.getParameter("id"))) {
            roleAllocationEntity.setId( Integer.parseInt(req.getParameter("id")));
        }
        if (req.getParameter("roleId") != null && !"".equals(req.getParameter("roleId"))) {
            roleAllocationEntity.setRoleId( Integer.parseInt(req.getParameter("roleId")));
        }
        if (req.getParameter("roleName") != null && !"".equals(req.getParameter("roleName"))) {
            roleAllocationEntity.setRoleName( req.getParameter("roleName"));
        }
        if (req.getParameter("uri") != null && !"".equals(req.getParameter("uri"))) {
            roleAllocationEntity.setUri( req.getParameter("uri"));
        }
        if (req.getParameter("menuName") != null && !"".equals(req.getParameter("menuName"))) {
            roleAllocationEntity.setMenuName( req.getParameter("menuName"));
        }
        if (req.getParameter("enable") != null && !"".equals(req.getParameter("enable"))) {
            roleAllocationEntity.setEnable( req.getParameter("enable"));
        }
        if (req.getParameter("remark") != null && !"".equals(req.getParameter("remark"))) {
            roleAllocationEntity.setRemark( req.getParameter("remark"));
        }
        return roleAllocationEntity;
    }
}
