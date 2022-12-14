package com.products.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableAutoConfiguration(
    exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.products.repository",
    transactionManagerRef = "transactionManager")
public class JpaConfig {
  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {

    String username = env.getProperty("spring.datasource.username");
    String password = env.getProperty("spring.datasource.password");
    String driverClass = env.getProperty("spring.datasource.driver-class-name");
    String url = env.getProperty("spring.datasource.url");

    return DataSourceBuilder.create().username(username).password(password).url(url)
        .driverClassName(driverClass).build();
  }

  @Bean(name = "transactionManager")
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  private HibernateJpaVendorAdapter vendorAdaptor() {
    return new HibernateJpaVendorAdapter();
  }

  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();
    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    entityManagerFactoryBean.setPackagesToScan("com.products.domain");
    entityManagerFactoryBean.setJpaProperties(addProperties());

    return entityManagerFactoryBean;
  }

  private Properties addProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.dialect",
        env.getProperty("spring.jpa.properties.hibernate.dialect"));

    return properties;
  }

}
