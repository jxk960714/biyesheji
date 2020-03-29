package com.jxk.sqmy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.entity.User;
@Mapper
public interface AdminDao {
	public List<Sqmy> queryAllSqmyList( @Param("sqmy")Sqmy sqmy,@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);//查出所有社情民意的列表
	public Integer querySqmyCount(@Param("sqmy")Sqmy sqmy);//查出所有社情民意的数量
	public int tg(@Param("sqmyId") int sqmyId);//通过审核
	public int btg(@Param("sqmyId") int sqmyId);//不通过审核
	public  int insertCategory(Category Category);//增加一级类别
	public boolean delcategory(@Param("categoryId")int categoryId);//删除类别
	public List<User> queryalluser();//查找所有的用户列表包括管理员
	public List<User> queryExceptadmin();
	public int xiugaiCategory(Category Category);//修改类别
	public int xiugaibsdw(Bsdw bsdw);//修改报送单位
	public int xiugaiuser(User user);//修改登录用户
	public boolean delSqmyByUserId(@Param("userid")int userid);//通过用户id删除社情民意
	public boolean deluser(@Param("userid")int userid);//删除用户
	public int inertbsdw(Bsdw bsdw);//增加报送单位
	public  Sqmy querySqmyByKeyWord(@Param("sqmy") Sqmy sqmy);
	public  int updateIsFaBu(Sqmy sqmy);
	public  Integer querySqmyByUserandStatus(@Param("user") User user);
	public  Integer querySqmyByCategoryAndStatus(@Param("category")Category category);
}
