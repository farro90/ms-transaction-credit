package com.nttdata.bc19.mstransactioncredit.service;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditPerson;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCreditPersonService {

    Mono<TransactionCreditPerson> create(TransactionCreditPersonRequest transactionCreditPersonRequest);
    Mono<TransactionCreditPerson> update(TransactionCreditPerson transactionCreditPerson);
    Mono<Void>deleteById(String id);
    Mono<TransactionCreditPerson> findById(String id);
    Flux<TransactionCreditPerson> findAll();

    Flux<TransactionCreditPerson> findByIdCreditPerson(String idCreditPerson);
}
