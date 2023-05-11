package business;

public enum MaxBookCheckoutDays {
	SEVEN_DAYS(7),
    TWENTY_ONE_DAYS(21);

    private final int days;

    MaxBookCheckoutDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
