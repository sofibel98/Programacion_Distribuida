package com.distribuida.config;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;

@ApplicationScoped
public class DbConfig {

    //Segunda opción: utilizando CDI, invocando directamente el @ConfigProperty
       public static Jdbi jdbi;

   @Inject
   @PostConstruct
    public void init(){
       Config config = ConfigProvider.getConfig();
       String user = config.getValue("db.user",String.class);
       String password = config.getValue("db.password",String.class);
       String url = config.getValue("db.url",String.class);

       jdbi = Jdbi.create(url, user, password)
               .installPlugin(new PostgresPlugin());
    }

    public void test(){
       init();
    }
}

