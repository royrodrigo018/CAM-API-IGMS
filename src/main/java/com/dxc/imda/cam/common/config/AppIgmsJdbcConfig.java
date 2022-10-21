package com.dxc.imda.cam.common.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = { "com.dxc.imda.cam.igms.dao", "com.dxc.imda.cam.igms.entity" },
	entityManagerFactoryRef = "igmsEntityManager",
	transactionManagerRef = "igmsTransactionManager")
//@EnableJdbcRepositories(basePackages = { "com.dxc.imda.cam.igms.dao", 
//	"com.dxc.imda.cam.igms.model", 
//	"com.dxc.imda.cam.igms.entity"})
public class AppIgmsJdbcConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private Environment env;	
	    
	@Bean(name = "igmsDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.igms")
    public DataSource igmsDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("spring.datasource.igms.driverClassName"));
    	dataSource.setUrl(env.getProperty("spring.datasource.igms.url"));
    	logger.info("url: " + env.getProperty("spring.datasource.igms.url"));
    	dataSource.setUsername(env.getProperty("spring.datasource.igms.username"));
    	dataSource.setPassword(env.getProperty("spring.datasource.igms.password"));
    	return dataSource;
    }

	@Bean(name = "igmsJdbcTemplate")
	@Primary
	public JdbcTemplate igmsJdbcTemplate(@Qualifier("igmsDataSource") DataSource igmsDS) {
		return new JdbcTemplate(igmsDS);
	}
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(igmsDataSource());
    }
	
	
	@Bean(name = "igmsEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean igmsEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(igmsDataSource());
        em.setPackagesToScan(new String[] { "com.dxc.imda.cam.igms.entity" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }
	
	@Bean
	@Primary    
    public PlatformTransactionManager igmsTransactionManager() { 
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(igmsEntityManager().getObject());
        return transactionManager;
    }
    
}
