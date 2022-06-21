package com.nttdata.bc19.mstransactioncredit.repository;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditBusiness;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCreditBusinessRepository extends ReactiveMongoRepository<TransactionCreditBusiness, String> {

    Flux<TransactionCreditBusiness> findByIdCreditBusiness(String idCreditBusiness);
}
