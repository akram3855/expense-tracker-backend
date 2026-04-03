package com.akram.expensetracker.repository;

import com.akram.expensetracker.model.Expense;
import com.akram.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser(User user);

    List<Expense> findByCategory(String category);

    List<Expense> findByDateBetween(LocalDate start, LocalDate end);
}