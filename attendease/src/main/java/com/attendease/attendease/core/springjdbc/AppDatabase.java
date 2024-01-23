package com.attendease.attendease.core.springjdbc;

import com.attendease.attendease.model.User;
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

    public void OnCreateUser(User user) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
        String insertSQL = "INSERT INTO user (Username, FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(insertSQL, user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());

        System.out.println("Account successfully created: " + result);

        ctx.close();
    }

    public User OnReadUsers(int id) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String selectSQL = "SELECT * FROM user WHERE id = ?";

        User user;

        try {
            user = jdbcTemplate.queryForObject(selectSQL, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }

        ctx.close();

        return user;
    }

    public List<User> OnReadAllUsers() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String selectSQL = "SELECT * FROM user";

        List<User> user = jdbcTemplate.query(selectSQL, new BeanPropertyRowMapper<>(User.class));

        ctx.close();

        return user;
    }

    public void OnDeleteUser(User user) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        String deleteSQL = "DELETE FROM user WHERE id = ?";

        int result = jdbcTemplate.update(deleteSQL, user.getUserID());

        System.out.println("Deleted " + result + " user(s) from the database.");

        ctx.close();
    }


    public void OnUpdateUser(User user) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("configs.xml");
        JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        // Assuming you have fields like 'name', 'email', and 'id' in your User class
        String updateSQL = "UPDATE user SET Username = ?, Email = ? WHERE UserID = ?";

        int result = jdbcTemplate.update(updateSQL, user.getUsername(), user.getEmail(), user.getUserID());

        System.out.println("Updated " + result + " user(s) in the database.");

        ctx.close();
    }
}

