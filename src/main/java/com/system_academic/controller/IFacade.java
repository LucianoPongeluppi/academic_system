package com.system_academic.controller;

import java.util.List;
import com.system_academic.domain.DomainEntity;

public interface IFacade {
    public String create(DomainEntity entidade);
    public String delete(DomainEntity entidade);
	public String update(DomainEntity entidade);
	public List<DomainEntity> read(DomainEntity entidade);
	public DomainEntity select(DomainEntity entidade);
}
