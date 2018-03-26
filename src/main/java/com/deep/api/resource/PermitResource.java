package com.deep.api.resource;

import com.deep.api.authorization.annotation.Permit;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.PermitModel;
import com.deep.domain.service.PermitService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.HashMap;

@RestController
@RequestMapping(value = "permit")
public class PermitResource {
    @Resource
    private PermitService permitService;

    /**
     * 展示所有的权限,不更新新的页面
     * @return 展示所有权限的json格式
     */
    @Permit(modules = "permit")
    @GetMapping(value = "/")
    public Response permitLists() {
        Response response = Responses.successResponse();

        HashMap<String, Object> data = new HashMap<>();
        data.put("allPermit", permitService.getAll());
        response.setData(data);

        return response;
    }

    /**
     * 添加一个权限
     * @param permitModel
     * @param bindingResult
     * @return
     */
    @Permit(modules = "permit")
    @PostMapping(value = "/add")
    public Response addRole(@Valid PermitModel permitModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Responses.errorResponse("添加角色出错,请检查网络后重试");
        } else {
            permitModel.setPermitId(permitModel.getPermitId());
            permitModel.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            permitModel.setGmtModified(new Timestamp(System.currentTimeMillis()));

            Response response = Responses.successResponse();

            HashMap<String, Object> data = new HashMap<>();
            data.put("success", permitService.addPermit(permitModel));
            response.setData(data);
            return response;
        }
    }

}