package com.wonhigh.i18n.ms.manager;

import java.util.List;
import java.util.Map;

import com.wonhigh.i18n.ms.common.model.UserEntity;
import com.yougou.logistics.base.common.exception.ManagerException;
import com.yougou.logistics.base.manager.BaseCrudManager;

/**
 * 
 * 
 * @author wang.w
 * @date 2014-6-10 下午2:08:26
 * @version 0.9.1 
 * @copyright richmo998
 */
public interface UserManager extends BaseCrudManager{
	
	/**
	 * 加载特定的数据
	 * @param paramMap
	 * @return
	 * @throws ManagerException
	 */
	public List<UserEntity> findUserRecordList(Map<String, Object> paramMap,int pageNo,int pageSize) throws ManagerException;
	
	/**
	 * 更新特定的字段
	 * @param paramMap
	 * @throws ManagerException
	 */
	public void modifyUserRecord(Map<String, Object> paramMap) throws ManagerException;
	
	/**
	 * 发送普通邮件
	 * @param email
	 * @throws ManagerException
	 */
	public void sendCommonUser(UserEntity user) throws ManagerException;
	
	/**
	 * 发送模板邮件
	 * @param email
	 * @throws ManagerException
	 */
	public void sendModelUser(UserEntity user) throws ManagerException;
	
	/**
	 * 转移数据至历史表
	 * @param emailList
	 * @throws ManagerException
	 */
	public void transferUserRecord2History(List<UserEntity> user) throws ManagerException;
	
	/**
	 * 恢复状态为失败的或者状态为锁定且时间大于seconds的
	 * @param seconds 秒
	 * @throws ManagerException
	 */
	public void modifyUserFail2Init(Map<String, Object> paramMap) throws ManagerException;
	
	/**
	 * 删除历史记录
	 * @param paramsMap
	 * @throws ManagerException
	 */
	public void removeHistoryRecord(Map<String, Object> paramsMap) throws ManagerException;
	
	
	
	
	/**
	 * 增加
	 * @param paramMap
	 * @throws ManagerException
	 */
	public int addUserRecord(Map<String, Object> paramMap) throws ManagerException;
	
	
	public int vaildUserName(Map<String, Object> paramMap)throws ManagerException;
	
	
	
}
