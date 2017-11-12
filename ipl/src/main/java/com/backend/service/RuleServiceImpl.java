package com.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dao.AbstractDao;
import com.backend.dao.RuleDao;
import com.backend.model.Rule;

@Service("ruleService")
@Transactional
@CacheConfig(cacheNames = "rule")
public class RuleServiceImpl  implements RuleService{

	@Autowired
	private RuleDao ruleDao;
	
	@Override
	public void saveRule(Rule rule) {
		ruleDao.saveRule(rule);
		
	}

	@Override
	@Cacheable(value="rule" , key="'rule'")
	public List<Rule> findAllRules() {
	return	ruleDao.findAllRules();
	}

	@Override	
	@Cacheable(value="rule" , key="#ruleId")
	public Rule findRuleById(int ruleId) {
		return ruleDao.findRuleById(ruleId);
	}

	
	
}
