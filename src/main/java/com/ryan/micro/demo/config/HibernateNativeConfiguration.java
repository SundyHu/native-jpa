package com.ryan.micro.demo.config;

import org.hsqldb.jdbc.JDBCDriver;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration(proxyBeanMethods = false)
@ImportRuntimeHints(HibernateNativeConfiguration.HibernateRuntimeHintsRegistrar.class)
public class HibernateNativeConfiguration {

    static class HibernateRuntimeHintsRegistrar implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
//            Stream.of(
//                    BytecodeProviderImpl.class
//            ).forEach(x -> hints.reflection().registerType(x, MemberCategory.values()));

            hints.resources().registerResourceBundle("org.hsqldb.resources.sql-state-messages")
                    .registerResourceBundle("org.hsqldb.resources.info-table-remarks")
                    .registerResourceBundle("org.hsqldb.resources.info-column-remarks");
            hints.reflection().registerType(JDBCDriver.class);
        }
    }
}
