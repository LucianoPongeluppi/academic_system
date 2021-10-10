package com.system_academic.controller;

import java.util.List;
import com.system_academic.domain.DomainEntity;

public class ReadCommand extends AbstractCommand{
    public List<DomainEntity> execute(DomainEntity entidade){
        return facade.read(entidade);
    }
}
