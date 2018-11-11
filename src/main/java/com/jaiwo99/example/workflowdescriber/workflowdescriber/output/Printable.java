package com.jaiwo99.example.workflowdescriber.workflowdescriber.output;

import java.util.function.Function;

public interface Printable {

    void print(Function<String, Void> printFunc);
}
