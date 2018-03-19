package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Message;
import com.deep.domain.model.MessageExample;
import com.deep.domain.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class MessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value = "/messageboard",method = RequestMethod.GET)
    public String upload(){

        return "insertMessage";
    }


    @RequestMapping(value = "/messageboard/insert",method = RequestMethod.POST)
    public @ResponseBody
    Response addMessage(@Valid Message message)
    {
        message.setUsername(message.getUsername());
        message.setContact(message.getContact());
        message.setMessage(message.getMessage());
        message.setInserttime(new Date());

        messageService.insertMessage(message);

        MessageExample messageExample =new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andMessageEqualTo(message.getMessage());
        List<Message> select=messageService.findMessageSelective(messageExample);

        Response response= Responses.successResponse();
        HashMap<String,Object>data=new HashMap<>();
        data.put("Message",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageboard/searchByMessage",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByMessage(@RequestParam(value = "message",required = false,defaultValue = "")String message,
                             @RequestParam(value = "pageNumb",required = true)int pageNumb,
                             @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andMessageLike("%"+message+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByMessage",select);
        response.setData(data);
        return response;
    }

    @RequestMapping(value = "/messageboard/searchByUsername",method = RequestMethod.POST)
    public @ResponseBody
    Response searchByUsername(@RequestParam(value = "username",required = false,defaultValue = "")String username,
                              @RequestParam(value = "pageNumb",required = true)int pageNumb,
                              @RequestParam(value = "limit",required = true)int limit)
    {
        MessageExample messageExample=new MessageExample();
        MessageExample.Criteria criteria=messageExample.createCriteria();
        criteria.andMessageLike("%"+username+"%");
        List<Message>select=messageService.findMessageSelectiveWithRowbounds(messageExample,pageNumb,limit);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUsername",select);
        response.setData(data);
        return response;
    }






//    @RequestMapping(value = "/",method = RequestMethod.GET)
//    public String (){
//
//        return "";
//    }
//
//
//    @RequestMapping(value = "/",method = RequestMethod.POST)
//    public @ResponseBody Response ()
//    {
//
//    }


}
