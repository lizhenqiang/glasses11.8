package com.pb.common;

import java.util.Date;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pb.dao.StudentDao;
import com.pb.domain.Student;

public class SpringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BeanFactory factory=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		StudentDao studentDao=(StudentDao) factory.getBean("studentDao");
		Student student = new Student();
		student.setName("zhangsan");
		student.setBirthday(new Date());

		
		studentDao.save(student);
//		
		
		

	}

}
