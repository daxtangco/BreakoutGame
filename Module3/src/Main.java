public class Main {
    public static void main(String[] args) {
        String multiline = "**********************************************\n" +
                "*                                            *\n" +
                "*            Dax Axis A. Tangco              *\n" +
                "*                 BS CpE                     *\n" +
                "*                11916877                    *\n" +
                "*              August 10, 2000               *\n" +
                "*        dax_axis_tangco@dlsu.edu.ph         *\n" +
                "*             09477560064                    *\n" +
                "*                                            *\n" +
                "**********************************************";
        System.out.println(multiline);

        Student student = new Student();
        student.setName("Dax Axis A. Tangco");
        student.setCourse("BS CpE");
        student.setId(11916877);
        student.setBirthday("August 10, 2000");
        student.setEmail("dax_axis_tangco@dlsu.edu.ph");
        student.setMobile("09477560064");

        System.out.println(student.toString());
    }
}


class Student {
    private String name;
    private String course;
    private int id;
    private String birthday;
    private String email;
    private String mobile;

    // Getters
    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getId() {
        return id;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // toString method
    @Override
    public String toString() {
        return "**********************************************\n" +
                "*                                            *\n" +
                "*            " + name + "              *\n" +
                "*                 " + course + "                     *\n" +
                "*                " + id + "                    *\n" +
                "*              " + birthday + "               *\n" +
                "*        " + email + "         *\n" +
                "*             " + mobile + "                    *\n" +
                "*                                            *\n" +
                "**********************************************";
    }
}
