/**
 * ResourceServiceImpl.java	  V1.0   2014-4-3 上午9:29:29
 *
 * Copyright GTA Information System Co. ,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.gta.oec.service.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.dao.resource.ResourceDao;
import com.gta.oec.exception.BaseException;
import com.gta.oec.vo.resource.ResourceVo;
/**
 * 
 * 功能描述：资源服务层
 *
 * @author  bingzhong.qin
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	private static final Log logger = LogFactory.getLog(ResourceServiceImpl.class);


	@Autowired
    private ResourceDao resourceDao;
}
