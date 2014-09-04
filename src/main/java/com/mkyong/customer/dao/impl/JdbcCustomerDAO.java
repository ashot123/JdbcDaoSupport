package com.mkyong.customer.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;
import com.mkyong.customer.model.CustomerRowMapper;

public class JdbcCustomerDAO extends JdbcDaoSupport implements CustomerDAO {
    //insert example
    public void insert(Customer customer) {

        String sql = "INSERT INTO CUSTOMER " +
                "(ID, NAME, AGE) VALUES (?, ?, ?)";

        getJdbcTemplate().update(sql, customer.getId(),
                customer.getName(), customer.getAge());

    }

    //insert with named parameter
    public void insertNamedParameter(Customer customer) {

        //not supported

    }

    //insert batch example
    public void insertBatch(final List<Customer> customers) {

        String sql = "INSERT INTO CUSTOMER " +
                "(ID, NAME, AGE) VALUES (?, ?, ?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Customer customer = customers.get(i);
                ps.setLong(1, customer.getId());
                ps.setString(2, customer.getName());
                ps.setInt(3, customer.getAge());
            }

            @Override
            public int getBatchSize() {
                return customers.size();
            }
        });
    }

    //insert batch with named parameter
    public void insertBatchNamedParameter(final List<Customer> customers) {

        //not supported
    }

    //insert batch with named parameter
    public void insertBatchNamedParameter2(final List<Customer> customers) {

        //not supported
    }

    //insert batch example with SQL
    public void insertBatchSQL(final String sql) {

        getJdbcTemplate().batchUpdate(new String[]{sql});

    }

    //query single row with RowMapper
    public Customer findByCustomerId(int id) {

        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

        return (Customer) getJdbcTemplate().queryForObject(
                sql, new Object[]{id}, new CustomerRowMapper());
    }

    //query single row with BeanPropertyRowMapper (Customer.class)
    public Customer findByCustomerId2(int id) {

        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

        return (Customer) getJdbcTemplate().queryForObject(
                sql, new Object[]{id},
                new BeanPropertyRowMapper(Customer.class));
    }

    //query multiple rows with manual mapping
    public List<Customer> findAll() {

        String sql = "SELECT * FROM CUSTOMER";

        List<Customer> customers = new ArrayList<>();

        /*List<Map> rows = */
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        for (Map row : rows) {
            Customer customer = new Customer();
            customer.setId((Long) (row.get("ID")));
            customer.setName((String) row.get("NAME"));
            customer.setAge((Integer) row.get("AGE"));
            customers.add(customer);
        }

        return customers;
    }

    //query multiple rows with BeanPropertyRowMapper (Customer.class)
    public List<Customer> findAll2() {

        String sql = "SELECT * FROM CUSTOMER";

        return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    public String findCustomerNameById(int id) {

        String sql = "SELECT NAME FROM CUSTOMER WHERE ID = ?";

        return  getJdbcTemplate().queryForObject(
                sql, new Object[]{id}, String.class);

    }

    public int findTotalCustomer() {

        String sql = "SELECT COUNT(*) FROM CUSTOMER";

        // Deprecated
        return getJdbcTemplate().queryForInt(sql);
    }


}
