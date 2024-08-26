package org.isbel8ai.training.datasources.config;

import org.isbel8ai.training.datasources.model.account.Account;
import org.isbel8ai.training.datasources.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackageClasses = AccountRepository.class,
        entityManagerFactoryRef = "accountEntityManagerFactory",
        transactionManagerRef = "accountTransactionManager"
)
public class AccountJpaConfig {

    @Bean
    @ConfigurationProperties("spring.account-datasource")
    public DataSource accountDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(
            @Qualifier("accountDataSource") DataSource dataSource, JpaProperties jpaProperties) {

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), null);

        return builder
                .dataSource(dataSource)
                .packages(Account.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager accountTransactionManager(
            @Qualifier("accountEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
