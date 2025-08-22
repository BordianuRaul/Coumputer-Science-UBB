package Domain;

public enum OperationSign {
    DIVISION("/"),
    MULTIPLICATION("*"),
    SUBTRACTION("-"),
    ADDITION("+");

    private final String sign;

    OperationSign(String sign) {
        this.sign = sign;
    }

    public String fromString(final String sign) {
        for(OperationSign operationSign : OperationSign.values()) {
            if(operationSign.sign.equals(sign)) {
                return operationSign.toString();
            }
        }
        return null;
    }

}
