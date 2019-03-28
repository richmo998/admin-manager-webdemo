package com.wonhigh.i18n.ms.service;

import java.util.List;
import java.util.Map;

import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.service.BaseCrudService;

public interface BizTypeService extends BaseCrudService{
	/**
	 * 查询业务类型数量
	 *
	 *
	 * @since 2017年3月29日 下午3:11:15
	 * 
	 * @param params
	 * @throws ServiceException
	 * @return int
	 */
	public int count(Map<String, Object> params) throws ServiceException;

	/**
	 * 查询业务类型列表
	 *
	 *
	 * @since 2017年3月29日 下午3:11:40
	 * 
	 * @param params
	 * @throws ServiceException
	 * @return List<BizTypeEntity>
	 */
	public List<BizTypeEntity> list(Map<String, Object> params) throws ServiceException;

	/**
	 * 添加一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:14
	 * 
	 * @param entity
	 * @throws ServiceException
	 * @return void
	 */
	public void save(BizTypeEntity entity) throws ServiceException;

	/**
	 * 删除一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:45
	 * 
	 * @param entity
	 * @throws ServiceException
	 * @return void
	 */
	public void remove(BizTypeEntity entity) throws ServiceException;

	/**
	 * 修改一条业务类型信息
	 *
	 *
	 * @since 2017年3月29日 下午3:12:58
	 * 
	 * @param entity
	 * @throws ServiceException
	 * @return void
	 */
	public void update(BizTypeEntity entity) throws ServiceException;
}