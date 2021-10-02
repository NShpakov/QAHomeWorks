package ru.nshpakov.cucumber.steps.driver;

public enum DriverFactory {

    CHROME {
        @Override
        public DriverManager getDriverManager() {
            return new ChromeDriverManager();
        }
    },
    FIREFOX {
        @Override
        public DriverManager getDriverManager() {
            return new FirefoxDriverManager();
        }
    },
    OPERA {
        @Override
        public DriverManager getDriverManager() {
            return new OperaDriverManager();
        }
    };

    public abstract DriverManager getDriverManager();

}