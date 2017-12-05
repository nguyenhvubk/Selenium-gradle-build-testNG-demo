package demo;

import java.lang.reflect.Field;

public class TicketInfor {

    String outFlightPort;
    String outFlightName;
    String outFlightClass;
    String outFlightPrice;

    String inFlightPort;
    String inFlightName;
    String inFlightClass;
    String inFlightPrice;

    String passCount;
    String billAddress;
    String delAddress;

    String tax;
    String totalPrice;

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append( this.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(this) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    };

    public String getOutFlightPort() {
        return outFlightPort;
    }

    public void setOutFlightPort(String outFlightPort) {
        this.outFlightPort = outFlightPort;
    }

    public String getOutFlightName() {
        return outFlightName;
    }

    public void setOutFlightName(String outFlightName) {
        this.outFlightName = outFlightName;
    }

    public String getOutFlightClass() {
        return outFlightClass;
    }

    public void setOutFlightClass(String outFlightClass) {
        this.outFlightClass = outFlightClass;
    }

    public String getOutFlightPrice() {
        return outFlightPrice;
    }

    public void setOutFlightPrice(String outFlightPrice) {
        this.outFlightPrice = outFlightPrice;
    }

    public String getInFlightPort() {
        return inFlightPort;
    }

    public void setInFlightPort(String inFlightPort) {
        this.inFlightPort = inFlightPort;
    }

    public String getInFlightName() {
        return inFlightName;
    }

    public void setInFlightName(String inFlightName) {
        this.inFlightName = inFlightName;
    }

    public String getInFlightClass() {
        return inFlightClass;
    }

    public void setInFlightClass(String inFlightClass) {
        this.inFlightClass = inFlightClass;
    }

    public String getInFlightPrice() {
        return inFlightPrice;
    }

    public void setInFlightPrice(String inFlightPrice) {
        this.inFlightPrice = inFlightPrice;
    }

    public String getPassCount() {
        return passCount;
    }

    public void setPassCount(String passCount) {
        this.passCount = passCount;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getDelAddress() {
        return delAddress;
    }

    public void setDelAddress(String delAddress) {
        this.delAddress = delAddress;
    }

    public String getTax() {
        return tax;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}
