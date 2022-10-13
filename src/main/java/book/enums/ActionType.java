package book.enums;

public enum ActionType {
    INSERT(1),
    UPDATE(2),
    DELETE(3),
    UPLOAD(4),
    LOGIN(5),
    LOGOUT(6);

    private final int value;
    ActionType(int value) {
        this.value = value;
    }
    public int value() {
        return this.value;
    }
}
