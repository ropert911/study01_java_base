package com.study.jpa.rest.dao;

import com.study.jpa.rest.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "people")		//默认为persons，这里要改
public interface PersonRepository extends JpaRepository<Person, Long> {

	//保存
	//http://127.0.0.1/people/  发post请求
	//删除
	//http://127.0.0.1/people/21  使用DELETE方式访问
	//更新
	//http://127.0.0.1/people/21  发PUT请求

	//分页
	//http://127.0.0.1/people/?page=1&size=2
	//排序
	//http://127.0.0.1/people/?sort=age,desc

	//http://127.0.0.1/persons/search/nameStartsWith?name=汪  可查询
	@RestResource(path = "nameStartsWith", rel = "nameStartsWith")
	Person findByNameStartsWith(@Param("name")String name);

}
