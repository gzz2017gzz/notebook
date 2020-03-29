package com.gzz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = applicationContext.getBean(User.class);
		user.run();
	}
}
//初始化//User --> 构造函数
//名字//BeanNameAware --> setBeanName:user
//工厂//BeanFactoryAware --> setBeanFactory
//上下文//ApplicationContextAware --> setApplicationContext
//处理器预处理//BeanPostProcessor --> postProcessBeforeInitialization[user]
//注解//User --> @PostConstruct
//属性处理之后//InitializingBean --> afterPropertiesSet
//处理器初始化之后//BeanPostProcessor --> postProcessAfterInitialization[user]
//可用//User这个Bean可以使用了
////
//注解//User --> @PreDestroy
//销毁//DisposableBean --> destroy