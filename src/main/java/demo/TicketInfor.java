package demo;

import java.lang.reflect.Field;

public class TicketInfor {

    String firstName;
    String lastName;
    String creditNum;

    String flightType;
    String passCountSelect;

    String outFlightPortSelect;
    String outFlightMonth;
    String outFlightDate;

    String inFlightPortSelect;
    String inFlightMonth;
    String inFlightDate;

    String flightClassSelect;
    String airline;

    String outFlightSelect;
    String inFlightSelect;

    String billAddress;
    String billCity;
    String billState;
    String billPostal;

    String delAddress;
    String delCity;
    String delState;
    String delPostal;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(String creditNum) {
        this.creditNum = creditNum;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public String getPassCountSelect() {
        return passCountSelect;
    }

    public void setPassCountSelect(String passCountSelect) {
        this.passCountSelect = passCountSelect;
    }

    public String getOutFlightPortSelect() {
        return outFlightPortSelect;
    }

    public void setOutFlightPortSelect(String outFlightPortSelect) {
        this.outFlightPortSelect = outFlightPortSelect;
    }

    public String getOutFlightMonth() {
        return outFlightMonth;
    }

    public void setOutFlightMonth(String outFlightMonth) {
        this.outFlightMonth = outFlightMonth;
    }

    public String getOutFlightDate() {
        return outFlightDate;
    }

    public void setOutFlightDate(String outFlightDate) {
        this.outFlightDate = outFlightDate;
    }

    public String getInFlightPortSelect() {
        return inFlightPortSelect;
    }

    public void setInFlightPortSelect(String inFlightPortSelect) {
        this.inFlightPortSelect = inFlightPortSelect;
    }

    public String getInFlightMonth() {
        return inFlightMonth;
    }

    public void setInFlightMonth(String inFlightMonth) {
        this.inFlightMonth = inFlightMonth;
    }

    public String getInFlightDate() {
        return inFlightDate;
    }

    public void setInFlightDate(String inFlightDate) {
        this.inFlightDate = inFlightDate;
    }

    public String getFlightClassSelect() {
        return flightClassSelect;
    }

    public void setFlightClassSelect(String flightClassSelect) {
        this.flightClassSelect = flightClassSelect;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getOutFlightSelect() {
        return outFlightSelect;
    }

    public void setOutFlightSelect(String outFlightSelect) {
        this.outFlightSelect = outFlightSelect;
    }

    public String getInFlightSelect() {
        return inFlightSelect;
    }

    public void setInFlightSelect(String inFlightSelect) {
        this.inFlightSelect = inFlightSelect;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getBillPostal() {
        return billPostal;
    }

    public void setBillPostal(String billPostal) {
        this.billPostal = billPostal;
    }

    public String getDelAddress() {
        return delAddress;
    }

    public void setDelAddress(String delAddress) {
        this.delAddress = delAddress;
    }

    public String getDelCity() {
        return delCity;
    }

    public void setDelCity(String delCity) {
        this.delCity = delCity;
    }

    public String getDelState() {
        return delState;
    }

    public void setDelState(String delState) {
        this.delState = delState;
    }

    public String getDelPostal() {
        return delPostal;
    }

    public void setDelPostal(String delPostal) {
        this.delPostal = delPostal;
    }

    String outFlightPort;
    String outFlightName;
    String outFlightClass;
    String outFlightPrice;

    String inFlightPort;
    String inFlightName;
    String inFlightClass;
    String inFlightPrice;

    String passCount;
    String billAdd;
    String delAdd;

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

    public String getBillAdd() {
        return billAdd;
    }

    public void setBillAdd(String billAdd) {
        this.billAdd = billAdd;
    }

    public String getDelAdd() {
        return delAdd;
    }

    public void setDelAdd(String delAdd) {
        this.delAdd = delAdd;
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
