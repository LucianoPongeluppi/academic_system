package com.system_academic.controller;
import com.system_academic.domain.DomainEntity;

public class UpdateCommand extends AbstractCommand{
    public String execute(DomainEntity entidade){
        return facade.update(entidade);
    }
}
