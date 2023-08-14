package com.distribuida.init;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;

@Singleton
@Startup
public class DatabaseCheck {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        try {
            // Verificar conexión a la base de datos
            if (entityManager != null) {
                System.out.println("Conexión a la base de datos establecida.");
            }

            // Generar SQL de creación de tablas
            String createTablesSQL = (String) entityManager
                    .getEntityManagerFactory()
                    .getProperties()
                    .get("javax.persistence.schema-generation.create-source");

            if (createTablesSQL != null && createTablesSQL.equalsIgnoreCase("metadata")) {
                System.out.println("SQL de creación de tablas generado durante el despliegue.");
            }
        } catch (PersistenceException e) {
            System.err.println("Error al establecer conexión o generar tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
