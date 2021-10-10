package com.system_academic.controller;

import com.system_academic.domain.DomainEntity;

public interface ICommand {
    public Object execute(DomainEntity entidade);
}
