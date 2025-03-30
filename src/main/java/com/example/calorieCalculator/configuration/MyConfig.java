package com.example.calorieCalculator.configuration;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.example.calorieCalculator")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("org.postgresql.Driver"); // Драйвер для PostgreSQL
            dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/calorie_db"); // URL базы данных
            dataSource.setUser("bestuser"); // Имя пользователя БД
            dataSource.setPassword("bestuser"); // Пароль от БД

        } catch (PropertyVetoException e) {
            throw new RuntimeException("Ошибка при настройке DataSource", e);
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.example.calorieCalculator.model"); // Указываем пакет с сущностями

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // Диалект PostgreSQL
        props.setProperty("hibernate.show_sql", "true"); // Вывод SQL-запросов в консоль
        props.setProperty("hibernate.hbm2ddl.auto", "update"); // Автоматическое обновление схемы

        sessionFactoryBean.setHibernateProperties(props);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
