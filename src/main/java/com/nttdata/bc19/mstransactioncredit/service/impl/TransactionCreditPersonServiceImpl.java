package com.nttdata.bc19.mstransactioncredit.service.impl;

import com.nttdata.bc19.mstransactioncredit.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditPerson;
import com.nttdata.bc19.mstransactioncredit.model.responseWC.CreditPerson;
import com.nttdata.bc19.mstransactioncredit.repository.ITransactionCreditPersonRepository;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditPersonRequest;
import com.nttdata.bc19.mstransactioncredit.service.ITransactionCreditPersonService;
import com.nttdata.bc19.mstransactioncredit.util.TransactionTypeActPro;
import com.nttdata.bc19.mstransactioncredit.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCreditPersonServiceImpl implements ITransactionCreditPersonService {

    @Autowired
    ITransactionCreditPersonRepository transactionCreditPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCreditPerson> create(TransactionCreditPersonRequest transactionCreditPersonRequest) {
        return clientServiceWC.findCreditPersonById(transactionCreditPersonRequest.getIdCreditPerson())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditResponse -> this.businessLogicTransaction(transactionCreditPersonRequest, CreditResponse));
    }

    @Override
    public Mono<TransactionCreditPerson> update(TransactionCreditPerson transactionCreditPerson) {
        transactionCreditPerson.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCreditPersonById(transactionCreditPerson.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditPerson -> this.updateTransaction(transactionCreditPerson));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCreditPersonRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCreditPerson> findById(String id) {
        return transactionCreditPersonRepository.findById(id);
    }

    @Override
    public Flux<TransactionCreditPerson> findAll() {
        return transactionCreditPersonRepository.findAll();
    }

    @Override
    public Flux<TransactionCreditPerson> findByIdCreditPerson(String idCreditPerson) {
        return transactionCreditPersonRepository.findByIdCreditPerson(idCreditPerson);
    }

    private Mono<TransactionCreditPerson> businessLogicTransaction(TransactionCreditPersonRequest transactionCreditPersonRequest, CreditPerson creditPerson){

        TransactionCreditPerson transactionCreditPerson = new TransactionCreditPerson();
        transactionCreditPerson.setId(new ObjectId().toString());
        transactionCreditPerson.setIdCreditPerson(transactionCreditPersonRequest.getIdCreditPerson());
        transactionCreditPerson.setTransactionTypeCredit(transactionCreditPersonRequest.getTransactionTypeCredit());
        transactionCreditPerson.setTransactionDate(LocalDateTime.now());
        transactionCreditPerson.setCreatedAt(LocalDateTime.now());
        transactionCreditPerson.setAmount(transactionCreditPersonRequest.getAmount());

        if(transactionCreditPersonRequest.getTransactionTypeCredit().equals(TransactionTypeActPro.PAGO.name())){
            creditPerson.setAmountPaid(creditPerson.getAmountPaid() + transactionCreditPersonRequest.getAmount());
            return clientServiceWC.updateCreditPerson(creditPerson)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(creditPersonUpdate -> this.registerTransaction(creditPersonUpdate, transactionCreditPerson));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionCreditPerson> registerTransaction(CreditPerson creditPerson, TransactionCreditPerson transactionCreditPerson){
        transactionCreditPerson.setCreditPerson(creditPerson);
        return transactionCreditPersonRepository.save(transactionCreditPerson);
    }

    private Mono<TransactionCreditPerson> updateTransaction(TransactionCreditPerson transactionCreditPerson){
        transactionCreditPerson.setUpdatedAt(LocalDateTime.now());
        return transactionCreditPersonRepository.save(transactionCreditPerson);
    }
}
