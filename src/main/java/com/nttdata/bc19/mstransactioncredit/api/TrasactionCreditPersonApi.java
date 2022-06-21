package com.nttdata.bc19.mstransactioncredit.api;

import com.nttdata.bc19.mstransactioncredit.model.TransactionCreditPerson;
import com.nttdata.bc19.mstransactioncredit.request.TransactionCreditPersonRequest;
import com.nttdata.bc19.mstransactioncredit.service.ITransactionCreditPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/credit/person")
public class TrasactionCreditPersonApi {

    @Autowired
    private ITransactionCreditPersonService transactionCreditPersonService;

    @PostMapping
    public Mono<TransactionCreditPerson> create(@RequestBody TransactionCreditPersonRequest transactionCreditPersonRequest){
        return transactionCreditPersonService.create(transactionCreditPersonRequest);
    }

    @PutMapping
    public Mono<TransactionCreditPerson> update(@RequestBody TransactionCreditPerson transactionCreditPerson){
        return transactionCreditPersonService.update(transactionCreditPerson);
    }

    @GetMapping
    public Flux<TransactionCreditPerson> findAll(){
        return transactionCreditPersonService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<TransactionCreditPerson> findById(@PathVariable String id){ return transactionCreditPersonService.findById(id); }

    @GetMapping("/find/{idCreditPerson}")
    public Flux<TransactionCreditPerson> findByIdPasProPerCli(@PathVariable String idCreditPerson){
        return transactionCreditPersonService.findByIdCreditPerson(idCreditPerson);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionCreditPersonService.deleteById(id);
    }
}
