package com.wsj.service;

import com.wsj.domain.User2;

public interface User2Service {
	public void addRequired(User2 user);

	public void addRequiredException(User2 user);

	public void addRequiresNew(User2 user);

	public void addRequiresNewException(User2 user);

}
