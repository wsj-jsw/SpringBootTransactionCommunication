package com.wsj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wsj.domain.User1;
import com.wsj.domain.User2;
import com.wsj.service.User1Service;
import com.wsj.service.User2Service;

@SpringBootTest
public class SpringPropagationBehaviorTest {

	@Autowired
	User1Service user1Service;
	@Autowired
	User2Service user2Service;

	@Test
	public void notransaction_exception_required_required() {

		// 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequired(user2);

		throw new RuntimeException();
	}

	@Test
	public void notransaction_required_required_exception() {

		// “张三”插入，“李四”未插入。
		// 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。

		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiredException(user2);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void transaction_exception_required_required() {

		// “张三”、“李四”均未插入。 外围方法开启事务，内部方法加入外围方法事务，外围方法回滚，内部方法也要回滚。

		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequired(user2);

		throw new RuntimeException();
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void transaction_required_required_exception() {
		// “张三”、“李四”均未插入。 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
		User1 user1 = new User1();
		user1.setName("张三2");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四2");
		user2Service.addRequiredException(user2);
	}

	@Test
	@Transactional
	public void transaction_required_required_exception_try() {
		// “张三”、“李四”均未插入。 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，即使方法被catch不被外围方法感知，整个事务依然回滚。
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		try {
			user2Service.addRequiredException(user2);
		} catch (Exception e) {
			System.out.println("方法回滚");
		}
	}

	@Test
	public void notransaction_exception_requiresNew_requiresNew() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequiresNew(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiresNew(user2);
		throw new RuntimeException();

	}

	@Test
	public void notransaction_requiresNew_requiresNew_exception() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequiresNew(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiresNewException(user2);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void transaction_exception_required_requiresNew_requiresNew() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiresNew(user2);

		User2 user3 = new User2();
		user3.setName("王五");
		user2Service.addRequiresNew(user3);
		throw new RuntimeException();
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void transaction_required_requiresNew_requiresNew_exception() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiresNew(user2);

		User2 user3 = new User2();
		user3.setName("王五");
		user2Service.addRequiresNewException(user3);
	}

	@Test
	@Transactional(propagation = Propagation.REQUIRED)
	public void transaction_required_requiresNew_requiresNew_exception_try() {
		User1 user1 = new User1();
		user1.setName("张三");
		user1Service.addRequired(user1);

		User2 user2 = new User2();
		user2.setName("李四");
		user2Service.addRequiresNew(user2);
		User2 user3 = new User2();
		user3.setName("王五");
		try {
			user2Service.addRequiresNewException(user3);
		} catch (Exception e) {
			System.out.println("回滚");
		}
	}

}
