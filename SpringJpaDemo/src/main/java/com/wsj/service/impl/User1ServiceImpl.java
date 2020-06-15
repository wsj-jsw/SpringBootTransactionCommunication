package com.wsj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wsj.dao.User1Repository;
import com.wsj.domain.User1;
import com.wsj.service.User1Service;

@Service
public class User1ServiceImpl implements User1Service {

	@Autowired
	User1Repository use1Repository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRequired(User1 user) {
		use1Repository.save(user);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addRequiresNew(User1 user) {
		// TODO Auto-generated method stub
		use1Repository.save(user);
	}

}
