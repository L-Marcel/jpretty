package simple.enums;

public enum Genrer {
    MALE(0),
    FEMALE(1),
    OTHER(2);

    private int code;
    private Genrer(int code) {
        this.code = code;
    };

    public static Genrer fromCode(int code) {
        for(Genrer genrer : Genrer.values()) {
            if(genrer.getCode() == code) {
                return genrer;
            };
        };
        return null;
    };


    public int getCode() {
        return this.code;
    };
}
