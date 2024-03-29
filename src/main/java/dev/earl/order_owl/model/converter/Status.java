package dev.earl.order_owl.model.converter;

public enum Status {
    SHIPPED("S"), DELIVERED("D"), CANCELED("C"), PENDING("P"), RETURNED("R");

    private String statusType;

    private Status(String statusType){
        this.statusType = statusType;
    }

    public String getStatusType(){
        return statusType;
    }

    public static Status fromStatusType(String statusType){
        switch (statusType) {
            case "S" -> {
                return Status.SHIPPED;
            }
            case "D" -> {
                return Status.DELIVERED;
            }
            case "C" -> {
                return Status.CANCELED;
            }
            case "P" -> {
                return Status.PENDING;
            }
            case "R" -> {
                return Status.RETURNED;
            }
            default ->  {
                throw new IllegalArgumentException("status type [" + statusType + "] is not supported");
            }
        }
    }
}
