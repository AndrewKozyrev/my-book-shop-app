package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksData(){

        List<Book> books = jdbcTemplate.query("SELECT * FROM books", (ResultSet rs, int rownum)->{
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setPriceOld(rs.getString("price_old"));
            book.setPrice(rs.getString("price"));
            int authorId =rs.getInt("author_id");
            Author author = jdbcTemplate.queryForObject("SELECT * FROM authors WHERE id = " + authorId, (row, number) -> {
                Author a = new Author();
                a.setId(row.getInt("id"));
                a.setFirstName(row.getString("first_name"));
                a.setLastName(row.getString("last_name"));
                return a;
            });
            book.setAuthor(author.getFirstName() + " " + author.getLastName());
            return book;
        });
        return new ArrayList<>(books);
    }
}
