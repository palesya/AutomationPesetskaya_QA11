package Lecture_14_15;

public class User_Builder {

    String firstName;
    String lastName;
    Integer age;
    String sex;
    Integer phone;
    String job="QA";

    public static class Builder{
        private User_Builder user;

        public Builder() {
            this.user = new User_Builder();
        }
        public Builder withFirstName(String firstName){
            user.firstName=firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            user.lastName=lastName;
            return this;
        }

        public Builder withAge(Integer age){
            user.age=age;
            return this;
        }

        public Builder withJob(String job){
            user.job=job;
            return this;
        }

        public Builder withSex(String sex){
            user.sex=sex;
            return this;
        }

        public Builder withPhone(Integer phone){
            user.phone=phone;
            return this;
        }

        public User_Builder build(){
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", phone=" + phone +
                ", job='" + job + '\'' +
                '}';
    }
}
