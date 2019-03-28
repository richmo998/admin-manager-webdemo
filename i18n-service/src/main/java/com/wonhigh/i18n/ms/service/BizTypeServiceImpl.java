package com.wonhigh.i18n.ms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wonhigh.i18n.ms.common.model.BizTypeEntity;
import com.wonhigh.i18n.ms.dal.database.BizTypeMapper;
import com.yougou.logistics.base.common.exception.DaoException;
import com.yougou.logistics.base.common.exception.ServiceException;
import com.yougou.logistics.base.dal.database.BaseCrudMapper;
import com.yougou.logistics.base.service.BaseCrudServiceImpl;

@Service("bizTypeService")
public class BizTypeServiceImpl extends BaseCrudServiceImpl implements BizTypeService {
	@Autowired
	private BizTypeMapper bizTypeMapper;
	
	@Override
	public BaseCrudMapper init() {
		return null;
	}

	@Override
	public int count(Map<String, Object> params) throws ServiceException {
		try {
			return bizTypeMapper.count(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<BizTypeEntity> list(Map<String, Object> params)
			throws ServiceException {
		try {
			return bizTypeMapper.list(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(BizTypeEntity entity) throws ServiceException {
		check(entity, "save");
		try {
			bizTypeMapper.save(entity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void remove(BizTypeEntity entity) throws ServiceException {
		check(entity, "remove");
		try {
			bizTypeMapper.remove(entity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(BizTypeEntity entity) throws ServiceException {
		check(entity, "update");
		try {
			bizTypeMapper.update(entity);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 校验增删改entity的合法性
	 *
	 *
	 * @since 2017年3月29日 下午5:45:22
	 * 
	 * @param entity
	 * @param type
	 * @throws ServiceException
	 * @return void
	 */
	private void check(BizTypeEntity entity, String type) throws ServiceException {
		if (entity == null) {
			throw new ServiceException("参数为空");
		}
		if ("save".equals(type)) {
			if (StringUtils.isEmpty(entity.getBizNo()) || StringUtils.isEmpty(entity.getBizName())) {
				throw new ServiceException("保存时参数错误");
			}
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("bizNo", entity.getBizNo());
			params.put("index", 0);
			params.put("size", 10);
			List<BizTypeEntity> list = this.list(params);
			if (list != null && !list.isEmpty()) {
				throw new ServiceException("already exist.");
			}
			return;
		}
		if ("remove".equals(type)) {
			if (entity.getId() == 0) {
				throw new ServiceException("删除时参数错误");
			}
			return;
		}
		if ("update".equals(type)) {
			if (entity.getId() == 0 || StringUtils.isEmpty(entity.getBizNo())  || StringUtils.isEmpty(entity.getBizName())) {
				throw new ServiceException("更新时参数错误");
			}
			return;
		}
	}
}