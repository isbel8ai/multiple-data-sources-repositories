package org.isbel8ai.training.datasources.config;

import org.isbel8ai.training.datasources.model.procedure.Procedure;
import org.isbel8ai.training.datasources.repository.procedure.ProcedureRepository;
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
        basePackageClasses = ProcedureRepository.class,
        entityManagerFactoryRef = "procedureEntityManagerFactory",
        transactionManagerRef = "procedureTransactionManager"
)
public class ProcedureJpaConfig {

    @Bean
    @ConfigurationProperties("spring.procedure-datasource")
    public DataSource procedureDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean procedureEntityManagerFactory(
            @Qualifier("procedureDataSource") DataSource dataSource, JpaProperties jpaProperties) {

        EntityManagerFactoryBuilder builder = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(), jpaProperties.getProperties(), null);

        return builder
                .dataSource(dataSource)
                .packages(Procedure.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager procedureTransactionManager(
            @Qualifier("procedureEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
