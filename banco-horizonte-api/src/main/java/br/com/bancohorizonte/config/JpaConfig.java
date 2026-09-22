package br.com.bancohorizonte.config;
import jakarta.persistence.*;
public final class JpaConfig { private static final EntityManagerFactory EMF=Persistence.createEntityManagerFactory("banco-horizonte"); private JpaConfig(){} public static EntityManager entityManager(){return EMF.createEntityManager();} public static void close(){EMF.close();} }
