package org.github.rbiondi.resources

import io.smallrye.reactive.messaging.annotations.Broadcast
import io.smallrye.reactive.messaging.kafka.OutgoingKafkaRecordMetadata
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import org.eclipse.microprofile.reactive.messaging.Message
import org.github.rbiondi.models.BankTransaction
import org.github.rbiondi.models.UserId
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import javax.inject.Inject
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TransactionResource {

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(TransactionResource::class.java)
    }

    @Inject
    @Broadcast
    @Channel("transactions")
    private lateinit var transactionEmitter: Emitter<BankTransaction>

    @POST
    fun add(@Valid transaction: BankTransaction) : Response {
        logger.info("Transaction received: {}", transaction)

        val metadata = OutgoingKafkaRecordMetadata.builder<UserId>()
                                                  .withKey(transaction.userId)
                                                  .build()

        transactionEmitter.send(Message.of(transaction).addMetadata(metadata))

        return Response.ok().build()

    }

}