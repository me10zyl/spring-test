package com.me10zyl.springtest2.springboot.failureAnalyer;

import com.me10zyl.springtest2.exception.MyException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.stereotype.Component;

public class MyFailureAnalyer extends AbstractFailureAnalyzer<Throwable> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, Throwable cause) {
        if (rootFailure instanceof MyException) {
            return new FailureAnalysis("My custom error", "Check configuration", rootFailure);
        }
        return null;
    }
}
