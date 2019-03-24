package com.shozo.microservice.department;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DepartmentServiceApplication {

	public static final String topicExchangeName = "rabbitMq-Exchange";
	public static final String addEmployee = "add-Employee";
	public static final String deleteEmployee = "delete-Employee";

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName,false,false);
	}
	
	@Bean("addEmployeeQ")
	Queue queue1() {
		return new Queue(addEmployee, false);
	}
	
	@Bean("deleteEmployeeQ")
	Queue queue2() {
		return new Queue(deleteEmployee, false);
	}
	
	@Bean("addEmployeeBinding")
	Binding binding1(@Qualifier("addEmployeeQ")Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("add.employee");
	}
	
	@Bean("deleteEmployeeBinding")
	Binding binding2(@Qualifier("deleteEmployeeQ")Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("delete.employee");
	}
	

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
