package com.deep.api.resource;

import com.deep.api.Utils.StringToLongUtil;
import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.RoleModel;
import com.deep.domain.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.ws.rs.HEAD;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "role")
public class RoleResource {

    private final Logger logger = LoggerFactory.getLogger(RoleResource.class);

    @Resource
    private RoleService roleService;

    /**
     * 查看所有的角色
     * @return json数据返回所有角色
     */
    @Permit(authorities = "query_role")
    @GetMapping(value = "/")
    public Response roleLists() {

        logger.info("invoke roleLists, url is role/");

        List<RoleModel> roleModels = roleService.getAll();
        if (roleModels.size() <= 0) {
            return Responses.errorResponse("获取角色信息失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("allRole", roleModels);
        data.put("number", roleModels.size());
        response.setData(data);
        return response;
    }

    /**
     * 添加一个角色
     * @param roleModel
     * @param bindingResult
     * @return
     */
    @Permit(authorities = "add_role")
    @PostMapping(value = "/add")
    public Response addRole(@Valid @RequestBody RoleModel roleModel, BindingResult bindingResult) {

        logger.info("invoke addRole{}, url is role/add", roleModel, bindingResult);

        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("添加角色出错,请检查网络后重试");
        } else {
            roleModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
            if (roleModel.getDefaultPermit().equals("")) {
                // 默认权限的位数为64*3 = 192位, in database, it uses 192 Bytes
                roleModel.setDefaultPermit("0000000000000000000000000000000000000000000000000000000000000000" +
                                           "0000000000000000000000000000000000000000000000000000000000000000" +
                                           "0000000000000000000000000000000000000000000000000000000000000000");
            }
            Long addId = roleService.addRole(roleModel);
            if (addId <= 0) {
                return Responses.errorResponse("添加失败");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("oneAgent", addId);
            response.setData(data);
            return response;
        }
    }

    /**
     * 根据权限的主键查找一个角色
     * @param id 主键
     * @return
     */
    @Permit(authorities = "query_role")
    @GetMapping(value = "/{id}")
    public Response findRole(@PathVariable("id")String id) {

        logger.info("invoke findRole{}", id);

        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        RoleModel roleModel = roleService.getOneRole(uid);
        if (roleModel == null) {
            return Responses.errorResponse("查询失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", roleModel);
        response.setData(data);
        return response;
    }

    /**
     * 删除一个角色
     * @param id
     */
    @Permit(authorities = "remove_role")
    @DeleteMapping(value = "/{id}")
    public Response deleteRole(@PathVariable("id")String id) {

        logger.info("invoke deleteRole{}, url is role/{id}", id);

        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        Long deleteID = roleService.deleteRole(uid);
        if (deleteID <= 0) {
            return Responses.errorResponse("删除失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneAgent", deleteID);
        response.setData(data);
        return response;
    }

    /**
     * 修改权限方法
     * @param roleModel
     * @return
     */
    @Permit(authorities = "modify_role")
    @PutMapping(value = "/{id}")
    public Response roleUpdate(@RequestBody @Valid RoleModel roleModel, @PathVariable("id") String id, BindingResult bindingResult) {
        logger.info("invoke roleUpdate{}, url is role/{id}", roleModel, id, bindingResult);
        long uid = StringToLongUtil.stringToLong(id);
        if (uid == -1) {
            return Responses.errorResponse("查询错误");
        }
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("修改角色失败");
        }
        RoleModel middle = roleService.getOneRole(uid);
        if (middle == null) {
            return Responses.errorResponse("修改失败");
        }
        roleModel.setId(uid);
        roleModel.setGmtCreate(middle.getGmtCreate());
        roleModel.setGmtModified(new Timestamp(System.currentTimeMillis()));
        Long updateId = roleService.updateRole(roleModel);
        if (updateId <= 0) {
            return Responses.errorResponse("修改失败");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("oneUser", updateId);
        response.setData(data);
        return response;
    }
}
