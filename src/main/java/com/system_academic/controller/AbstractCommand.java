package com.system_academic.controller;

public abstract class AbstractCommand implements ICommand {
    protected IFacade facade = new Facade();
}
