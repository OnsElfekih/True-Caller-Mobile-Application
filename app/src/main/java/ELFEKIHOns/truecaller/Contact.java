package ELFEKIHOns.truecaller;

public class Contact {
    String first,last,phone;

    public Contact(String first, String last, String phone) {
        this.first = first;
        this.last = last;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
