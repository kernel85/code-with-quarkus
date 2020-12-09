package org.github.rbiondi.models

import io.reactivex.annotations.NonNull
import java.math.BigDecimal
import java.time.Instant
import javax.validation.constraints.Min

data class BankTransaction(

    @field:NonNull
    @field:Min(message = "UserId must be greater than 0", value = 0L)
    val userId: UserId,

    @field:Min(message = "Amount must be greater than 0", value = 0L)
    val amount: BigDecimal,

    @field:NonNull
    val date: Instant

)

typealias UserId = Long