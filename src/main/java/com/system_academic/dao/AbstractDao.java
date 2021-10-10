package com.system_academic.dao;

import java.sql.Connection;

public abstract class AbstractDao implements IDao {
    protected Connection connection;
}
