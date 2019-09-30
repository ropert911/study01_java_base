package com.study.transaction.service;


import com.study.transaction.domain.User;
import com.study.transaction.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoServiceImpl {
	@Autowired
	private UserJpaRepository userJpaRepository;

	@Transactional(rollbackFor={IllegalArgumentException.class}) //2
	public void savePersonWithRollBack(User book){
		userJpaRepository.save(book);

		if(book.getName().equals("xq")){
			throw new IllegalArgumentException("xq已存在，数据将回滚"); //3
		}
	}

	@Transactional(noRollbackFor={IllegalArgumentException.class}) //4
	public void savePersonWithoutRollBack(User book){
		userJpaRepository.save(book);
		
		if(book.getName().equals("xq")){
			throw new IllegalArgumentException("xq虽已存在，数据将不会回滚");
		}
	}
}
