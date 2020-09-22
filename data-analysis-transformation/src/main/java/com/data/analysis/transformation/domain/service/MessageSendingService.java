package com.data.analysis.transformation.domain.service;

import com.data.analysis.transformation.domain.model.IndicatorResult;
import com.data.analysis.transformation.infra.constantes.Constantes;
import com.data.analysis.transformation.infra.kafka.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageSendingService extends AbstractProducer {

    public void sendIndicatorToReport(IndicatorResult indicatorResult) throws Exception {
        send(Constantes.TRANSFORMED_DATA_TOPIC, indicatorResult);
    }
}
