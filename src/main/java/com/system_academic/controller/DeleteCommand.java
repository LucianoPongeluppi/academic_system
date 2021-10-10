package com.system_academic.controller;

import com.system_academic.domain.DomainEntity;

public class DeleteCommand extends AbstractCommand {
    public String execute(DomainEntity entidade){
        return facade.delete(entidade);
    }    
}
