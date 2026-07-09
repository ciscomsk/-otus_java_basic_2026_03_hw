package ru.otus.java.basic.hw26.dao;

import java.sql.*;

public class DBRepositoryImpl implements DBRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/otus-db-hw";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "password";

    private static final String USER_DDL1_QUERY = """
            DROP TABLE IF EXISTS "user" """;

    private static final String USER_DDL2_QUERY = """
            CREATE TABLE IF NOT EXISTS "user"
            (
                id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                login varchar(100) NOT NULL UNIQUE,
                password varchar(100) NOT NULL,
                username varchar(100) NOT NULL UNIQUE
            )
            """;

    private static final String USER_INIT_QUERY = """
            INSERT INTO "user" (login, password, username)
            VALUES
                ('qwe', 'qwe', 'qwe'),
                ('asd', 'asd', 'asd'),
                ('zxc', 'zxc', 'zxc')
            """;

    private static final String ADD_USER_QUERY = """
            INSERT INTO "user" (login, password, username) VALUES (?, ?, ?)""";

    private static final String USERNAME_QUERY = """
            SELECT username
            FROM "user"
            WHERE
                login = ?
                AND password = ?
            """;

    private final Connection connection;

    public DBRepositoryImpl() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void init() {
        try (Statement st = connection.createStatement()) {
            int ddl1Res = st.executeUpdate(USER_DDL1_QUERY);
            System.out.println("Table user dropped: " + ddl1Res);
            int ddl2Res = st.executeUpdate(USER_DDL2_QUERY);
            System.out.println("Table user created: " + ddl2Res);
            int initRes = st.executeUpdate(USER_INIT_QUERY);
            System.out.println("Table user init: " + initRes);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBInitException(e.getMessage());
        }
    }

    @Override
    public void addUser(String login, String password, String username) {
        try (PreparedStatement ps = connection.prepareStatement(ADD_USER_QUERY)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, username);
            int res = ps.executeUpdate();
            System.out.println("User added: " + res);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UniqueViolationException(e.getMessage());
        }
    }

    @Override
    public String getUsernameByCredentials(String login, String password) {
        try (PreparedStatement ps = connection.prepareStatement(USERNAME_QUERY)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
