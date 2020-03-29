package com.jxk.sqmy.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BsdwDao {
	public boolean delbsdw(@Param("bsdwId") int bsdwId);

}
