package com.ngtesting.platform.vo;


import com.ngtesting.platform.config.ConstantIssue;
import com.ngtesting.platform.model.IsuFieldDefine;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class IsuJqlFilter implements Serializable {
    private static final long serialVersionUID = -5923944030125754321L;

    String code;
    String label;
    ConstantIssue.IssueType type;
    ConstantIssue.IssueInput input;
    Map values;
    List<String> operators;
    Boolean display;

    public IsuJqlFilter(IsuFieldDefine field) {
        this.code = field.getCode();
        this.label = field.getLabel();
        this.type = field.getType();
        this.input = field.getInput();
        this.display = field.getDefaultShowInFilters();

        if (ConstantIssue.IssueInput.text.equals(type)) {
            this.operators = ConstantIssue.OperatorsForText;
        } else if (ConstantIssue.IssueInput.date.equals(type)) {
            this.operators = ConstantIssue.OperatorsForDate;
        }
    }

    public IsuJqlFilter(IsuFieldDefine field, Map values) {
        this.code = field.getCode();
        this.label = field.getLabel();
        this.type = field.getType();
        this.input = field.getInput();
        this.display = field.getDefaultShowInFilters();

        this.values = values;
        if (ConstantIssue.IssueInput.text.equals(input)) {
            this.operators = ConstantIssue.OperatorsForText;
        } else if (ConstantIssue.IssueInput.dropdown.equals(input)) {
            this.operators = ConstantIssue.OperatorsForSelect;
        } else if (ConstantIssue.IssueInput.date.equals(input)) {
            this.operators = ConstantIssue.OperatorsForDate;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ConstantIssue.IssueType getType() {
        return type;
    }

    public void setType(ConstantIssue.IssueType type) {
        this.type = type;
    }

    public ConstantIssue.IssueInput getInput() {
        return input;
    }

    public void setInput(ConstantIssue.IssueInput input) {
        this.input = input;
    }

    public Map getValues() {
        return values;
    }

    public void setValues(Map values) {
        this.values = values;
    }

    public List<String> getOperators() {
        return operators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
}
