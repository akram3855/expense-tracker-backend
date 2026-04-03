package com.akram.expensetracker.controller;

import com.akram.expensetracker.model.Expense;
import com.akram.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    // 🔥 ADD EXPENSE (USER BASED)
    @PostMapping
    public Expense addExpense(@RequestBody Expense expense, Authentication authentication) {
        return service.addExpense(expense, authentication.getName());
    }

    //  GET EXPENSES
    @GetMapping
    public List<Expense> getAll(Authentication authentication) {
        return service.getExpensesByUser(authentication.getName());
    }
    //  DELETE EXPENSE
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
    }

    //  UPDATE EXPENSE
    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }
}