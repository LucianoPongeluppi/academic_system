package com.system_academic.dao;

import java.util.List;
import com.system_academic.domain.DomainEntity;

public interface IDao{
    public void create(DomainEntity entidade);
	public void update(DomainEntity entidade);
	public void delete(DomainEntity entidade);
	public List<DomainEntity> read(DomainEntity entidade);
	public DomainEntity select(DomainEntity entidade);
}