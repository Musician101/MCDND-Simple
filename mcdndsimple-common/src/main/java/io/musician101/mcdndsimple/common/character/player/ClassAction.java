package io.musician101.mcdndsimple.common.character.player;

import io.musician101.mcdndsimple.common.character.player.outputoption.OutputOptions;
import java.util.ArrayList;
import java.util.List;

public class ClassAction extends Rechargeable {

    private String gainedFrom = "---";
    private int max = 0;
    private String name = "";
    private List<String> output = new ArrayList<>();
    private OutputOptions outputOptions = new OutputOptions();
    private int used = 0;

    public String getGainedFrom() {
        return gainedFrom;
    }

    public void setGainedFrom(String gainedFrom) {
        this.gainedFrom = gainedFrom;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public OutputOptions getOutputOptions() {
        return outputOptions;
    }

    public void setOutputOptions(OutputOptions outputOptions) {
        this.outputOptions = outputOptions;
    }

    public int getUsedCharges() {
        return used;
    }

    public void setUsedCharges(int used) {
        this.used = used;
    }
}
