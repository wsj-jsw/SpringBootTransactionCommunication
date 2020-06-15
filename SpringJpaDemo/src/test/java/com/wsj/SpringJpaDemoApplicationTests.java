package com.wsj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wsj.dao.ProductCategoryRepository;
import com.wsj.dao.UserRepository;
import com.wsj.dataobject.ProductCategory;
import com.wsj.domain.UserDO;

@SpringBootTest
class SpringJpaDemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Test
	public void testSave() {
		ProductCategory productCategory = new ProductCategory();
		
		productCategory.setCategoryId(1);
		productCategory.setCategoryName("人类最爱");
		productCategory.setCategoryType(3);
		productCategoryRepository.save(productCategory);
	}

	@Test
	public void testFind() {
		Optional<UserDO> optionalUserDO = userRepository.findById(1);
		if (optionalUserDO.isPresent()) {
			UserDO userDO = optionalUserDO.get();
			System.out.println("testFind user: " + userDO.getUserName());
		}

	}

	@Test
	public void testFindAll() {
		List<UserDO> list = userRepository.findAll();
		for (UserDO user : list) {
			System.out.println("user_name:" + user.getUserName());
		}
	}

	@Test
	public void testUpdate() {
		Optional<UserDO> optionalUserDO = userRepository.findById(1);
		if (optionalUserDO.isPresent()) {
			UserDO userDO = optionalUserDO.get();
			userDO.setUserName("fishpro001");
			userRepository.save(userDO);
			System.out.println("testFind user" + userDO.getUserName());
		}

	}
	@Test
	public  void c() throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("请输入一个字符");
	    char c;
	    c=(char) bufferedReader.read();
	    System.out.println(c);
	
	}
}
