/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cslc.eils.gameControl.dao.ImpGroupDao;
import com.cslc.eils.gameControl.dto.REQDelGroup;
import com.cslc.eils.gameControl.entity.TSysDelgroup;
import com.cslc.eils.gameControl.entity.TSysImportgroup;
import com.cslc.eils.gameControl.util.Constants;
import com.cslc.eils.gameControl.util.DateUtil;
import com.cslc.eils.gameControl.util.JsonUtil;

/**
 * @author tianhao
 *
 */
public class ImpGroupDaoImpl extends HibernateDaoImpl implements ImpGroupDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log log = LogFactory.getLog(this.getClass());

	@Override
	public TSysImportgroup findByRequestId(int requesId) {
		String hql = "from TSysImportgroup t where t.requestId = ?";
		Object[] objParm = new Object[]{requesId};
		return (TSysImportgroup)this.findUniqueObjByParam(hql, objParm);
	}

	@Override
	public void updateImpGroupStatus(TSysImportgroup t) {
		this.update(t);
	}

	@Override
	public List<TSysImportgroup> findFailRecorder() {
		//TSysImportgroup中状态含义：0进行中,1已完成
		String hql = "from TSysImportgroup t where t.status <> 1";
		List<TSysImportgroup> ls = this.findByParam(hql, null);
		return ls;
	}

	@Override
	public void insert(TSysImportgroup tg) {
		super.saveOrUpdate(tg);
		
	}


}
