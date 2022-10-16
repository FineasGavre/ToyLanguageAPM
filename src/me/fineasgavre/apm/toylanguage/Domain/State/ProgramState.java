package me.fineasgavre.apm.toylanguage.Domain.State;

import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.Domain.ADTs.Interfaces.ITLStack;
import me.fineasgavre.apm.toylanguage.Domain.ADTs.TLList;
import me.fineasgavre.apm.toylanguage.Domain.ADTs.TLMap;
import me.fineasgavre.apm.toylanguage.Domain.ADTs.TLStack;
import me.fineasgavre.apm.toylanguage.Domain.Statements.Interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.Domain.Values.Interfaces.IValue;

public class ProgramState {
    private ITLStack<IStatement> executionStack;
    private ITLMap<String, IValue> symbolTable;
    private ITLList<IValue> output;

    private IStatement originalStatement;

    public ProgramState(IStatement originalStatement) {
        this.executionStack = new TLStack<>();
        this.symbolTable = new TLMap<>();
        this.output = new TLList<>();

        this.originalStatement = originalStatement.clone();
        this.executionStack.push(originalStatement);
    }

    public ProgramState(ITLStack<IStatement> executionStack, ITLMap<String, IValue> symbolTable, ITLList<IValue> output, IStatement originalStatement) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
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

    @Override
    public String toString() {
        return "ProgramState{" +
                "executionStack=" + executionStack +
                ", symbolTable=" + symbolTable +
                ", output=" + output +
                ", originalStatement=" + originalStatement +
                '}';
    }
}
