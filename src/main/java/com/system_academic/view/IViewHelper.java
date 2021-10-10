package com.system_academic.view;

import com.system_academic.domain.DomainEntity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IViewHelper {
	public DomainEntity getEntidade(HttpServletRequest request);

	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException;

	public void updateView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException;
}
