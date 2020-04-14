package com.jxk.sqmy.service;

import java.util.List;
import com.jxk.sqmy.entity.Bsdw;
import com.jxk.sqmy.entity.Category;
import com.jxk.sqmy.entity.Sqmy;

public interface SqmyService {
	public List<Category> getCategory();
	public int insertSqmy(Sqmy sqmy);
	public List<Bsdw> getBsdw();
	public List<Sqmy> querySqmyListByUserId(Sqmy sqmy, int pageIndex, int pageSize);
	public int querySqmyCount(Sqmy sqmy);
	public int delsqmy(int id);
	public int xiugaisqmy(Sqmy sqmy);
	public Sqmy querySqmybyId(int sqmyid);
}
