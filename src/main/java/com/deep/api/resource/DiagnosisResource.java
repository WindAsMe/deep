package com.deep.api.resource;

import com.deep.api.request.DiagnosisRequest;
import com.deep.domain.model.DiagnosisPlanModel;
import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.service.DiagnosisPlanService;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/18  11:46
 */
@RestController
@RequestMapping(value = "/diagnosis")
public class DiagnosisResource {

    private final Logger logger = LoggerFactory.getLogger(DiagnosisResource.class);

    @Resource
    private DiagnosisPlanService diagnosisPlanService;

    /**
     * 添加的接口：/addPlan
     * 接收参数：整个表单信息（所有参数必填）
     * 参数类型为：Long factoryNum;Date diagnosisT;String building;String etB;String operator;String remark;String diagnosisC;String diagnosisM;String drugQ;
     */
    @PostMapping(value = "")
    public Response addPlan(@RequestBody @Valid DiagnosisPlanModel diagnosisPlanModel, BindingResult bindingResult) throws ParseException {

        logger.info("invoke diagnosis/insert {}", diagnosisPlanModel);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("param is error");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param",bindingResult.getAllErrors());
            response.setData(map);
            return response;
        } else {
            int isSuccess = diagnosisPlanService.addPlan(diagnosisPlanModel);
            if (isSuccess <= 0) {
                return Responses.errorResponse("插入错误");
            }
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("id",diagnosisPlanModel.getId());
            response.setData(data);
            return response;
        }
    }

    /**
     * 按照主键删除的接口
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Response dropPlan(@PathVariable("id") int id) {

        logger.info("invoke diagnosis/{}", id);

        int isSuccess =  diagnosisPlanService.dropPlan(id);
        if (isSuccess == 0) {
            return Responses.errorResponse("删除失败");
        }
        Response response = Responses.successResponse();
        return response;
    }

    /**
     * 操作员使用按主键修改的接口：/{id}
     * 操作员使用按主键修改的方法名：changePlanByOperator()
     * 操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @param diagnosisPlanModel
     * @param bindingResult
     * @return
     * @throws ParseException
     */
    @PatchMapping(value = "/{id}")
    public Response changePlanByOperator(@PathVariable(value = "id") int id,
                                         @RequestBody @Valid DiagnosisPlanModel diagnosisPlanModel,
                                         BindingResult bindingResult) throws ParseException {

        if (id < 0) {
          return Responses.errorResponse("id is invalide");
        }

        if (bindingResult.hasErrors()) {
          Response response = Responses.errorResponse("param is error");
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("param",bindingResult.getAllErrors());
          response.setData(map);
          return response;
        }

        diagnosisPlanModel.setId(id);
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("param is error");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("param",bindingResult.getAllErrors());
            response.setData(map);
            return response;
        }

        logger.info("invoke diagnosis/update {}", diagnosisPlanModel);
        int isSuccess = diagnosisPlanService.updateDiagnosisPlanModel(diagnosisPlanModel);
        if (isSuccess == 0) {
            return Responses.errorResponse("修改失败");
        }
        Response response = Responses.successResponse();
        return response;
    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateByProfessor
     * 监督者使用按主键修改的方法名：changePlanByProfessor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @return Response
     */
    @RequestMapping(value = "/p/{id}",method = RequestMethod.PATCH)
    public Response changePlanByProfessor(@PathVariable(value = "id") int id,
                                          @RequestBody Map<String, Integer> json) {
        logger.info("invoke diagnosis/p/{} {}", id,json);
        if (!json.containsKey("isPassCheck")) {
            return Responses.errorResponse("lack isPassCheck");
        }

        short ispassCheck = json.get("isPassCheck").shortValue();
        if (ispassCheck == 2) {
            return Responses.errorResponse("已经审批过了");
        }

        int professorId = json.get("professorId").intValue();

        int isSuccess =  diagnosisPlanService.checkDiagnosisPlanModelById(id, ispassCheck, professorId);
        if (isSuccess == 0) {
            return Responses.errorResponse("错误");
        }
        Response response = Responses.successResponse();
        return response;

    }

    /**
     * 监督者使用按主键修改的接口：/diagnosisUpdateBySupervisor
     * 监督者使用按主键修改的方法名：changePlanBySupervisor()
     * 监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
     * @return response
     */
    @RequestMapping(value = "/s/{id}",method = RequestMethod.PATCH)
    public Response changePlanBySupervisor(@PathVariable(value = "id") int id,
                                           @RequestBody Map<String, Object> json) {

        logger.info("invoke diagnosis/supdate/{} {}", id,json);
        if (!json.containsKey("ispassSup")) {
            return Responses.errorResponse("lack ispassSup");
        }
        short ispassSup = ((Integer)json.get("ispassSup")).shortValue();
        if (ispassSup == 2) {
            return Responses.errorResponse("已经审批过了");
        }

        String upassReason = (String) json.get("upassReason");

        int isSuccess = diagnosisPlanService.supCheckDiagnosisPlanModelById(id, ispassSup, upassReason);
        if (isSuccess == 0) {
            return Responses.errorResponse("监督失败");
        }
        Response response = Responses.successResponse();
        return response;
    }

    /**
     * 按主键查询的接口：/{id}
     * 接收参数：整型的主键号（保留接口查询，前端不调用此接口）
     * @return Response
     */
    @GetMapping(value = "/{id}")
    public Response findPlanById(@PathVariable("id") int id) {
        logger.info("invoke Get diagnosis/{}", id);
        if (id < 0) {
            return Responses.errorResponse("id 无效");
        }

        //查询语句的写法：一定要在声明对象时把值直接赋进去
        DiagnosisPlanModel diagnosisPlanModel = diagnosisPlanService.findPlanById(id);
        if (diagnosisPlanModel == null) {
            return Responses.errorResponse("查询失败");
        }

        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("model", diagnosisPlanModel);
        response.setData(data);
        return response;
    }

    /**
     * 按条件查询接口：/
     * 按条件查询方法名：findPlanSelective()
     * @param diagnosisRequest
     * @return Response
     */
    @GetMapping(value = "")
    public Response findPlanSelective(DiagnosisRequest diagnosisRequest) {
        logger.info("invoke Get diagnosis {}", diagnosisRequest);
        RowBounds rowBounds = new RowBounds(diagnosisRequest.getPage() * diagnosisRequest.getSize(),diagnosisRequest.getSize());
        List<DiagnosisPlanModel> diagnosisPlanModels = diagnosisPlanService.selectDiagnosisPlanModelByDiagnosisRequest(diagnosisRequest, rowBounds);
        System.out.println(diagnosisPlanModels);
        Response response = Responses.successResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("List", diagnosisPlanModels);
        data.put("size", diagnosisPlanModels.size());
        response.setData(data);
        return response;
    }

    @GetMapping(value = "professor/select")
    public Response professorFindPlan(@RequestParam("factoryNum") long factoryNum,
                                      @RequestParam("ispassSup") int ispassSup,
                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                      @RequestParam(value = "size", defaultValue = "10") int size) {
      logger.info("invoke Get diagnosis/professor/select {},{},{},{}", factoryNum,ispassSup,page,size);
      RowBounds rowBounds = new RowBounds(page*size,size);



    }
}
