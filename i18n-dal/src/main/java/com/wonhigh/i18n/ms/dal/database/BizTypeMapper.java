package com.wonhigh.i18n.ms.dal.database;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;

/**
 * 业务类型数据访问与操作接口
 *
 *
 * @since 2017年3月29日 下午3:04:34
 *
 */
public interface BizTypeMapper extends BaseCrudMapper {
	/**
	 * 查询业务类型数量
	 *
	 *
	 * @since 2017年3月29日 下午3:11:15
	 * 
	 * @param params
	 * @throws DaoException
	 * @return Integer
	 */
	public Integer count(@Param("params") Map<String, Object> params) throws DaoException;

	/**
	 * 查询业务类型列表
	 *
	 *
	 * @since 2017年3月29日 下午3:11:40
	 * 
	 * @param params
	 * @throws DaoException
	 * @return List<BizTypeEntity>
	 */
	public List<BizTypeEntity> list(@Param("params") Map<String, Object> params) throws DaoException;

	/**
	 * 添加一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:14
	 * 
	 * @param entity
	 * @throws DaoException
	 * @return void
	 */
	public void save(BizTypeEntity entity) throws DaoException;

	/**
	 * 删除一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:45
	 * 
	 * @param entity
	 * @throws DaoException
	 * @return void
	 */
	public void remove(BizTypeEntity entity) throws DaoException;

	/**
	 * 修改一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:58
	 * 
	 * @param entity
	 * @throws DaoException
	 * @return void
	 */
	public void update(BizTypeEntity entity) throws DaoException;
}