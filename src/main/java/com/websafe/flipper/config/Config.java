package com.websafe.flipper.config;

public class Config {
    private static Integer budget = 2147483647;

    public void config(String settingName, String settingValue) {
        settingValue = settingValue.toLowerCase();
        switch (settingName.toLowerCase()) {
            case "budget":
                budget = new Integer(settingValue);
        }
    }

    public Integer getBudget() {
        return budget;
    }
}
