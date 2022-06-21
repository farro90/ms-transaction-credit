package com.nttdata.bc19.mstransactioncredit.service;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditBusiness;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditBusinessRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionCreditBusinessService {

    Mono<TransactionCreditBusiness> create(TransactionCreditBusinessRequest transactionCurrentAccountBusinessRequest);
    Mono<TransactionCreditBusiness> update(TransactionCreditBusiness transactionCreditBusiness);
    Mono<Void>deleteById(String id);
    Mono<TransactionCreditBusiness> findById(String id);
    Flux<TransactionCreditBusiness> findAll();

    Flux<TransactionCreditBusiness> findByIdCreditBusiness(String idCreditBusiness);
}
