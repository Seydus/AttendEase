package com.attendease.attendease.core.springjdbc;

import com.attendease.attendease.model.Users;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AppDatabase {
    public static AppDatabase Instance;

    public static AppDatabase getInstance() {
        if (Instance == null) {
            Instance = new AppDatabase();
        }

        return Instance;
    }

    public void OnCreateUser(Users users) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        String insertSQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        int result = jdbcTemplate.update(insertSQL, users.getName(), users.getEmail(), users.getPassword());

        System.out.println("Account successfully created: " + result);

        ctx.close();
    }

    public Users OnReadUsers(int id) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String selectSQL = "SELECT * FROM users WHERE id = ?";

        Users user;

        try {
            user = jdbcTemplate.queryForObject(selectSQL, new Object[]{id}, new BeanPropertyRowMapper<>(Users.class));
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        ctx.close();

        return user;
    }

    public List<Users> OnReadAllUsers() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String selectSQL = "SELECT * FROM users";

        List<Users> users = jdbcTemplate.query(selectSQL, new BeanPropertyRowMapper<>(Users.class));

        ctx.close();

        return users;
    }

    public void OnDeleteUser(Users user) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String deleteSQL = "DELETE FROM users WHERE id = ?";

        int result = jdbcTemplate.update(deleteSQL, user.getId());

        System.out.println("Deleted " + result + " user(s) from the database.");

        ctx.close();
    }


    public void OnUpdateUser(Users user) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        // Assuming you have fields like 'name', 'email', and 'id' in your User class
        String updateSQL = "UPDATE users SET name = ?, email = ? WHERE id = ?";

        int result = jdbcTemplate.update(updateSQL, user.getName(), user.getEmail(), user.getId());

        System.out.println("Updated " + result + " user(s) in the database.");

        ctx.close();
    }
}

