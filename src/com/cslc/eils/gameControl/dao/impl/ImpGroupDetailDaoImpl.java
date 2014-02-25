/**
 * 
 */
package com.cslc.eils.gameControl.dao.impl;


import java.util.List;

import com.cslc.eils.gameControl.dao.ImpGroupDetailDao;
import com.cslc.eils.gameControl.entity.TSysImportgroup;
import com.cslc.eils.gameControl.entity.TSysImportgroupdetail;

/**
 * @author tianhao
 *
 */
public class ImpGroupDetailDaoImpl extends HibernateDaoImpl implements ImpGroupDetailDao {

	/**/
	private static final long serialVersionUID = 1L;

	@Override
	public void insert(TSysImportgroupdetail tgd) {
		super.insert(tgd);
	}
	
	@Override
	public void updateImpGroupDetail(List<TSysImportgroupdetail> ls) {
		for(TSysImportgroupdetail t : ls){
			this.update(t);
		}		
	}

	@Override
	public List<TSysImportgroupdetail> findByRequestId(int requstId) {
		String hql = "from TSysImportgroupdetail t where t.errCode <> 0";
		List<TSysImportgroupdetail> ls = this.findByParam(hql, null);
		return ls;
	}

	@Override
	public TSysImportgroupdetail findByRequestIdAndSn(TSysImportgroup t,
			long groupSn) {
		String hql = "from TSysImportgroupdetail td where td.TSysImportgroup = ? and td.groupSn = ?";
		Object[] params = new Object[]{t,groupSn};
		TSysImportgroupdetail td = (TSysImportgroupdetail)this.findUniqueObjByParam(hql, params);
		return td;
	}
	
}
