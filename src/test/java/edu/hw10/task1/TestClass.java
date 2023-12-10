package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import lombok.Getter;

@Getter
public class TestClass {
    @NotNull
    public String notNullField;

    @Min(10)
    public int minField;

    @Max(50)
    public int maxField;
}
