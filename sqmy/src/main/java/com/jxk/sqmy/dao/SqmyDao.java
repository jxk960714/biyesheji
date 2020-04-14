package com.jxk.sqmy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;
@Mapper
public interface SqmyDao {
	public List<Category> getCategory();
	public int insertSqmy(Sqmy sqmy);
	public List<Bsdw> getBsdw();
	public List<Sqmy> querySqmyListByUserId(@Param("sqmy") Sqmy sqmy,
                                            @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	public int querySqmyCount(@Param("sqmy") Sqmy sqmy);
	//通过sqmyid删除sqmy
	public int delsqmy(@Param("id") int id);
	public boolean delSqmybyCateId(@Param("cateId") int cateId);
	public int xiugaisqmy(Sqmy sqmy);
	public Sqmy querySqmybyId(@Param("sqmyId") int sqmyId);
}
