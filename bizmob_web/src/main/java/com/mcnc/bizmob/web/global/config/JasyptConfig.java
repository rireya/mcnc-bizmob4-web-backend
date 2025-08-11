package com.mcnc.bizmob.web.global.config;

import org.jasypt.digest.config.SimpleDigesterConfig;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    @Value("${jasypt.encryptor.password}")
    private String password;
	
	@Bean(name="jasyptStringEncryptor")
	public StringEncryptor stringEncryptor() {
	    PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
	    
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        
        encryptor.setConfig(config);
	    
	    return encryptor;
	}
	
	
	@Bean(name="sha256Encryptor")
	public PasswordEncryptor sdhaEncryptor() {
		
		SimpleDigesterConfig 			config 				= new SimpleDigesterConfig();
		config.setAlgorithm("SHA-256");
		
		ConfigurablePasswordEncryptor 	passwordEncryptor 	= new ConfigurablePasswordEncryptor();
		passwordEncryptor.setConfig( config );
		passwordEncryptor.setPlainDigest( true );
		passwordEncryptor.setStringOutputType( "hexadecimal" );
		
		return passwordEncryptor;
	}
}
