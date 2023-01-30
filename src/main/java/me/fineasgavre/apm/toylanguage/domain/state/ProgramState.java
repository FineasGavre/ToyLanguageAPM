package me.fineasgavre.apm.toylanguage.domain.state;

import me.fineasgavre.apm.toylanguage.domain.adts.TLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLHeap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLList;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.interfaces.ITLStack;
import me.fineasgavre.apm.toylanguage.domain.adts.TLList;
import me.fineasgavre.apm.toylanguage.domain.adts.TLMap;
import me.fineasgavre.apm.toylanguage.domain.adts.TLStack;
import me.fineasgavre.apm.toylanguage.domain.statements.interfaces.IStatement;
import me.fineasgavre.apm.toylanguage.domain.values.interfaces.IValue;
import me.fineasgavre.apm.toylanguage.domain.values.StringValue;
import me.fineasgavre.apm.toylanguage.exceptions.TLException;
import me.fineasgavre.apm.toylanguage.exceptions.execution.EmptyExecutionStackTLException;

import java.io.BufferedReader;

public class ProgramState {
    private static int nextId = 1;

    private static synchronized int getNextId() {
        return nextId++;
    }

    private final int id;
    private ITLStack<IStatement> executionStack;
    private ITLStack<ITLMap<String, IValue>> symbolTableStack;
    private ITLHeap<IValue> heap;
    private ITLMap<StringValue, BufferedReader> fileTable;
    private ITLList<IValue> output;

    private final IStatement originalStatement;

    public ProgramState(IStatement originalStatement) {
        this.executionStack = new TLStack<>();
        this.symbolTableStack = new TLStack<>();
        this.heap = new TLHeap<>();
        this.fileTable = new TLMap<>();
        this.output = new TLList<>();
        this.id = getNextId();

        this.symbolTableStack.push(new TLMap<>());

        this.originalStatement = originalStatement.clone();
        this.executionStack.push(originalStatement);
    }

    public ProgramState(ITLStack<IStatement> executionStack, ITLStack<ITLMap<String, IValue>> symbolTableStack, ITLHeap<IValue> heap, ITLMap<StringValue, BufferedReader> fileTable, ITLList<IValue> output, IStatement originalStatement) {
        this.executionStack = executionStack;
        this.symbolTableStack = symbolTableStack;
        this.heap = heap;
        this.fileTable = fileTable;
        this.output = output;
        this.id = getNextId();

        this.originalStatement = originalStatement.clone();
    }

    public ProgramState executeOneStep() throws TLException {
        if (executionStack.isEmpty()) {
            throw new EmptyExecutionStackTLException();
        }

        var currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public ITLStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(ITLStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public ITLMap<String, IValue> getSymbolTable() {
        return symbolTableStack.peek();
    }

    public ITLStack<ITLMap<String, IValue>> getSymbolTableStack() {
        return symbolTableStack;
    }

    public void setSymbolTableStack(ITLStack<ITLMap<String, IValue>> symbolTableStack) {
        this.symbolTableStack = symbolTableStack;
    }

    public ITLHeap<IValue> getHeap() {
        return heap;
    }

    public void setHeap(ITLHeap<IValue> heap) {
        this.heap = heap;
    }

    public ITLMap<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(ITLMap<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
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

    public int getId() {
        return id;
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    @Override
    public String toString() {
        return "ProgramState{" +
                "executionStack=" + executionStack +
                ", symbolTableStack=" + symbolTableStack +
                ", heap=" + heap +
                ", output=" + output +
                ", originalStatement=" + originalStatement +
                '}';
    }
}
