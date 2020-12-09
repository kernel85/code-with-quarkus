package org.github.rbiondi.jackson

import io.quarkus.kafka.client.serialization.ObjectMapperSerializer
import org.github.rbiondi.models.BankTransaction

class BankTransactionSerializer : ObjectMapperSerializer<BankTransaction>()
