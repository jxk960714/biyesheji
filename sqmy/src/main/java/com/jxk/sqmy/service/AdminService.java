package com.jxk.sqmy.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.entity.User;

public interface AdminService {
	public List<Sqmy> queryAllSqmyList( Sqmy sqmy ,int pageIndex,int pageSize);
	public int querySqmyCount(Sqmy sqmy);
	public int tg(int sqmyId);
	public int btg(int sqmyId);
	public  int insertCategory(Category Category);
	public boolean delcategory(int categoryId);
	public List<User> queryalluser();
	public List<User> queryExceptadmin();
	public int xiugaiCategory(Category fCategory);//修改类别
	public int xiugaibsdw(Bsdw bsdw);//修改报送单位
	public int xiugaiuser(User user);//修改登录用户
	public int inertbsdw(Bsdw bsdw);//增加报送单位
	public boolean deluser(int userid);//删除用户
	public  List<Integer> getSqmyCountByUserAndAtatus();
	public  List<Integer> getSqmyCountByCategoryAndStatus();
}
