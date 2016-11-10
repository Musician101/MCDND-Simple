package io.musician101.mcdndsimple.common.character.tab;

import java.util.ArrayList;
import java.util.List;

public class BackgroundTab
{
    private double weight = 0.0;
    private int age = 0;
    private List<String> armorProficiencies = new ArrayList<>();
    private List<String> background = new ArrayList<>();
    private List<String> bonds = new ArrayList<>();
    private List<String> flaws = new ArrayList<>();
    private List<String> ideals = new ArrayList<>();
    private List<String> otherNotes = new ArrayList<>();
    private List<String> personalityTraits = new ArrayList<>();
    private List<String> racialTraits = new ArrayList<>();
    private List<String> toolProficiencies = new ArrayList<>();
    private List<String> weaponProficiencies = new ArrayList<>();
    private String alignment = "";
    private String eyes = "";
    private String gender = "";
    private String hair = "";
    private String height = "";
    private String languages = "";
    private String size = "";
    private String vision = "";

    public int getAge()
    {
        return age;
    }

    public String getAlignment()
    {
        return alignment;
    }

    public List<String> getArmorProficiencies()
    {
        return armorProficiencies;
    }

    public List<String> getBackground()
    {
        return background;
    }

    public List<String> getBonds()
    {
        return bonds;
    }

    public String getEyes()
    {
        return eyes;
    }

    public List<String> getFlaws()
    {
        return flaws;
    }

    public String getGender()
    {
        return gender;
    }

    public String getHair()
    {
        return hair;
    }

    public String getHeight()
    {
        return height;
    }

    public List<String> getIdeals()
    {
        return ideals;
    }

    public String getLanguages()
    {
        return languages;
    }

    public List<String> getOtherNotes()
    {
        return otherNotes;
    }

    public List<String> getPersonalityTraits()
    {
        return personalityTraits;
    }

    public List<String> getRacialTraits()
    {
        return racialTraits;
    }

    public String getSize()
    {
        return size;
    }

    public List<String> getToolProficiencies()
    {
        return toolProficiencies;
    }

    public String getVision()
    {
        return vision;
    }

    public List<String> getWeaponProficiencies()
    {
        return weaponProficiencies;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setAlignment(String alignment)
    {
        this.alignment = alignment;
    }

    public void setArmorProficiencies(List<String> armorProficiencies)
    {
        this.armorProficiencies = armorProficiencies;
    }

    public void setBackground(List<String> background)
    {
        this.background = background;
    }

    public void setBonds(List<String> bonds)
    {
        this.bonds = bonds;
    }

    public void setEyes(String eyes)
    {
        this.eyes = eyes;
    }

    public void setFlaws(List<String> flaws)
    {
        this.flaws = flaws;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public void setHair(String hair)
    {
        this.hair = hair;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public void setIdeals(List<String> ideals)
    {
        this.ideals = ideals;
    }

    public void setLanguages(String languages)
    {
        this.languages = languages;
    }

    public void setOtherNotes(List<String> otherNotes)
    {
        this.otherNotes = otherNotes;
    }

    public void setPersonalityTraits(List<String> personalityTraits)
    {
        this.personalityTraits = personalityTraits;
    }

    public void setRacialTraits(List<String> racialTraits)
    {
        this.racialTraits = racialTraits;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public void setToolProficiencies(List<String> toolProficiencies)
    {
        this.toolProficiencies = toolProficiencies;
    }

    public void setVision(String vision)
    {
        this.vision = vision;
    }

    public void setWeaponProficiencies(List<String> weaponProficiencies)
    {
        this.weaponProficiencies = weaponProficiencies;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }
}