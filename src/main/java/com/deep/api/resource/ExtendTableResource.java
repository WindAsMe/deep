package com.deep.api.resource;

import com.deep.api.Utils.ExtendTableUtil;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.ExtendModel;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拓展数据库的功能
 * 此功能只有管理员拥有权限
 */
@RestController
@RequestMapping(value = "extend")
public class ExtendTableResource {
    /**
     * 方法功能: 创建数据库
     * @param extendModel
     * @return
     */
    @PostMapping(value = "/add/")
    public Response createTable(@RequestBody ExtendModel extendModel) {
        if (!ExtendTableUtil.createTable(extendModel.getTableName(), extendModel.getColumns())) {
            return Responses.errorResponse("failed to create a table");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 方法功能:删除一个数据表
     * @param tableName
     * @return
     */
    @DeleteMapping(value = "/delete/{tableName}")
    public Response deleteTable(@PathVariable("tableName") String tableName) {
        System.out.println(tableName);
        if (!ExtendTableUtil.dropTable(tableName)) {
            return Responses.errorResponse("failed to drop a table");
        } else {
            return Responses.successResponse();
        }
    }

    /**
     * 方法功能:获取用户自定义的表格信息
     * @return
     */
    @GetMapping(value = "/tableLists")
    public Response getAllTable() {
        Map<String, Object> userExtendedTables =  ExtendTableUtil.getAllTable();
        if (userExtendedTables == null) {
            return Responses.errorResponse("查询错误!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", userExtendedTables);
        hashMap.put("size", userExtendedTables.size());
        response.setData(hashMap);
        return response;
    }

    /**
     * 获取用户自定义表的列名称
     */
    @GetMapping(value = "/columnLists/{tableName}")
    public static Response getTableColumns(@PathVariable("tableName") String tableName) {
        Map<String, String> userExtendedTablesColumns =  ExtendTableUtil.getAllColumns(tableName);
        if (userExtendedTablesColumns == null) {
            return Responses.errorResponse("查询错误!");
        }
        Response response = Responses.successResponse();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", userExtendedTablesColumns);
        hashMap.put("size", userExtendedTablesColumns.size());
        response.setData(hashMap);
        return response;
    }
}
