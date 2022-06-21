package com.nttdata.bc19.mstransactioncredit.service.impl;

import com.nttdata.bc19.mstransactioncredit.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditBusiness;
import com.nttdata.bc19.mstransactioncredit.model.responseWC.CreditBusiness;
import com.nttdata.bc19.mstransactioncredit.repository.ITransactionCreditBusinessRepository;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditBusinessRequest;
import com.nttdata.bc19.mstransactioncredit.service.ITransactionCreditBusinessService;
import com.nttdata.bc19.mstransactioncredit.util.TransactionTypeActPro;
import com.nttdata.bc19.mstransactioncredit.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionCreditBusinessServiceImpl implements ITransactionCreditBusinessService {

    @Autowired
    ITransactionCreditBusinessRepository transactionCreditBusinessRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionCreditBusiness> create(TransactionCreditBusinessRequest transactionCreditBusinessRequest) {
        return clientServiceWC.findCreditBusinessById(transactionCreditBusinessRequest.getIdCreditBusiness())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditResponse -> this.businessLogicTransaction(transactionCreditBusinessRequest, CreditResponse));
    }

    @Override
    public Mono<TransactionCreditBusiness> update(TransactionCreditBusiness transactionCreditBusiness) {
        transactionCreditBusiness.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findCreditBusinessById(transactionCreditBusiness.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(CreditBusiness -> this.updateTransaction(transactionCreditBusiness));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionCreditBusinessRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionCreditBusiness> findById(String id) {
        return transactionCreditBusinessRepository.findById(id);
    }

    @Override
    public Flux<TransactionCreditBusiness> findAll() {
        return transactionCreditBusinessRepository.findAll();
    }

    @Override
    public Flux<TransactionCreditBusiness> findByIdCreditBusiness(String idCreditBusiness) {
        return transactionCreditBusinessRepository.findByIdCreditBusiness(idCreditBusiness);
    }

    private Mono<TransactionCreditBusiness> businessLogicTransaction(TransactionCreditBusinessRequest transactionCreditBusinessRequest, CreditBusiness creditBusiness){

        TransactionCreditBusiness transactionCreditBusiness = new TransactionCreditBusiness();
        transactionCreditBusiness.setId(new ObjectId().toString());
        transactionCreditBusiness.setIdCreditBusiness(transactionCreditBusinessRequest.getIdCreditBusiness());
        transactionCreditBusiness.setTransactionTypeCredit(transactionCreditBusinessRequest.getTransactionTypeCredit());
        transactionCreditBusiness.setTransactionDate(LocalDateTime.now());
        transactionCreditBusiness.setCreatedAt(LocalDateTime.now());
        transactionCreditBusiness.setAmount(transactionCreditBusinessRequest.getAmount());

        if(transactionCreditBusinessRequest.getTransactionTypeCredit().equals(TransactionTypeActPro.PAGO.name())){
            creditBusiness.setAmountPaid(creditBusiness.getAmountPaid() + transactionCreditBusinessRequest.getAmount());
            return clientServiceWC.updateCreditBusiness(creditBusiness)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(creditBusinessUpdate -> this.registerTransaction(creditBusinessUpdate, transactionCreditBusiness));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionCreditBusiness> registerTransaction(CreditBusiness creditBusiness, TransactionCreditBusiness transactionCreditBusiness){
        transactionCreditBusiness.setCreditBusiness(creditBusiness);
        return transactionCreditBusinessRepository.save(transactionCreditBusiness);
    }

    private Mono<TransactionCreditBusiness> updateTransaction(TransactionCreditBusiness transactionCreditBusiness){
        transactionCreditBusiness.setUpdatedAt(LocalDateTime.now());
        return transactionCreditBusinessRepository.save(transactionCreditBusiness);
    }
}
