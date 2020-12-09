package org.github.rbiondi.consumers

import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Message
import org.github.rbiondi.models.BankTransaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletionStage
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TransactionConsumer {

    companion object {
        private val logger : Logger = LoggerFactory.getLogger(TransactionConsumer::class.java)
    }

    @Incoming("transactions")
    fun consume(bankTransaction: Message<BankTransaction>) : CompletionStage<Void> {
        handle(bankTransaction.payload)
        return bankTransaction.ack()
    }

    private fun handle(bankTransaction: BankTransaction) {
        logger.info("Consuming transaction: {}", bankTransaction)
    }

}