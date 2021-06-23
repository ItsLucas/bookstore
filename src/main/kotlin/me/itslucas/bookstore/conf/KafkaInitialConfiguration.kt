package me.itslucas.bookstore.conf

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class KafkaInitialConfiguration {
    @Bean
    fun initialTopic() : NewTopic {
        return NewTopic("billing",1,1);
    }
    fun updateTopic() : NewTopic {
        return NewTopic("test",1,1);
    }
}
