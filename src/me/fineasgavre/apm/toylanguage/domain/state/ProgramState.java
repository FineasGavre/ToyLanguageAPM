package me.fineasgavre.apm.toylanguage.domain.state;

import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLStack;
import me.fineasgavre.apm.toylanguage.domain.adts.TLList;
import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.TLStack;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;

import java.io.BufferedReader;

public class ProgramState {
    private ITLStack<IStatement> executionStack;
    private ITLMap<String, IValue> symbolTable;
    private ITLMap<StringValue, BufferedReader> fileTable;
    private ITLList<IValue> output;

    private IStatement originalStatement;

    public ProgramState(IStatement originalStatement) {
        this.executionStack = new TLStack<>();
        this.symbolTable = new TLMap<>();
        this.fileTable = new TLMap<>();
        this.output = new TLList<>();

        this.originalStatement = originalStatement.clone();
        this.executionStack.push(originalStatement);
    }

    public ProgramState(ITLStack<IStatement> executionStack, ITLMap<String, IValue> symbolTable, ITLMap<StringValue, BufferedReader> fileTable, ITLList<IValue> output, IStatement originalStatement) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;

        this.originalStatement = originalStatement.clone();
        this.executionStack.push(originalStatement);
    }

    public ITLStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(ITLStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public ITLMap<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(ITLMap<String, IValue> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ITLList<IValue> getOutput() {
        return output;
    }

    public void setOutput(ITLList<IValue> output) {
        this.output = output;
    }

    public IStatement getOriginalStatement() {
        return originalStatement;
    }

    @Override
    public String toString() {
        return "ProgramState{" +
                "executionStack=" + executionStack +
                ", symbolTable=" + symbolTable +
                ", output=" + output +
                ", originalStatement=" + originalStatement +
                '}';
    }

    public ITLMap<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(ITLMap<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }
}