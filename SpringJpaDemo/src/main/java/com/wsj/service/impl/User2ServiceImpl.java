package com.wsj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wsj.dao.User2Repository;
import com.wsj.domain.User2;
import com.wsj.service.User2Service;

@Service
public class User2ServiceImpl implements User2Service {

	@Autowired
	User2Repository use2Repository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRequired(User2 user) {
		use2Repository.save(user);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRequiredException(User2 user) {
		use2Repository.save(user);
		throw new RuntimeException();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addRequiresNew(User2 user) {
		use2Repository.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void addRequiresNewException(User2 user) {
		use2Repository.save(user);
		throw new RuntimeException();
	}

}
