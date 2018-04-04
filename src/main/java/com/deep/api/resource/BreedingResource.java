package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.BreedingPlan;
import com.deep.domain.model.BreedingPlanExample;
import com.deep.domain.model.OtherTime;
import com.deep.domain.service.BreedingPlanService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author: Created  By  Caojiawei
 * date: 2018/2/2  12:52
 */
@Controller
public class BreedingResource {

    @Resource
    private BreedingPlanService breedingPlanService;

    @ResponseBody
    @RequestMapping(value = "/breedingPlan",method = RequestMethod.GET)
    public String helloBreed() {
        return "Hello BreedingPlan!";
    }

//    按主键删除的接口：/breedingInsert
//    按主键删除的方法名：addPlan()
//    接收参数：整个表单信息（所有参数必填）
//    参数类型为：Long factoryNum;String building;String mEtI;String mEtB;String fEtI;String fEtB;Date breedingT; Date gestationT;Date prenatalIT;Date cubT;Integer quantity;String operator;String remark；
    @RequestMapping(value = "/breedingInsert",method = RequestMethod.GET)
    public String addPlan(){
        return "BreedingInsert";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingInsert/show",method = RequestMethod.POST)
    public Response addPlan(@Valid BreedingPlan insert,
                            @Valid OtherTime otherTime,
                            BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案录入失败");
            return response;
        }else {
            Byte zero = 0;
            Date breedingT = new Date();
            Date gestationT = new Date();
            Date prenatalIT = new Date();
            Date cubT = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_breedingT().isEmpty()){
                breedingT =  formatter.parse(otherTime.getS_breedingT());
            }
            if (!otherTime.getS_gestationT().isEmpty()){
                gestationT = formatter.parse(otherTime.getS_gestationT());
            }
            if (!otherTime.getS_prenatalIT().isEmpty()){
                prenatalIT = formatter.parse(otherTime.getS_prenatalIT());
            }
            if (!otherTime.getS_cubT().isEmpty()){
                cubT = formatter.parse(otherTime.getS_cubT());
            }
            insert.setGmtCreate(new Date());
            insert.setBreedingT(breedingT);
            insert.setGestationT(gestationT);
            insert.setPrenatalIT(prenatalIT);
            insert.setCubT(cubT);
            insert.setIsPass(zero);
            insert.setIsPass1(zero);
            breedingPlanService.addPlan(insert);

            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",insert);
            response.setData(data);
            return response;
        }
    }

