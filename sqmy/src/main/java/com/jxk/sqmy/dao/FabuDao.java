package com.jxk.sqmy.dao;

import com.jxk.sqmy.entity.Fabu;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Fabu)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-01 19:11:51
 */
public interface FabuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Fabu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Fabu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fabu 实例对象
     * @return 对象列表
     */
    List<Fabu> queryAll(Fabu fabu);

    /**
     * 新增数据
     *
     * @param fabu 实例对象
     * @return 影响行数
     */
    int insert(Fabu fabu);

    /**
     * 修改数据
     *
     * @param fabu 实例对象
     * @return 影响行数
     */
    int update(Fabu fabu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Fabu queryFabuByKeyWord(@Param("fabu") Fabu fabu);

}