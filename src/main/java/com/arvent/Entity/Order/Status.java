package com.arvent.Entity.Order;

public enum Status {

        TOPAY("To Pay"),
        TOSHIP("To Ship"),
        TORECEIVE("To Receive"),
        COMPLETED("Completed"),
        CANCELLED("Cancelled"),
        RETURNREFUND("Return Refund");

        private String status;

        Status(String status) {
                this.status = status;
        }

        public String getStatus() {
                return status;
        }
}
