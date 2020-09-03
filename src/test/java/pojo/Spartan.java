package pojo;

public class Spartan {

    private String name;
    private String gender;
    private long phone;
    private int id;

    public Spartan() {
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", id=" + id +
                '}';
    }

    public Spartan(String name, String gender, long phone ) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setId(int id) {
        this.id = id;
    }
}

