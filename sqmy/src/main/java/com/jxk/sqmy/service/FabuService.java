package com.jxk.sqmy.service;

import com.jxk.sqmy.entity.Fabu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Fabu)表服务接口
 *
 * @author makejava
 * @since 2020-03-01 19:11:51
 */
public interface FabuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Fabu queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Fabu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fabu 实例对象
     * @return 实例对象
     */
    Fabu insert(Fabu fabu);

    /**
     * 修改数据
     *
     * @param fabu 实例对象
     * @return 实例对象
     */
    Fabu update(Fabu fabu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    boolean  fabu(Fabu fabu);
    List<Fabu> getFabu(int pageIndex, int limit);

}