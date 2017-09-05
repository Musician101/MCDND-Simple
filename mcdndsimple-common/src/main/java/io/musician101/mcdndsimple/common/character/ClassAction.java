package io.musician101.mcdndsimple.common.character;

import io.musician101.mcdndsimple.common.character.outputoption.OutputOptions;
import java.util.ArrayList;
import java.util.List;

public class ClassAction extends Rechargeable
{
    private int max = 0;
    private int used = 0;
    private List<String> output = new ArrayList<>();
    private OutputOptions outputOptions = new OutputOptions();
    private String gainedFrom = "---";
    private String name = "";

    public String getGainedFrom()
    {
        return gainedFrom;
    }

    public int getMax()
    {
        return max;
    }

    public String getName()
    {
        return name;
    }

    public List<String> getOutput() {
        return output;
    }

    public OutputOptions getOutputOptions() {
        return outputOptions;
    }

    public int getUsedCharges()
    {
        return used;
    }

    public void setGainedFrom(String gainedFrom)
    {
        this.gainedFrom = gainedFrom;
    }

    public void setMax(int max)
    {
        this.max = max;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public void setOutputOptions(OutputOptions outputOptions) {
        this.outputOptions = outputOptions;
    }

    public void setUsedCharges(int used)
    {
        this.used = used;
    }
}
