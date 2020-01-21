package com.gzz.config;

import java.util.EnumSet;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
//该注解用来启用Spring StateMachine状态机功能
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Status, Events> {
 

	/*
	 * configure用来初始化当前状态机拥有哪些状态。
	 */
	@Override
	public void configure(StateMachineStateConfigurer<Status, Events> states) throws Exception {
		states.withStates()//
				.initial(Status.UNPAID) // 定义了初始状态为UNPAID
				.states(EnumSet.allOf(Status.class));// 指定States中的所有状态作为该状态机的状态定义。
	}

	/*
	 * configure用来初始化当前状态机有哪些状态迁移动作
	 * 从其中命名中我们很容易理解每一个迁移动作，都有来源状态source，目标状态target以及触发事件event。
	 */
	@Override
	public void configure(StateMachineTransitionConfigurer<Status, Events> transitions) throws Exception {
		transitions//
				.withExternal()//
				.source(Status.UNPAID).target(Status.WAITING_FOR_RECEIVE)//
				.event(Events.PAY)//
				.and()//
				.withExternal()//
				.source(Status.WAITING_FOR_RECEIVE).target(Status.DONE)//
				.event(Events.RECEIVE);//
	}
	/*
	 * configure为当前的状态机指定了状态监听器，其中listener()则是调用了下一个函数创建的监听器实例，用来处理各个各个发生的状态迁移事件。
	 * 这里注释是因为我们有其他更好的方法去替代
	 */
//    @Override
//    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
//            throws Exception {
//        config
//            .withConfiguration()
//                .listener(listener());   // 指定状态机的处理监听器
//    }
	/*
	 * listener()方法用来创建StateMachineListener状态监听器的实例，
	 * 在该实例中会定义具体的状态迁移处理逻辑，上面的实现中只是做了一些输出，
	 * 实际业务场景会有更严密的逻辑，所以通常情况下，我们可以将该实例的定义放到独立的类定义中，并用注入的方式加载进来。 这里注释是因为我们有其他更好的方法去替代
	 */
//    @Bean
//    public StateMachineListener<States, Events> listener() {
//        return new StateMachineListenerAdapter<States, Events>() {
//
//            @Override
//            public void transition(Transition<States, Events> transition) {
//                if(transition.getTarget().getId() == States.UNPAID) {
//                    logger.info("订单创建，待支付");
//                    return;
//                }
//
//                if(transition.getSource().getId() == States.UNPAID
//                        && transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
//                    logger.info("用户完成支付，待收货");
//                    return;
//                }
//
//                if(transition.getSource().getId() == States.WAITING_FOR_RECEIVE
//                        && transition.getTarget().getId() == States.DONE) {
//                    logger.info("用户已收货，订单完成");
//                    return;
//                }
//            }
//
//        };
//    }

}
