package com.nttdata.bc19.mstransactioncredit.repository;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionCreditPersonRepository extends ReactiveMongoRepository<TransactionCreditPerson, String> {

    Flux<TransactionCreditPerson> findByIdCreditPerson(String idCreditPerson);
}
