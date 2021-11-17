package com.professional.MyEcommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.professional.MyEcommerce.entity.Country;
import com.professional.MyEcommerce.entity.Order;
import com.professional.MyEcommerce.entity.Product;
import com.professional.MyEcommerce.entity.ProductCategory;
import com.professional.MyEcommerce.entity.State;

public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		theEntityManager = entityManager;
	}
	
	
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE,HttpMethod.PATCH,HttpMethod.POST,HttpMethod.PUT};
		disableHttpMethods(Product.class,config,theUnsupportedActions);
		disableHttpMethods(ProductCategory.class,config,theUnsupportedActions);
		disableHttpMethods(Country.class,config,theUnsupportedActions);
		disableHttpMethods(State.class,config,theUnsupportedActions);
		disableHttpMethods(Order.class,config,theUnsupportedActions);


		exposeIds(config);
		
		cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);


	}


	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config,
			HttpMethod[] theUnsupportedActions) {
		
		config.getExposureConfiguration().forDomainType(theClass).withItemExposure((metdata,httpMethods)->httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metdata,httpMethods)->httpMethods.disable(theUnsupportedActions));
		// TODO Auto-generated method stub
		
	}
	private void exposeIds(RepositoryRestConfiguration config) {
		
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses = new ArrayList<>();
		
		for(EntityType tempEntityType : entities){
			entityClasses.add(tempEntityType.getJavaType());
		}
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
	}
	

}
