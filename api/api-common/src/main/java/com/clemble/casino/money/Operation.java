package com.clemble.casino.money;

public enum Operation {

    Debit,
    Credit;

    public Operation toOpposite() {
        switch(this) {
            case Credit:
                return Debit;
            case Debit:
                return Credit;
            default:
                throw new IllegalArgumentException();
        }
    }

}
