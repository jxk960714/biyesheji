package com.jxk.sqmy.service.impl;

import com.jxk.sqmy.dao.AdminDao;
import com.jxk.sqmy.entity.Fabu;
import com.jxk.sqmy.dao.FabuDao;
import com.jxk.sqmy.entity.Sqmy;
import com.jxk.sqmy.service.FabuService;
import com.jxk.sqmy.util.PageCalculator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Fabu)表服务实现类
 *
 * @author makejava
 * @since 2020-03-01 19:11:51
 */
@Service("fabuService")
public class FabuServiceImpl implements FabuService {
    @Resource
    private FabuDao fabuDao;
    @Resource
    private AdminDao adminDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Fabu queryById(Integer id) {
        return this.fabuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Fabu> queryAllByLimit(int offset, int limit) {
        return this.fabuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fabu 实例对象
     * @return 实例对象
     */
    @Override
    public Fabu insert(Fabu fabu) {
        this.fabuDao.insert(fabu);
        return fabu;
    }

    /**
     * 修改数据
     *
     * @param fabu 实例对象
     * @return 实例对象
     */
    @Override
    public Fabu update(Fabu fabu) {
        this.fabuDao.update(fabu);
        return this.queryById(fabu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.fabuDao.deleteById(id) > 0;
    }

    @Override
    public boolean fabu(Fabu fabu) {
        System.out.println(fabuDao.queryFabuByKeyWord(fabu)==null);
        if(fabuDao.queryFabuByKeyWord(fabu)==null){
            Sqmy sqmy =new Sqmy();
            sqmy.setKeyWord(fabu.getKeyWord());
            if(adminDao.querySqmyByKeyWord(sqmy)!=null){
                adminDao.updateIsFaBu(adminDao.querySqmyByKeyWord(sqmy));
            }
            DateFormat format =new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");
            fabu.setFaBuTime(format.format(new Date()));
            fabuDao.insert(fabu);
            return true;
        }else {
            return false;
        }


    }

    @Override
    public List<Fabu> getFabu(int pageIndex, int limit) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, limit);
        return fabuDao.queryAllByLimit(rowIndex,limit);
    }
}