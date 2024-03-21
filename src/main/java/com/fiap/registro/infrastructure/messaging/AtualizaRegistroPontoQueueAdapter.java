package com.fiap.registro.infrastructure.messaging;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fiap.registro.domain.ports.out.IAtualizaRegistroPontoQueuePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AtualizaRegistroPontoQueueAdapter implements IAtualizaRegistroPontoQueuePort {
    @Value("${queue.atualiza.registro.ponto}")
    private String queueAtualizaRegistroPonto;
    @Override
    public void publish(String mensagem) {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueStr = sqs.getQueueUrl(queueAtualizaRegistroPonto).getQueueUrl();
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(queueStr)
                .withMessageBody(mensagem)
                .withDelaySeconds(5);
        sqs.sendMessage(sendMessageRequest);
    }
}
