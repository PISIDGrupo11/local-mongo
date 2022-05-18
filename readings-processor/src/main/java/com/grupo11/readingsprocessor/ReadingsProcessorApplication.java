package com.grupo11.readingsprocessor;

import com.grupo11.readingsprocessor.database.exceptions.NotFoundException;
import com.grupo11.readingsprocessor.service.SenderServices.SenderAnomalyCollectionService;
import com.grupo11.readingsprocessor.service.SenderServices.SenderMeasurementsService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@EnableAsync
public class ReadingsProcessorApplication {

    public static void main(String[] args) throws MqttException, InterruptedException, NotFoundException {
        ApplicationContext ctx = SpringApplication.run(ReadingsProcessorApplication.class, args);

        SenderMeasurementsService senderMeasurementsService = ctx.getBean(SenderMeasurementsService.class);
        senderMeasurementsService.runService();

        SenderAnomalyCollectionService senderAnomalyCollectionService = ctx
                .getBean(SenderAnomalyCollectionService.class);
        senderAnomalyCollectionService.runService();

    }
}
