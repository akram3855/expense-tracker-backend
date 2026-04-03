package com.akram.expensetracker.service;

import com.akram.expensetracker.model.Expense;
import com.akram.expensetracker.model.User;
import com.akram.expensetracker.repository.ExpenseRepository;
import com.akram.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    @Autowired
    private UserRepository userRepository;

    //  ADD EXPENSE WITH USER
    public Expense addExpense(Expense expense, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        expense.setUser(user);

        return repo.save(expense);
    }

    //  GET USER-SPECIFIC EXPENSES
    public List<Expense> getExpensesByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();

        return repo.findByUser(user);
    }
    //  DELETE
    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }

    //  UPDATE
    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existing = repo.findById(id).orElseThrow();

        existing.setTitle(updatedExpense.getTitle());
        existing.setAmount(updatedExpense.getAmount());
        existing.setCategory(updatedExpense.getCategory());
        existing.setDate(updatedExpense.getDate());

        return repo.save(existing);
    }


    public List<Expense> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Expense> getByDateRange(LocalDate start, LocalDate end) {
        return repo.findByDateBetween(start, end);
    }

    public Double getTotalExpense(String email) {
        return getExpensesByUser(email)
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }
}