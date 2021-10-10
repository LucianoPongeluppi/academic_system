package com.system_academic.controller;

import com.system_academic.domain.DomainEntity;

public class SelectCommand extends AbstractCommand {
    public DomainEntity execute(DomainEntity entidade){
        return facade.select(entidade);
    }
}
