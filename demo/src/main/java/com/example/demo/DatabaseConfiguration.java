package com.example.demo;

import javax.sql.DataSource;//커넥션풀사용할때나 원격객체를 호출할때
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
//@PropertySource("classpath:/application.properties")
@PropertySource("classpath:/application.yml")
//@MapperScan(basePackages = "com.example.demo.mapper")
public class DatabaseConfiguration {//NullPointerException ->  BeanCreationException
	private static final Logger logger = LogManager.getLogger(DatabaseConfiguration.class);
	//Bean있는 메소드는 byName,  byType호출이 가능함
	//하드코딩 하지 말고 메소드의 리턴타입으로 객체 주입함 - 결합도 낮추는 코드전개 한가지
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		logger.info("datasource : {}", dataSource);
		return dataSource;
	}
	@Autowired//그물 엮는다 의미 - 의존성 주입관련 어노테이션
	private ApplicationContext applicationContext;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		//classpath는 src/main/resourcs이고 해당 쿼리가 있는 xml 위치는 본인의 취향대로 위치키시고 그에 맞도록 설정해주면 된다.
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}	
}