package com.deep.api.resource;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.Pic;
import com.deep.domain.model.PicExample;
import com.deep.domain.service.PicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class PicController {
    @Resource
    private PicService picService;

    @RequestMapping(value = "/uploadfile",method = RequestMethod.GET)
    public String upload(){

        return "uploadFile";

    }


    @RequestMapping(value = "/uploadfile/upload",method = RequestMethod.POST)
    public @ResponseBody Response addPic(@Valid Pic pic,
                           @RequestParam("file")MultipartFile file,
                           HttpServletRequest request){
//        java.text.SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
//        Date udate=new Date();
//        udate=formatter.parse(udate);

        //pic.setUdate(new Date());
        pic.setVaccine(pic.getVaccine());
        pic.setBrand(pic.getBrand());
        pic.setUploader(pic.getUploader());
        pic.setSymptom(pic.getSymptom());
        pic.setSex(pic.getSex());
        pic.setSolution(pic.getSolution());
        pic.setExpert(pic.getExpert());

        Date udate=new Date();
        pic.setUdate(udate);

        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString= formatter.format(udate);
        String fileDate=dateString.replaceAll(":","-");
        String filepath = request.getSession().getServletContext().getContextPath()+"../file/";
        String originalFilename=file.getOriginalFilename();
        String suffix=originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String fileName=pic.getBrand()+"_"+pic.getVaccine()+"_"+fileDate+"."+suffix;
        String fileAddress=filepath+fileName;
        if(!file.isEmpty()){
            File saveFile=new File(fileAddress);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out =new BufferedOutputStream(
                        new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("上传失败，"+e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("上传失败，"+e.getMessage());
            }
        }
        pic.setAddress(fileAddress);
        pic.setFilename(fileName);
        picService.insertPic(pic);

        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andFilenameEqualTo(pic.getFilename());
        List<Pic> select=picService.findPicSelective(picExample);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("Pic",select);
        response.setData(data);
        return response;

    }
//    @RequestMapping(value = "/searchFile/searchByExpert",method = RequestMethod.GET)
//    public String searchByExpert(){
//
//        return "searchByExpert";
//
//    }
    @RequestMapping(value = "/searchFile/searchByExpert",method = RequestMethod.POST)
    public @ResponseBody Response getByExpert(@RequestParam(value = "expert",required = true)String expert){
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andExpertLike("%"+expert+"%");
        List<Pic> select=picService.findPicSelective(picExample);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByExpert",select);
        response.setData(data);
        return response;
    }

//    @RequestMapping(value = "/searchFile/searchByFilename",method = RequestMethod.GET)
//    public String searchByFilename(){
//
//        return "searchByFilename";
//
//    }
    @RequestMapping(value = "/searchFile/searchByFilename",method = RequestMethod.POST)
    public @ResponseBody Response getByFilename(@RequestParam(value = "filename",required = true)String filename){
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andFilenameLike("%"+filename+"%");
        List<Pic> select=picService.findPicSelective(picExample);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByFilename",select);
        response.setData(data);
        return response;
    }

//    @RequestMapping(value = "/searchFile/searchBySymptom",method = RequestMethod.GET)
//    public String searchBySymptom(){
//
//        return "searchBySymptom";
//
//    }
    @RequestMapping(value = "/searchFile/searchBySymptom",method = RequestMethod.POST)
    public @ResponseBody Response getBySymptom(@RequestParam(value = "symptom",required = true)String symptom){
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andSymptomLike("%"+symptom+"%");
        List<Pic> select=picService.findPicSelective(picExample);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchBySymptom",select);
        response.setData(data);
        return response;
    }

//    @RequestMapping(value = "/searchFile/searchByUploader",method = RequestMethod.GET)
//    public String searchByUploader(){
//
//        return "searchByUploader";
//
//    }
    @RequestMapping(value = "/searchFile/searchByUploader",method = RequestMethod.POST)
    public @ResponseBody Response getByUploader(@RequestParam(value = "uploader",required = true)String uploader){
        PicExample picExample=new PicExample();
        PicExample.Criteria criteria=picExample.createCriteria();
        criteria.andUploaderLike("%"+uploader+"%");
        List<Pic> select=picService.findPicSelective(picExample);

        Response response = Responses.successResponse();
        HashMap<String,Object>data = new HashMap<>();
        data.put("searchByUploader",select);
        response.setData(data);
        return response;
    }

}