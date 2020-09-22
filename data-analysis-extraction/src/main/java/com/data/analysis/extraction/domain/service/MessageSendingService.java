package com.data.analysis.extraction.domain.service;

import com.data.analysis.extraction.domain.model.Acummulator;
import com.data.analysis.extraction.infra.constantes.Constantes;
import com.data.analysis.extraction.infra.kafka.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageSendingService extends AbstractProducer {

    public void send(List<Acummulator> accumulatorList) throws Exception {
        send(Constantes.EXTRACTED_DATA_TOPIC, accumulatorList);
    }
}
