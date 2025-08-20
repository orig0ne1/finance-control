package com.example.finance_control;

import com.example.finance_control.repositories.categories.CategoriesRepository;
import com.example.finance_control.repositories.categories.Category;
import com.example.finance_control.repositories.transactions.Transaction;
import com.example.finance_control.repositories.transactions.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Collections;
import java.util.List;
@Component
public class Data {
    private final Connection connection;
    private final CategoriesRepository categoriesRepository;
    private final TransactionsRepository transactionsRepository;

    @Autowired
    public Data(Connection connection,
                CategoriesRepository categoriesRepository,
                TransactionsRepository transactionsRepository, PropertyResolver propertyResolver) {
        this.connection = connection;
        this.categoriesRepository = categoriesRepository;
        this.transactionsRepository = transactionsRepository;
    }

    public List<Transaction> getTransactionByCategoryId(Integer categoryId) {
        return transactionsRepository.findAllById(Collections.singleton(categoryId));
    }

    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    public void addCategory(String title) {
        Category category = new Category();
        category.setTitle(title);
        categoriesRepository.save(category);
    }

    public void addTransaction(String description, Long price, Integer categoryId) {
        Transaction transaction = new Transaction();
        transaction.setCategoryId(categoryId);
        transaction.setDescription(description);
        transaction.setPrice(price);
        transactionsRepository.save(transaction);
    }

    public void deleteCategory(int categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

    public void deleteTransaction(int id) {
        categoriesRepository.deleteById(id);
    }
}