package simple.enums;

public enum Access {
    ADMIN(1),
    USER(0);

    private int code;
    private Access(int code) {
        this.code = code;
    };

    public static Access fromCode(int code) {
        for(Access access : Access.values()) {
            if(access.getCode() == code) {
                return access;
            };
        };
        return null;
    };

    public int getCode() {
        return this.code;
    };
}
