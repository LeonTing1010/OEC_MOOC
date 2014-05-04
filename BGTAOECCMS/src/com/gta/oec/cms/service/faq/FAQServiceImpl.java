package com.gta.oec.cms.service.faq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gta.oec.cms.common.pagination.PaginationContext;
import com.gta.oec.cms.dao.faq.IFAQDao;
import com.gta.oec.cms.vo.faq.FAQ;

@Service
public class FAQServiceImpl implements IFAQService {

@Autowired
private IFAQDao faqDao;

@Override
public List<FAQ> findFAQPageQuery(PaginationContext<FAQ> pc) {
	return faqDao.findFAQPageQuery(pc);
}

@Override
public void updateFAQ(FAQ faq) {
	faqDao.updateFAQ(faq);
}

@Override
public void delFAQ(Integer faqID) {
	faqDao.delFAQ(faqID);
}

@Override
public void addFAQ(FAQ faq) {
	faqDao.addFAQ(faq);
}

@Override
public List<FAQ> findTitle() {
	return faqDao.findTitle();
}

@Override
public FAQ findTitleByID(Integer id) {
	return faqDao.findTitleByID(id);
}

@Override
public List<FAQ> findPIDByID(Integer id) {
	return faqDao.findPIDByID(id);
}

}
