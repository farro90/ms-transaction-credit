package com.nttdata.bc19.mstransactioncredit.api;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditBusiness;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditBusinessRequest;
import com.nttdata.bc19.mstransactioncredit.service.ITransactionCreditBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/credit/business")
public class TrasactionCreditBusinessApi {

    @Autowired
    private ITransactionCreditBusinessService transactionCreditBusinessService;

    @PostMapping
    public Mono<TransactionCreditBusiness> create(@RequestBody TransactionCreditBusinessRequest transactionCreditBusinessRequest){
        return transactionCreditBusinessService.create(transactionCreditBusinessRequest);
    }

    @PutMapping
    public Mono<TransactionCreditBusiness> update(@RequestBody TransactionCreditBusiness transactionCreditBusiness){
        return transactionCreditBusinessService.update(transactionCreditBusiness);
    }

    @GetMapping
    public Flux<TransactionCreditBusiness> findAll(){
        return transactionCreditBusinessService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCreditBusiness> findById(@PathVariable String id){ return transactionCreditBusinessService.findById(id); }

    @GetMapping("/find/{idCreditBusiness}")
    public Flux<TransactionCreditBusiness> findByIdPasProPerCli(@PathVariable String idCreditBusiness){
        return transactionCreditBusinessService.findByIdCreditBusiness(idCreditBusiness);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCreditBusinessService.deleteById(id);
    }
}
