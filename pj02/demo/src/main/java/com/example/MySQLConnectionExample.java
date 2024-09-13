package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // MySQL 서버 연결 정보
        String url = "jdbc:mysql://localhost:3305/MYDB";
        String user = "root";
        String password = "kosmo1234";

        // 데이터베이스 연결
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("MySQL 데이터베이스에 성공적으로 연결되었습니다!");

            // 여기서 데이터베이스 작업 수행...

            // 연결 종료
            connection.close();
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("MySQL 데이터베이스에 연결 성공!");

            // 데이터 삽입 SQL 쿼리
            String query = "INSERT INTO TEST (STU1, STU2) VALUES (?, ?)";

            // PreparedStatement를 사용하여 데이터를 안전하게 삽입
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // 임의의 데이터 삽입
                stmt.setString(1, "12345"); // STU1에 들어갈 값
                stmt.setString(2, "JohnDoe"); // STU2에 들어갈 값

                int rowsInserted = stmt.executeUpdate(); // 쿼리 실행
                if (rowsInserted > 0) {
                    System.out.println("데이터 삽입 성공!");
                }
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 삽입 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("MySQL 데이터베이스에 연결 성공!");

            // 데이터 삽입 SQL 쿼리 준비
            String query = "INSERT INTO TEST (STU1, STU2) VALUES (?, ?)";

            // PreparedStatement를 사용하여 데이터를 안전하게 삽입
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // 첫 번째 행 추가
                stmt.setString(1, "67890"); // STU1에 들어갈 값
                stmt.setString(2, "JaneDoe"); // STU2에 들어갈 값
                stmt.executeUpdate(); // 쿼리 실행

                // 두 번째 행 추가
                stmt.setString(1, "54321"); // STU1에 들어갈 값
                stmt.setString(2, "MikeSmith"); // STU2에 들어갈 값
                stmt.executeUpdate(); // 쿼리 실행

                System.out.println("행을 성공적으로 추가했습니다!");
            }
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 또는 삽입 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
