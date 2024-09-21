package com.example.rehabready.Interfaces;

public interface IDialogCloseListener {
    enum Action {
        DELETE,
        ADD
    }

    public void handleDialogClose(Action action, Long doseId);
}