//    按主键删除的接口：/breedingDeleteById
//    按主键删除的方法名：dropPlan()
//    接收参数：整型id，根据主键号删除
    @RequestMapping(value = "/breedingDeleteById",method = RequestMethod.GET)
    public String dropPlan(){
        return "BreedingDeleteById";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingDeleteById/show",method = RequestMethod.DELETE)
    public Response dropPlan(@Valid BreedingPlan breedingPlan,
                             BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案删除失败");
            return response;
        }else {
            BreedingPlan delete = new BreedingPlan();
            breedingPlanService.dropPlan(breedingPlan.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",delete);
            response.setData(data);
            return response;
        }
    }

//    操作员使用按主键修改的接口：/breedingUpdateByOperator
//    操作员使用按主键修改的方法名：changePlanByOperator()
//    操作员使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/breedingUpdateByOperator",method = RequestMethod.GET)
    public String changePlanByOperator(){
        return "BreedingUpdateByOperator";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingUpdateByOperator/show",method = RequestMethod.POST)
    public Response changePlanByOperator(@Valid BreedingPlan operator,
                                         @Valid OtherTime otherTime,
                                         BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(操作员页面)修改失败");
            return response;
        }else{
            Date breedingT = null;
            Date gestationT = null;
            Date prenatalIT = null;
            Date cubT = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (!otherTime.getS_breedingT().isEmpty()){
                breedingT =  formatter.parse(otherTime.getS_breedingT());
            }
            if (!otherTime.getS_gestationT().isEmpty()){
                gestationT = formatter.parse(otherTime.getS_gestationT());
            }
            if (!otherTime.getS_prenatalIT().isEmpty()){
                prenatalIT = formatter.parse(otherTime.getS_prenatalIT());
            }
            if (!otherTime.getS_cubT().isEmpty()){
                cubT = formatter.parse(otherTime.getS_cubT());
            }
            operator.setBreedingT(breedingT);
            operator.setGestationT(gestationT);
            operator.setPrenatalIT(prenatalIT);
            operator.setCubT(cubT);
            breedingPlanService.changePlanSelective(operator);

            BreedingPlan selectById = breedingPlanService.findPlanById(operator.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    专家使用按主键修改的接口：/breedingUpdateByProfessor
//    专家使用按主键修改的方法名：changePlanByProfessor()
//    专家使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/breedingUpdateByProfessor",method = RequestMethod.GET)
    public String changePlanByProfessor(){
        return "BreedingUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingUpdateByProfessor/show",method = RequestMethod.POST)
    public Response changePlanByProfessor(@Valid BreedingPlan professor,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(专家页面)修改失败");
            return response;
        }else{
            professor.setGmtModified(new Date());
            if (professor.getIsPass() == 1){
                professor.setUpassReason("操作员已经修改档案并通过技术审核");
            }
            breedingPlanService.changePlanSelective(professor);

            BreedingPlan selectById = breedingPlanService.findPlanById(professor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    监督者使用按主键修改的接口：/breedingUpdateBySupervisor
//    监督者使用按主键修改的方法名：changePlanBySupervisor()
//    监督者使用接收参数：整个表单信息（整型id必填，各参数选填）
    @RequestMapping(value = "/breedingUpdateBySupervisor",method = RequestMethod.GET)
    public String changePlanBySupervisor(){
        return "BreedingUpdateBySupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingUpdateBySupervisor/show",method = RequestMethod.POST)
    public Response changePlanBySupervisor(@Valid BreedingPlan supervisor,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(监督页面)修改失败");
            return response;
        }else{
            supervisor.setGmtSupervised(new Date());
            breedingPlanService.changePlanSelective(supervisor);

            BreedingPlan selectById = breedingPlanService.findPlanById(supervisor.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按主键查询的接口：/breedingSelectById
//    按主键查询的方法名：findPlanById()
//    接收参数：整型的主键号（保留接口查询，前端不调用此接口）
    @RequestMapping(value = "/breedingSelectById",method = RequestMethod.GET)
    public String findPlanById(){
        return "BreedingSelectById";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingSelectById/show",method = RequestMethod.GET)
    public Response findPlanById(@Valid BreedingPlan breedingPlan,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(根据条件)查询失败");
            return response;
        }else {
            //查询语句的写法：一定要在声明对象时把值直接赋进去
            BreedingPlan selectById = breedingPlanService.findPlanById(breedingPlan.getId());
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",selectById);
            response.setData(data);
            return response;
        }
    }

//    按条件查询接口：/breedingSelective
//    按条件查询方法名：findPlanSelective()
//    接收的参数：前端的各参数，以及八个("s_breedingT1")("s_breedingT2")("s_gestationT1")("s_gestationT2")("s_prenatalIT1")("s_prenatalIT2")("s_cubT1")("s_cubT2")时间字符串（所有参数可以选填）
    @RequestMapping(value = "/breedingSelective",method = RequestMethod.GET)
    public String findPlanSelective(){
        return "BreedingSelective";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingSelective/show",method = RequestMethod.GET)
    public Response findPlanSelective(@Valid BreedingPlan breedingPlan,
                                      @Valid OtherTime otherTime,
                                      BindingResult bindingResult) throws ParseException {
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(根据条件)查询失败");
            return response;
        }else {
            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();
            Date breedingT1 = null;
            Date breedingT2 = null;
            Date gestationT1 = null;
            Date gestationT2 = null;
            Date prenatalIT1 = null;
            Date prenatalIT2 = null;
            Date cubT1 = null;
            Date cubT2 = null;
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:SS");
            if (otherTime.getS_breedingT1() != null && !otherTime.getS_breedingT1().isEmpty() && otherTime.getS_breedingT2() != null && !otherTime.getS_breedingT2().isEmpty()){
                breedingT1 =  formatter.parse(otherTime.getS_breedingT1());
                breedingT2 =  formatter.parse(otherTime.getS_breedingT2());
            }
            if (otherTime.getS_gestationT1() != null && !otherTime.getS_gestationT1().isEmpty() && otherTime.getS_gestationT2() != null && !otherTime.getS_gestationT2().isEmpty()){
                gestationT1 =  formatter.parse(otherTime.getS_gestationT1());
                gestationT2 =  formatter.parse(otherTime.getS_gestationT2());
            }
            if (otherTime.getS_prenatalIT1() != null && !otherTime.getS_prenatalIT1().isEmpty() && otherTime.getS_prenatalIT2() != null && !otherTime.getS_prenatalIT2().isEmpty()){
                prenatalIT1 =  formatter.parse(otherTime.getS_prenatalIT1());
                prenatalIT2 =  formatter.parse(otherTime.getS_prenatalIT2());
            }
            if (otherTime.getS_cubT1() != null && !otherTime.getS_cubT1().isEmpty() && otherTime.getS_cubT2() != null && !otherTime.getS_cubT2().isEmpty()){
                cubT1 =  formatter.parse(otherTime.getS_cubT1());
                cubT2 =  formatter.parse(otherTime.getS_cubT2());
            }
            if(breedingT1 != null && breedingT2 != null ){
                criteria.andBreedingTBetween(breedingT1,breedingT2);
            }
            if(gestationT1 != null && gestationT2 != null ){
                criteria.andGestationTBetween(gestationT1,gestationT2);
            }
            if(prenatalIT1 != null && breedingT2 != null ){
                criteria.andPrenatalITBetween(prenatalIT1,prenatalIT2);
            }
            if(cubT1 != null && cubT2 != null ){
                criteria.andCubTBetween(cubT1,cubT2);
            }
            if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
                criteria.andIdEqualTo(breedingPlan.getId());
            }
            if(breedingPlan.getFactoryNum() != null && breedingPlan.getFactoryNum().toString() !=""){
                criteria.andFactoryNumEqualTo(breedingPlan.getFactoryNum());
            }
            if(breedingPlan.getBuilding() != null && breedingPlan.getBuilding() !=""){
                criteria.andBuildingEqualTo(breedingPlan.getBuilding());
            }
            if(breedingPlan.getmEtI() != null && breedingPlan.getmEtI() !=""){
                criteria.andMEtIEqualTo(breedingPlan.getmEtI());
            }
            if(breedingPlan.getmEtB() != null && breedingPlan.getmEtB() !=""){
                criteria.andMEtBEqualTo(breedingPlan.getmEtB());
            }
            if(breedingPlan.getfEtI() != null && breedingPlan.getfEtI() !=""){
                criteria.andFEtIEqualTo(breedingPlan.getfEtI());
            }
            if(breedingPlan.getfEtB() != null && breedingPlan.getfEtB() !=""){
                criteria.andFEtBEqualTo(breedingPlan.getfEtB());
            }
            if(breedingPlan.getQuantity() != null && breedingPlan.getQuantity().toString() !=""){
                criteria.andQuantityEqualTo(breedingPlan.getQuantity());
            }
            if(breedingPlan.getOperator() != null && breedingPlan.getOperator() !=""){
                criteria.andOperatorEqualTo(breedingPlan.getOperator());
            }
            if(breedingPlan.getProfessor() != null && breedingPlan.getProfessor() !=""){
                criteria.andProfessorEqualTo(breedingPlan.getProfessor());
            }
            if(breedingPlan.getSupervisor() != null && breedingPlan.getSupervisor() !=""){
                criteria.andSupervisorEqualTo(breedingPlan.getSupervisor());
            }
            if(breedingPlan.getIsPass() != null && breedingPlan.getIsPass().toString() !=""){
                criteria.andIsPassEqualTo(breedingPlan.getIsPass());
            }
            if(breedingPlan.getUpassReason() != null && breedingPlan.getUpassReason() !=""){
                criteria.andUpassReasonLike(breedingPlan.getUpassReason());
            }
            if(breedingPlan.getIsPass1() != null && breedingPlan.getIsPass1().toString() !=""){
                criteria.andIsPassEqualTo(breedingPlan.getIsPass1());
            }
            System.out.println(otherTime.getPage()+otherTime.getSize());
            List<BreedingPlan> selective = breedingPlanService.findPlanSelective(breedingPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",selective);
            response.setData(data);
            return response;
        }
    }

//    供技术审核查询信息:/breedingSelectByProfessor
//    供技术审核查询方法名：findPlanSelectByProfessor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/breedingSelectByProfessor",method = RequestMethod.GET)
    public String findPlanSelectByProfessor(){
        return "BreedingSelectByProfessor";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingSelectByProfessor/show",method = RequestMethod.GET)
    public Response findPlanSelectByProfessor(@Valid BreedingPlan breedingPlan,
                                              @Valid OtherTime otherTime,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(监督页面)修改失败");
            return response;
        }else {
            Byte notPassed = 0;
            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();

            if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
                criteria.andIdEqualTo(breedingPlan.getId());
            }
            if(breedingPlan.getProfessor() != null && breedingPlan.getProfessor() !=""){
                criteria.andProfessorEqualTo(breedingPlan.getProfessor());
            }
            if(breedingPlan.getUpassReason() != null && breedingPlan.getUpassReason() !=""){
                criteria.andUpassReasonLike(breedingPlan.getUpassReason());
            }
            criteria.andIsPassEqualTo(notPassed);//显示的为未通过技术审核
            List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",select);
            response.setData(data);
            return response;
        }
    }

//    供监督者查询信息
//    供监督者查询方法名：findPlanSelectBySupervisor()
//    接收的参数：前端的各参数，（所有参数可以选填）
    @RequestMapping(value = "/breedingSelectBySupervisor",method = RequestMethod.GET)
    public String findPlanSelectBySupervisor(){
        return "BreedingSelectSupervisor";
    }
    @ResponseBody
    @RequestMapping(value = "/breedingSelectBySupervisor/show",method = RequestMethod.GET)
    public Response findPlanSelectBySupervisor(@Valid BreedingPlan breedingPlan,
                                               @Valid OtherTime otherTime,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Response response = Responses.errorResponse("育种实施档案(监督页面)修改失败");
            return response;
        }else {
            Byte notPassed1 = 0;
            BreedingPlanExample breedingPlanExample = new BreedingPlanExample();
            BreedingPlanExample.Criteria criteria = breedingPlanExample.createCriteria();

            if(breedingPlan.getId() != null && breedingPlan.getId().toString() !=""){
                criteria.andIdEqualTo(breedingPlan.getId());
            }
            if(breedingPlan.getSupervisor() != null && breedingPlan.getSupervisor() !=""){
                criteria.andSupervisorEqualTo(breedingPlan.getSupervisor());
            }
            criteria.andIsPass1EqualTo(notPassed1);
            List<BreedingPlan> select = breedingPlanService.findPlanSelective(breedingPlanExample,new RowBounds(otherTime.getPage(),otherTime.getSize()));
            Response response = Responses.successResponse();
            HashMap<String, Object> data = new HashMap<>();
            data.put("breeding_plan",select);
            response.setData(data);
            return response;
        }
    }
}
