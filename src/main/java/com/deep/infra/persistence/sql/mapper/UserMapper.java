package com.deep.infra.persistence.sql.mapper;

import com.deep.domain.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 列出用户列表
     * @return
     */
    @Select("select * from user_manage")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3")
    })
    List<UserModel> queryAllUser();

    /**
     * 根据ID获取单个用户
     * @param userId
     * @return
     */
    @Select("select * from user_manage where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3")
    })
    UserModel queryUserById(Long userId);

    /**
     * 根据用户名获取单个用户
     * @param pkUserid
     * @return
     */
    @Select("select * from user_manage where pk_userid = #{pkUserid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3")
    })
    UserModel queryUserByPkuserID(String pkUserid);

    /**
     * 根据用户名进行模糊查询获取单个用户的信息
     * @param userRealname
     * @return
     */
    @Select("select * from user_manage where user_realname = %#{userRealname}%")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "pkUserid", column = "pk_userid"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "userNum", column = "user_num"),
            @Result(property = "userPic", column = "user_pic"),
            @Result(property = "userRealname", column = "user_realname"),
            @Result(property = "userLocation", column = "user_location"),
            @Result(property = "userTelephone", column = "user_telephone"),
            @Result(property = "userRemark", column = "user_remark"),
            @Result(property = "userFactory", column = "user_factory"),
            @Result(property = "userRole", column = "user_role"),
            @Result(property = "userPermit", column = "user_permit"),
            @Result(property = "isExtended", column = "is_extended"),
            @Result(property = "isFactory", column = "is_factory"),
            @Result(property = "question_1", column = "question_1"),
            @Result(property = "question_2", column = "question_2"),
            @Result(property = "question_3", column = "question_3"),
            @Result(property = "answer_1", column = "answer_1"),
            @Result(property = "answer_2", column = "answer_2"),
            @Result(property = "answer_3", column = "answer_3")
    })
    UserModel queryUserByRealnameLike(String userRealname);

    /**
     * 插入一个用户
     * @param userModel
     * @return
     */
    @Insert("insert into user_manage(" +
            "gmt_create," +
            "gmt_modified," +
            "pk_userid," +
            "user_pwd," +
            "user_num," +
            "user_pic," +
            "user_realname," +
            "user_location," +
            "user_telephone," +
            "user_remark," +
            "user_factory," +
            "user_role," +
            "user_permit," +
            "is_extended," +
            "is_factory," +
            "question_1," +
            "question_2," +
            "question_3, " +
            "answer_1," +
            "answer_2," +
            "answer_3)" +
            "values(" +
            "#{gmtCreate}," +
            "#{gmtModified}," +
            "#{pkUserid}," +
            "#{userPwd}," +
            "#{userNum}," +
            "#{userPic}," +
            "#{userRealname}," +
            "#{userLocation}," +
            "#{userTelephone}," +
            "#{userRemark}," +
            "#{userFactory}," +
            "#{userRole}," +
            "#{userPermit}," +
            "#{isExtended}," +
            "#{isFactory}," +
            "#{question_1}," +
            "#{question_2}," +
            "#{question_3}," +
            "#{answer_1}," +
            "#{answer_2}," +
            "#{answer_3}" +
            ")")
    Long insertUser(UserModel userModel);

    /**
     * 修改用户信息
     * @param userModel
     * @return
     */
    @Update("update user_manage set " +
            "gmt_create = #{gmtCreate}," +
            "gmt_modified = #{gmtModified}," +
            "pk_userid = #{pkUserid}," +
            "user_pwd = #{userPwd}," +
            "user_num = #{userNum}," +
            "user_pic = #{userPic}," +
            "user_realname = #{userRealname}," +
            "user_location = #{userLocation}," +
            "user_telephone = #{userTelephone}," +
            "user_remark = #{userRemark}," +
            "user_factory = #{userFactory}," +
            "user_role = #{userRole}," +
            "user_permit = #{userPermit}," +
            "is_extended = #{isExtended}," +
            "is_factory = #{isFactory}," +
            "question_answer = #{questionAnswer}" +
            "question_1 = #{question_1}, " +
            "question_2 = #{question_2}, " +
            "question_3 = #{question_3}, " +
            "answer_1 = #{answer_1}, " +
            "answer_2 = #{answer_2}, " +
            "answer_3 = #{answer_3}" +
            " where id=#{id}")
    Long updateUser(UserModel userModel);

    /**
     * 删除用户信息
     * @param userID
     * @return
     */
    @Delete("delete from user_manage where id = #{id}")
    Long deleteUser(Long userID);
}
