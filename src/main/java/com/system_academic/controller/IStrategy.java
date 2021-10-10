package com.system_academic.controller;

import com.system_academic.domain.DomainEntity;

public interface IStrategy {
	public String process(DomainEntity entidade);
}
